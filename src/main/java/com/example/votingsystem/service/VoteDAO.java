package com.example.votingsystem.service;

import com.example.votingsystem.model.*;
import com.example.votingsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service("voteService")
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VoteDAO {

    private final JpaDishRepo dishRepo;

    private final JpaRestaurantRepo restaurantRepo;

    private final JpaUserRepo userRepo;

    private final JpaMenuRepo menuRepo;

    private final JpaVoteRepo voteRepo;

    @Autowired
    public VoteDAO(JpaDishRepo dishRepo, JpaRestaurantRepo restaurantRepo, JpaUserRepo userRepo, JpaMenuRepo menuRepo, JpaVoteRepo voteRepo) {
        this.dishRepo = dishRepo;
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
        this.menuRepo = menuRepo;
        this.voteRepo = voteRepo;
    }

    public List<Dish> getAllDishes(){return dishRepo.findAll();}

    public List<Restaurant> getAllRestaurants(){return restaurantRepo.findAll();}

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Integer id) { return userRepo.getById(id);
    }

    public List<Vote> getAllVotes() {
        return voteRepo.findAll();
    }

    public Vote getVoteById(Integer id) {return voteRepo.findVotesById(id);}

    public Vote getVoteByUserAndDate(User user, LocalDate date) {
        return new Vote();
    }

    public Menu getMenuByDateAndRestaurant(LocalDate date, Restaurant restaurant) {
        return menuRepo.findMenuByDateAndRestaurant(date, restaurant);
    }

    public Menu getMenuById(Integer id) {
        return menuRepo.getById(id);
    }

    public Vote saveVote(Vote vote) {
        return voteRepo.save(vote);
    }

    @Transactional
    public void saveVoteById(Integer id, Vote vote) {
        Vote v = voteRepo.findVotesById(id);
        if (v != null) {
            voteRepo.updateVote(
                        vote.getUser().getId(),
                        vote.getMenu().getId(),
                        vote.getDateTime(),
                        vote.getId());
        } else voteRepo.save(vote);
    }

    public List<Vote> getUserVotes(User user) {
        return voteRepo.findVotesByUser(user);
    }

    @Transactional
    public void deleteVoteById(Integer id) {
        voteRepo.deleteVoteById(id);
    }

    @Transactional
    public void deleteVoteByMenuAndUser(Menu menu, User user) {
        voteRepo.findVotesByMenuAndUser(menu, user);
    }

    public Menu getMenuByDateAndRestaurant(LocalDateTime dateTime, Restaurant restaurant) {
        return menuRepo.findMenuByDateAndRestaurant(dateTime.toLocalDate(), restaurant);
    }
}