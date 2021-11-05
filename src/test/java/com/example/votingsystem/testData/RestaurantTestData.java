package com.example.votingsystem.testData;

import com.example.votingsystem.model.BaseEntity;
import com.example.votingsystem.model.Restaurant;

import java.util.List;

public class RestaurantTestData {

    public final static Restaurant restaurant01 = new Restaurant(BaseEntity.START_SEQ + 10, "Restaurant #1");
    public final static Restaurant restaurant02 = new Restaurant(BaseEntity.START_SEQ + 11, "Restaurant #2");
    public final static Restaurant restaurant03 = new Restaurant(BaseEntity.START_SEQ + 12, "Restaurant #3");
    public final static Restaurant restaurant04 = new Restaurant(BaseEntity.START_SEQ + 13, "Restaurant #4");

    public final static List<Restaurant> restaurants = List.of(restaurant01, restaurant02, restaurant03, restaurant04);
}
