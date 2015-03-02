package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Recommender {
    public static void main(String[] args){
        try {
            DataModel dm = new FileDataModel(new File("data/movies.csv"));
            MovieLookerUpper titleFinder = new MovieLookerUpper("data/movie-information.csv");
            titleFinder.CreateMovieList();

            System.out.println("======Item Recommendations=====");
            List<ItemRecommendation> recommendations = ItemRecommender.GetRecommendations(dm);
            ItemRecommender.printItemRecommendations(recommendations, titleFinder);
        } catch (IOException e) {
            System.out.println("Oops!");
            e.printStackTrace();
        } catch (TasteException e) {
            System.out.println("Mahout oops!");
            e.printStackTrace();
        }
    }

}
