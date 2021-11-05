package com.example.votingsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

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

    //    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dishes")
    private Set<Menu> menus = new HashSet<>();

    @Transactional
    public void addMenusToDish(Set<Menu> menus) {
        this.menus.addAll(menus);
    }

    @Override
    public String toString() {
        return "Dish{" +
//                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
