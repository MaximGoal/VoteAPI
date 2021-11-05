package com.example.votingsystem.repository;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface JpaMenuRepo extends JpaRepository<Menu, Integer> {

    Menu findMenuByDateAndRestaurant(LocalDate date, Restaurant restaurant);
}
