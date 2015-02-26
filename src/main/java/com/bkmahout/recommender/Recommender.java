package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Thoughtworker on 2/10/15.
 */
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

            UserSimilarity similarity = new PearsonCorrelationSimilarity(dm);

            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dm);

            UserBasedRecommender recommender = new GenericUserBasedRecommender(dm, neighborhood, similarity);

            List<RecommendedItem> userRecommendations = recommender.recommend(100, 5);
            userRecommendations.forEach(rec -> {
                try {
                    System.out.println(titleFinder.GetTitleFromId(rec.getItemID()) + "," + rec.getValue());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            System.out.println("Oops!");
            e.printStackTrace();
        } catch (TasteException e) {
            System.out.println("Mahout oops!");
            e.printStackTrace();
        }
    }

    public static void printItemRecommendations(List<ItemRecommendation> recommendations, MovieLookerUpper titleFinder) throws IOException {
        for(ItemRecommendation recommendedItemList : recommendations) {
            String title = titleFinder.GetTitleFromId(recommendedItemList.ItemId);
            for(RecommendedItem recommendedItem : recommendedItemList.Recommendation) {
                String recommendedTitle = titleFinder.GetTitleFromId(recommendedItem.getItemID());
                System.out.println(title + "," + recommendedTitle + "," + recommendedItem.getValue());
            }
        }
    }
}
