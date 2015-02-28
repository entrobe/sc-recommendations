package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.util.ArrayList;
import java.util.List;

public class ItemRecommender {
    public static List<ItemRecommendation> GetRecommendations(DataModel dm) throws TasteException {
        ItemSimilarity sim = new LogLikelihoodSimilarity(dm);

        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
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
