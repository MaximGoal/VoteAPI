package com.example.votingsystem.testData;

import com.example.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.votingsystem.testData.RestaurantTestData.*;
import static com.example.votingsystem.model.BaseEntity.*;

public class MenuTestData {

    public final static Menu menu01 = new Menu(START_SEQ + 20, "Breakfast #1", restaurant01, LocalDate.now().plusDays(0));
    public final static Menu menu02 = new Menu(START_SEQ + 21, "Breakfast #2", restaurant02, LocalDate.now().plusDays(0));
    public final static Menu menu03 = new Menu(START_SEQ + 22, "Breakfast #3", restaurant03, LocalDate.now().plusDays(0));
    public final static Menu menu04 = new Menu(START_SEQ + 23, "Breakfast #4", restaurant04, LocalDate.now().plusDays(0));
    public final static Menu menu05 = new Menu(START_SEQ + 24, "Dinner #1", restaurant01, LocalDate.now().plusDays(1));
    public final static Menu menu06 = new Menu(START_SEQ + 25, "Dinner #2", restaurant02, LocalDate.now().plusDays(1));
    public final static Menu menu07 = new Menu(START_SEQ + 26, "Dinner #3", restaurant03, LocalDate.now().plusDays(1));
    public final static Menu menu08 = new Menu(START_SEQ + 27, "Dinner #4", restaurant04, LocalDate.now().plusDays(1));
    public final static Menu menu09 = new Menu(START_SEQ + 28, "5 oClock #1", restaurant01, LocalDate.now().plusDays(2));
    public final static Menu menu10 = new Menu(START_SEQ + 29, "5 oClock #2", restaurant02, LocalDate.now().plusDays(2));
    public final static Menu menu11 = new Menu(START_SEQ + 30, "5 oClock #3", restaurant03, LocalDate.now().plusDays(2));
    public final static Menu menu12 = new Menu(START_SEQ + 31, "5 oClock #4", restaurant04, LocalDate.now().plusDays(2));
    public final static Menu menu13 = new Menu(START_SEQ + 32, "Supper #1", restaurant01, LocalDate.now().plusDays(3));
    public final static Menu menu14 = new Menu(START_SEQ + 33, "Supper #2", restaurant02, LocalDate.now().plusDays(3));
    public final static Menu menu15 = new Menu(START_SEQ + 34, "Supper #3", restaurant03, LocalDate.now().plusDays(3));
    public final static Menu menu16 = new Menu(START_SEQ + 35, "Supper #4", restaurant04, LocalDate.now().plusDays(3));

    static {
        restaurant01.getMenus().addAll(Arrays.asList(menu01, menu05, menu09, menu13));
        restaurant02.getMenus().addAll(Arrays.asList(menu02, menu06, menu10, menu14));
        restaurant03.getMenus().addAll(Arrays.asList(menu03, menu07, menu11, menu15));
        restaurant04.getMenus().addAll(Arrays.asList(menu04, menu08, menu12, menu16));
    }

    public final static List<Menu> allMenus = List.of(menu01, menu02, menu03, menu04, menu05, menu06, menu07, menu08, menu09, menu10, menu11, menu12, menu13, menu14, menu15, menu16);
}
