package com.example.votingsystem.repository;

import com.example.votingsystem.model.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRestaurantRepoPageable extends PagingAndSortingRepository<Restaurant, Integer> {
}
