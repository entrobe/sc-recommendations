package com.bkmahout.recommender;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MovieLookerUpper {
    private static String filePath = "data/movie-information.csv";

    public static String getTitleFromId(long itemId) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        String movieTitle = "Title not found";
        while((line = br.readLine()) != null) {
            String[] values = line.split(",", -1);
            if(Long.parseLong(values[0]) == itemId) {
                movieTitle = values[1];
            }
        }
        return movieTitle;
    }
}
