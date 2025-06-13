package model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private Long id;
    private String title;
    private String author;
    private int publishYear;
    private Genre genre;
    private static Long count = 0L;

    public Book() {
        this.id = ++count;
    }

    public Book(String title, String author, int publishYear, Genre genre) {
        this();
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.genre = genre;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publishYear == book.publishYear && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && genre == book.genre && Objects.equals(count, book.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publishYear, genre, count);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                ", genre=" + genre +
                ", count=" + count +
                '}';
    }
}
