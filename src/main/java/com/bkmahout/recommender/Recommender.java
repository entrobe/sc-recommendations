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

            ItemSimilarity sim = new LogLikelihoodSimilarity(dm);

            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);

            List<ItemRecommendation> recommendations = getRecommendations(dm, recommender);

            for(ItemRecommendation recommendedItemList : recommendations) {
                String title = MovieLookerUpper.getTitleFromId(recommendedItemList.ItemId);
                for(RecommendedItem recommendedItem : recommendedItemList.Recommendation) {
                    String recommendedTitle = MovieLookerUpper.getTitleFromId(recommendedItem.getItemID());
                    System.out.println(title + "," + recommendedTitle + "," + recommendedItem.getValue());
                }
            }
        } catch (IOException e) {
            System.out.println("Oops!");
            e.printStackTrace();
        } catch (TasteException e) {
            System.out.println("Mahout oops!");
            e.printStackTrace();
        }
    }

    public static List<ItemRecommendation> getRecommendations(DataModel dm, GenericItemBasedRecommender recommender) throws TasteException {
        int x = 1;

        List<ItemRecommendation> recommendations = new ArrayList<>();
        for(LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();){
            long itemId = items.nextLong();

            recommendations.add(new ItemRecommendation(itemId, recommender.mostSimilarItems(itemId, 5)));

            x++;
            if(x>10) break;
        }
        return recommendations;
    }
}

class MovieLookerUpper {
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

