package com.example.votingsystem.repository;

import com.example.votingsystem.model.Menu;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaVoteRepo extends JpaRepository<Vote, Integer> {

    List<Vote> findVotesByUser(User user);

    Vote findVotesById(Integer id);

    Vote findVotesByMenuAndUser(Menu menu, User user);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Vote v where v.id = ?1")
    @Transactional
    void deleteVoteById(Integer id);

    @Modifying
    @Transactional
    void deleteVoteByDateTimeAndAndMenu(LocalDateTime dateTime, Menu menu);

    @Modifying
    @Query("update Vote v set v.user.id =?1, v.menu.id =?2, v.dateTime =?3 where v.id = ?4")
    @Transactional
    void updateVote(Integer userId, Integer menuId, LocalDateTime dateTime, Integer voteId);

}
