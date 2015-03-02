package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import java.io.File;
import java.io.IOException;

public class Recommender {
    public static void main(String[] args) {
        try {
            DataModel dm = new FileDataModel(new File("data/movies.csv"));
            MovieLookerUpper titleFinder = new MovieLookerUpper("data/movie-information.csv");
            titleFinder.CreateMovieList();

        } catch (IOException e) {
            System.out.println("Oops!");
            e.printStackTrace();
        }
    }

//    private static void printUserRecommendations(MovieLookerUpper titleFinder, List<RecommendedItem> userRecommendations) {
//        userRecommendations.forEach(rec -> {
//            try {
//                System.out.println(titleFinder.GetTitleFromId(rec.getItemID()) + "," + rec.getValue());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
//
//    public static void printItemRecommendations(List<ItemRecommendation> recommendations, MovieLookerUpper titleFinder) throws IOException {
//        recommendations.forEach(recommendation -> {
//            try {
//                String title = titleFinder.GetTitleFromId(recommendation.ItemId);
//                recommendation.Recommendation.forEach(item -> {
//                    try {
//                        String recommendedTitle = titleFinder.GetTitleFromId(item.getItemID());
//                        System.out.println(title + "," + recommendedTitle + "," + item.getValue());
//                    }
//                    catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}
