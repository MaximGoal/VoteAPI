package com.example.votingsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
@NoArgsConstructor
@Getter
@Setter
public class Dish extends BaseEntity {

    protected Double price;

    public Dish(int id, String name, Double price) {
        super(id, name);
        this.price = price;
    }

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonBackReference
//    private Menu menu;

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
