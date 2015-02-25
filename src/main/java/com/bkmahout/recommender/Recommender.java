package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.*;
import java.util.ArrayList;
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
            String title = titleFinder.getTitleFromId(recommendedItemList.ItemId);
            for(RecommendedItem recommendedItem : recommendedItemList.Recommendation) {
                String recommendedTitle = titleFinder.getTitleFromId(recommendedItem.getItemID());
                System.out.println(title + "," + recommendedTitle + "," + recommendedItem.getValue());
            }
        }
    }
}

