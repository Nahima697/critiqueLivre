package data;

import model.Book;
import model.Genre;
import model.Review;

import java.time.LocalDate;
import java.util.List;

public class FakeDataBase {
    public static List<Book> getBooks() {
        return List.of(
                new Book( "Dune", "Frank Herbert", 1965, Genre.SCIENCE_FICTION),
                new Book( "1984", "George Orwell", 1949, Genre.SCIENCE_FICTION),
                new Book( "Le Seigneur des Anneaux", "J.R.R. Tolkien", 1954, Genre.FANTASY),
                new Book( "Le Meurtre de Roger Ackroyd", "Agatha Christie", 1926, Genre.POLICIER)
        );
    }

    public static List<Review> getReviews() {
        return List.of(
                new Review(1L, "Alice", 4.5, "Très bon livre", LocalDate.of(2022, 5, 1)),
                new Review(1L, "Bob", 5.0, "Chef-d’œuvre", LocalDate.of(2023, 3, 12)),
                new Review(2L, "Charlie", 4.0, "Visionnaire", LocalDate.of(2021, 11, 20)),
                new Review(3L, "Alice", 5.0, "Incroyable", LocalDate.of(2022, 1, 10)),
                new Review(4L, "Bob", 3.5, "Intéressant", LocalDate.of(2020, 7, 4)),
                new Review(4L, "Alice", 4.0, "Classique efficace", LocalDate.of(2022, 8, 15))
        );
    }
}
