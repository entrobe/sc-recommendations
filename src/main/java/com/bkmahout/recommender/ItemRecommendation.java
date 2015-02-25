package com.bkmahout.recommender;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.util.List;

public class ItemRecommendation {
    public long ItemId;
    public List<RecommendedItem> Recommendation;

    public ItemRecommendation(long itemId, List<RecommendedItem> recommendation){
        ItemId = itemId;
        Recommendation = recommendation;
    }
}
