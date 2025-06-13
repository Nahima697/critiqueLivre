package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Review {
    private Long bookId;
    private String username;
    private double rating;
    private String comment;
    private LocalDate date;

    public Review(Long bookId, String username, double rating, String comment, LocalDate date) {
        if (rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("La note doit être entre 1.0 et 5.0.");
        }

        this.bookId = bookId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public int hashCode() {
        return Objects.hash(bookId, username, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "bookId=" + bookId +
                ", username='" + username + '\'' +
                ", rating=" + rating +
                '}';
    }
}
