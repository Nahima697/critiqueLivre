package service;

import model.Book;
import model.Genre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookService {
    private static BookService instance = new BookService();
    private final ReviewService reviewService = ReviewService.getInstance();
    private List<Book> books = new ArrayList<>();

    private BookService() {}
    public static BookService getInstance() {
        if(instance == null) {
            instance = new BookService();
        }
        return instance;
    }
     public Book addBook(String title, String author, int year, Genre genre) {
        Book book = new Book(title,  author,  year,genre );
        books.add(book);
        return book;
     }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        for (Book book : books) {
            genres.add(book.getGenre());
        }
        return genres;
    }

    public  List<Book> getBooksByAverageRating(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparingDouble(book -> reviewService.averageRatingByBook(book.getId())))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByGenreByBestAverageRating(Genre genre) {
        return books.stream()
                .filter(book -> book.getGenre().equals(genre))
                .sorted(Comparator.comparingDouble(book -> reviewService.averageRatingByBook(book.getId())))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Book> getBooksBeforeYear(int year) {
        return books.stream().filter(b->b.getPublishYear() < year)
                .toList();
    }

    public List<Book> getBooksByGenre(Genre genre) {
        return books.stream()
                .filter(b -> b.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    public int getBookNumber(List<Book> books) {
        return books.size();
    }

    //Grouper les livres par genre et afficher, pour chaque groupe la note moyenne globale
    public Map<Genre, Double> getAverageRatingByGenre(List<Book> books) {
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        Collectors.averagingDouble(b -> reviewService.averageRatingByBook(b.getId()))
                ));
    }

    public Map<Genre,Long> countBooksByGenre(List<Book> books) {
        return books.stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
    }

}
