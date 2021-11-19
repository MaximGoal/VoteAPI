package com.example.votingsystem.controller;

import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.RestaurantPage;
import com.example.votingsystem.service.VoteApiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class VoteRestController {

    private VoteApiService voteApiService;

    public VoteRestController(VoteApiService voteApiService) {
        this.voteApiService = voteApiService;
    }

    @GetMapping("/api")
    public ResponseEntity<Page<Restaurant>> getRestaurantsPage(RestaurantPage page) {
        return new ResponseEntity<>(voteApiService.getRestaurantsPageable(page), HttpStatus.OK);
    }

}
