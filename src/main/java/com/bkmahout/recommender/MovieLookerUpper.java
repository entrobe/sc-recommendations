package com.bkmahout.recommender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieLookerUpper {
    private String FilePath;
    private List<String[]> Movies;

    public MovieLookerUpper(String filePath){
        FilePath = filePath;
        Movies = new ArrayList<>();
    }

    public void CreateMovieList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FilePath));
        String line;
        while((line = br.readLine()) != null) {
            String[] values = line.split(",", -1);
            Movies.add(values);
        }
    }

    public String GetTitleFromId(long itemId) throws IOException {
        return Movies.stream()
                .filter(movieInfo -> Long.parseLong(movieInfo[0]) == itemId)
                .map(foundTitle -> foundTitle[1])
                .findFirst()
                .orElse("Title Not Found");
    }
}
