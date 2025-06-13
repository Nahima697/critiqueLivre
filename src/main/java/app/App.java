package app;

import service.JsonExportService;
import data.FakeDataBase;
import model.Book;
import model.Genre;
import model.Review;
import service.BookService;

import service.ReviewService;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final BookService bookService = BookService.getInstance();
    private static final ReviewService reviewService = ReviewService.getInstance();
    private static final JsonExportService jsonExportService = JsonExportService.getInstance();
    public static void main(String[] args) {
        loadInitialData(bookService,reviewService);
        startMenu();
        String json = jsonExportService.convertReviewToJson(reviewService.getAllReviews());
        jsonExportService.writeToFile(json, "reviews.json");
    }
    public static void loadInitialData(BookService bookService, ReviewService reviewService) {
        List<Book> books = FakeDataBase.getBooks();
        List<Review> reviews = FakeDataBase.getReviews();
        bookService.getAllBooks().addAll(books);
        reviewService.getReviews().addAll(reviews);
        System.out.println("📦 Livres chargés : " + bookService.getAllBooks().size());

    }

    public static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Afficher les livres triés par moyenne des notes");
            System.out.println("2. Afficher les critiques d’un utilisateur");
            System.out.println("3. Meilleurs livres par genre");
            System.out.println("4. Critiques de livres avant une année");
            System.out.println("5. Statistiques par genre (nombre + note moyenne)");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("📚 Livres triés par note moyenne :");
                    List<Book> sortedBooks = bookService.getBooksByAverageRating(bookService.getAllBooks());
                    sortedBooks.forEach(book -> System.out.printf(
                            "- ID: %d, Titre: %s, Note moyenne: %.2f%n",
                            book.getId(), book.getTitle(),
                            reviewService.averageRatingByBook(book.getId())));
                }

                case 2 -> {
                    System.out.print("Nom d'utilisateur : ");
                    String username = scanner.nextLine();
                    List<Review> reviews = reviewService.getReviewsByUsernameSortByDateDesc(username);
                    System.out.println("📝 Critiques de " + username + " :");
                    reviews.forEach(System.out::println);
                }

                case 3 -> {
                    System.out.println("Genres disponibles :");
                    Genre[] genreArray = Genre.values();
                    for (int i = 0; i < genreArray.length; i++) {
                        System.out.println(i + ". " + genreArray[i]);
                    }
                    System.out.print("Choisissez un genre par numéro : ");
                    int genreIndex = scanner.nextInt();
                    scanner.nextLine();

                    if (genreIndex >= 0 && genreIndex < genreArray.length) {
                        Genre genre = genreArray[genreIndex];
                        List<Book> bestBooks = bookService.getBooksByGenreByBestAverageRating(genre);
                        System.out.println("🏆 Meilleurs livres en " + genre + " :");
                        bestBooks.forEach(b -> System.out.printf("- %s (%.2f)\n",
                                b.getTitle(),
                                reviewService.averageRatingByBook(b.getId())));
                    } else {
                        System.out.println("❌ Index invalide !");
                    }
                }

                case 4 -> {
                    System.out.print("Entrer l'année limite : ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    List<Book> oldBooks = bookService.getBooksBeforeYear(year);
                    System.out.println("📖 Livres publiés avant " + year + " :");
                    oldBooks.forEach(b -> System.out.println("- " + b.getTitle()));

                    List<Review> reviews = reviewService.getReviewsByBooks(oldBooks);
                    System.out.println("📝 Leurs critiques :");
                    reviews.forEach(System.out::println);
                }

                case 5 -> {
                    System.out.println("📊 Statistiques par genre :");
                    for (Genre genre : Genre.values()) {
                        List<Book> booksByGenre = bookService.getBooksByGenre(genre);
                        long count = bookService.countBooksByGenre(booksByGenre).getOrDefault(genre, 0L);
                        double avg = bookService.getAverageRatingByGenre(booksByGenre).getOrDefault(genre, 0.0);
                        System.out.printf("- %s → %d livre(s), Note moyenne : %.2f\n", genre, count, avg);
                    }
                }

                case 0 -> System.out.println("👋 À bientôt !");
                default -> System.out.println("❌ Choix invalide !");
            }

        } while (choice != 0);
    }


}

