package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Review;
import util.DataWrapper;
import util.LocalDateAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class JsonExportService {
    private static JsonExportService instance = new JsonExportService();

    private JsonExportService() {}
    public static JsonExportService getInstance() {
        if (instance == null) {
            instance = new JsonExportService();
        }
        return instance;
    }
    public  String convertReviewToJson(List<Review> reviews) {
        DataWrapper wrapper = new DataWrapper(reviews);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        return gson.toJson(wrapper);
    }


    public void writeToFile(String json, String relativePath) {
        try {
            File file = new File(relativePath);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json);
                System.out.println("✅ Export JSON effectué dans : " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("❌ Erreur d'écriture JSON : " + e.getMessage());
        }
    }

}
