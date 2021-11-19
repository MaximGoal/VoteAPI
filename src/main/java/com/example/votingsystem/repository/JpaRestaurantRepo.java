package com.example.votingsystem.repository;

import com.example.votingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRestaurantRepo extends JpaRepository<Restaurant, Integer> {
}
