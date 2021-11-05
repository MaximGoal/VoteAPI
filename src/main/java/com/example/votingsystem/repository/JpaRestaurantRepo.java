package com.example.votingsystem.repository;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface JpaRestaurantRepo extends JpaRepository<Restaurant, Integer> {
}
