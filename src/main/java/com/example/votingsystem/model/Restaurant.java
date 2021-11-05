package com.example.votingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Restaurant extends BaseEntity {

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
                mappedBy = "restaurant")
    private List <Menu> menus = new ArrayList<>();

    public List<Menu> getMenus() {
        return menus;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id.equals(that.id) && name.equals((that.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
