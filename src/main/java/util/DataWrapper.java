package util;
import model.Review;

import java.util.List;

public class DataWrapper {
    public List<Review> reviews;

    public DataWrapper(List<Review> reviews) {
        this.reviews = reviews;
    }
}
