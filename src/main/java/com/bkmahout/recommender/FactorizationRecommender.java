package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.svd.Factorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.IOException;
import java.util.List;

public class FactorizationRecommender {
    public static List<RecommendedItem> GetRecommendations(DataModel dm) throws TasteException {
        Factorizer factorizer = new SVDPlusPlusFactorizer(dm, 1, 5);
        SVDRecommender recommender = new SVDRecommender(dm,factorizer);
        return recommender.recommend(100, 5);
    }

    public static void printFactorizationRecommendations(MovieLookerUpper titleFinder, List<RecommendedItem> userRecommendations) {
        userRecommendations.forEach(rec -> {
            try {
                System.out.println(titleFinder.GetTitleFromId(rec.getItemID()) + "," + rec.getValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
