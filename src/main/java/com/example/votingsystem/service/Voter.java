package com.example.votingsystem.service;

import com.example.votingsystem.exception.TimeException;
import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.Vote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Getter
@NoArgsConstructor
public class Voter {

    private VoteDAO voteDAO;

    private static List<Vote> votes;

    @Autowired
    public Voter(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    public List<Vote> getVotes() {
        return votes = voteDAO.getAllVotes();
    }

    public List<User> getAllUsers() {
        return voteDAO.getAllUsers();
    }

    public User getUserById(Integer id) {return voteDAO.getUserById(id); }

    public List<Restaurant> getRestaurants() { return voteDAO.getAllRestaurants(); }

    public Menu getMenuByDateAndRestaurant(LocalDate date, Restaurant restaurant) {
        return voteDAO.getMenuByDateAndRestaurant(date, restaurant);
    }

    @Transactional
    public boolean vote (Integer userId, Integer menuId) {
        Vote vote = getVoteByUserIdAndMenuId(userId, menuId);
        voteDAO.saveVote(vote);
        return true;
    }

    @Transactional
    public boolean unVote(Integer userId, Integer menuId) {
//        Vote vote = voteDAO.deleteVoteById();
//        voteDAO.deleteVoteById(vote.getId());
        return true;
    }

    public Vote getVoteByUserIdAndMenuId(Integer userId, Integer menuId) {
        LocalDateTime dateTime = LocalDateTime.now();

        User user = voteDAO.getUserById(userId);
        Restaurant restaurant = voteDAO.getMenuById(menuId).getRestaurant();

        // determine 11:00 vote deadline - check time before release !!!
        checkDateTime(dateTime);

        return new Vote(
                user,
                restaurant.getMenus().stream()
                        .filter(a -> a.getDate().isEqual(dateTime.toLocalDate()))
                        .findAny().get(),
                dateTime
        );
    }

    public boolean checkDateTime(LocalDateTime dateTime) {
        // determine 11:00 vote deadline - check time before release !!!
        if (dateTime.isAfter(LocalDateTime.of(
                dateTime.getYear(),
                dateTime.getMonth(),
                dateTime.getDayOfMonth(),
                22, 0, 0))) {
            throw new TimeException("Time to vote expired. It is already " + dateTime.toLocalTime() + " now.");
        }
        else return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voter voter = (Voter) o;
        return Objects.equals(voteDAO, voter.voteDAO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voteDAO);
    }
}
