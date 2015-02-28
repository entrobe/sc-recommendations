package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

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
            printItemRecommendations(recommendations, titleFinder);

            System.out.println("======User Recommendations=====");
            List<RecommendedItem> userRecommendations = UserRecommender.GetRecommendations(dm);
            printUserRecommendations(titleFinder, userRecommendations);

            System.out.println("======Latent Factor Model Recommendations======");
            List<RecommendedItem> svdRecommendations = FactorizationRecommender.GetRecommendations(dm);
            printUserRecommendations(titleFinder, svdRecommendations);
        } catch (IOException e) {
            System.out.println("Oops!");
            e.printStackTrace();
        } catch (TasteException e) {
            System.out.println("Mahout oops!");
            e.printStackTrace();
        }
    }

    private static void printUserRecommendations(MovieLookerUpper titleFinder, List<RecommendedItem> userRecommendations) {
        userRecommendations.forEach(rec -> {
            try {
                System.out.println(titleFinder.GetTitleFromId(rec.getItemID()) + "," + rec.getValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void printItemRecommendations(List<ItemRecommendation> recommendations, MovieLookerUpper titleFinder) throws IOException {
        recommendations.forEach(recommendation -> {
            try {
                String title = titleFinder.GetTitleFromId(recommendation.ItemId);
                recommendation.Recommendation.forEach(item -> {
                    try {
                        String recommendedTitle = titleFinder.GetTitleFromId(item.getItemID());
                        System.out.println(title + "," + recommendedTitle + "," + item.getValue());
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
