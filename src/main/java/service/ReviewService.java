package service;

import model.Book;
import model.Review;

import java.util.*;
import java.util.stream.Collectors;

public class ReviewService {
    private static ReviewService instance = new ReviewService();
    private final List<Review> reviews = new ArrayList<Review>();

    private ReviewService () {}

    public static ReviewService getInstance() {
        if(instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviewsByBookId(Long bookId) {
       return reviews.stream()
                .filter(r-> Objects.equals(r.getBookId(), bookId))
               .toList();
    }

    public List<Review> getAllReviews() {
        return reviews;
    }

    public List<Review> getAllReviewsByUsername(String username) {
        List<Review> result = reviews.stream()
                .filter(r -> Objects.equals(r.getUsername(), username))
                .toList();
        System.out.println("Trouvé " + result.size() + " review(s) pour " + username);
        return result;
    }


    public double averageRatingByBook(Long bookId) {
      return getReviewsByBookId(bookId).stream().mapToDouble(Review::getRating).average().orElse(0);
    }

    public double averageRating (List<Book> books) {
       return getReviewsByBooks(books).stream().mapToDouble(Review::getRating).average().orElse(0);

    }
    public List<Review> getReviewsByUsernameSortByDateDesc(String username) {
        return this.getAllReviewsByUsername(username).stream()
                .sorted(Comparator.comparing(Review::getDate).reversed()).toList();
    }

    public List<Review> getReviewsByBooks(List<Book> books) {
        Set<Long> bookIds = books.stream()
                .map(Book::getId)
                .collect(Collectors.toSet());
        return reviews.stream()
                .filter(review -> bookIds.contains(review.getBookId()))
                .collect(Collectors.toList());
    }

    public  List<Review> getReviews() {
        return reviews;
    }
}
