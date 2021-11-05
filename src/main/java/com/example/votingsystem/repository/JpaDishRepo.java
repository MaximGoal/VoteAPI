package com.example.votingsystem.repository;

import com.example.votingsystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDishRepo extends JpaRepository<Dish, Integer> {
}
