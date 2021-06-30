package com.example.votingsystem.model;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu extends BaseEntity {
    private List<Dish> dishes;

    public Menu() {
        dishes = new ArrayList<>();
    }

    public Menu(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }
}
