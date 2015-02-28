package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.util.List;

/**
 * Created by Thoughtworker on 2/28/15.
 */
public class UserRecommender {
    public static List<RecommendedItem> GetRecommendations(DataModel dm) throws TasteException {

            UserSimilarity similarity = new PearsonCorrelationSimilarity(dm);

            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dm);

            UserBasedRecommender recommender = new GenericUserBasedRecommender(dm, neighborhood, similarity);

            return recommender.recommend(100, 5);
    }
}
