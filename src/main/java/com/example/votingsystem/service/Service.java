package com.example.votingsystem.service;

import com.example.votingsystem.repository.JpaDishRepo;
import com.example.votingsystem.repository.JpaRestaurantRepo;
import com.example.votingsystem.repository.JpaUserRepo;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    private JpaDishRepo dishRepo;

    private JpaRestaurantRepo restaurantRepo;

    private JpaUserRepo userRepo;

    @Autowired
    public Service(JpaDishRepo dishRepo, JpaRestaurantRepo restaurantRepo, JpaUserRepo userRepo) {
        this.dishRepo = dishRepo;
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
    }

    public boolean saveVote() {
        return false;
    }


}
