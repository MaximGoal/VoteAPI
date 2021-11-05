package com.example.votingsystem.testData;

import com.example.votingsystem.model.BaseEntity;
import com.example.votingsystem.model.Dish;

import java.util.List;

import static com.example.votingsystem.model.BaseEntity.*;

public class DishesTestData {

    public static final Dish dish01 = new Dish(START_SEQ+10000, "Tea", 11.0);
    public static final Dish dish02 = new Dish(START_SEQ+10001, "Juice", 15.0);
    public static final Dish dish03 = new Dish(START_SEQ+10002, "Water", 5.0);
    public static final Dish dish04 = new Dish(START_SEQ+10003, "Bread", 2.5);
    public static final Dish dish05 = new Dish(START_SEQ+10004, "Gamburger", 20.0);
    public static final Dish dish06 = new Dish(START_SEQ+10005, "Rice", 10.0);
    public static final Dish dish07 = new Dish(START_SEQ+10006, "Spagetti", 12.0);
    public static final Dish dish08 = new Dish(START_SEQ+10007, "Rizotto", 35.5);
    public static final Dish dish09 = new Dish(START_SEQ+10008, "Jam", 1.0);
    public static final Dish dish10 = new Dish(START_SEQ+10009, "Meal", 30.0);
    public static final Dish dish11 = new Dish(START_SEQ+10010, "Chicken meal", 22.0);
    public static final Dish dish12 = new Dish(START_SEQ+10011, "Borstch", 17.0);
    public static final Dish dish13 = new Dish(START_SEQ+10012, "Schi", 16.0);
    public static final Dish dish14 = new Dish(START_SEQ+10013, "Solyanka", 16.0);
    public static final Dish dish15 = new Dish(START_SEQ+10014, "Harcho", 15.0);
    public static final Dish dish16 = new Dish(START_SEQ+10015, "Beafshteks", 33.0);
    public static final Dish dish17 = new Dish(START_SEQ+10016, "Hot dog", 23.0);
    public static final Dish dish18 = new Dish(START_SEQ+10017, "Pie", 17.0);
    public static final Dish dish19 = new Dish(START_SEQ+10018, "Tiramissu", 22.0);

    public static final List<Dish> dishes = List.of (
            dish01,
            dish02,
            dish03,
            dish04,
            dish05,
            dish06,
            dish07,
            dish08,
            dish09,
            dish10,
            dish11,
            dish12,
            dish13,
            dish14,
            dish15,
            dish16,
            dish17,
            dish18,
            dish19
    );
}