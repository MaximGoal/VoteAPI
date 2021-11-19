package com.example.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "menus")
@Getter
@Setter
public class Menu extends BaseEntity {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Restaurant restaurant;

    @DateTimeFormat
    private LocalDate date;

    //    @Transient
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "menus_dishes",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = {@JoinColumn(name = "dish_id", referencedColumnName = "id")})
    private Set<Dish> dishes = new HashSet<>();

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @JsonManagedReference
//    private List<Vote> votes;

    public Menu() {
    }

    public Menu(Integer id, String name, Set<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public Menu(Integer id, String name, Restaurant restaurant, LocalDate date) {
        super(id, name);
        this.restaurant = restaurant;
        this.date = date;
    }

    public Double menuPrice() {
        return dishes.stream().map(Dish::getPrice).reduce(0.0, Double::sum);
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    @Transactional
    public void addDishesToMenu(Set<Dish> dishes) {
        this.dishes.addAll(dishes);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                '}';
    }
}
