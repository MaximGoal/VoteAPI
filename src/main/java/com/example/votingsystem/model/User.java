package com.example.votingsystem.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class User extends BaseEntity {

    private boolean vote = false;

    public User(int id, String name) {
        super(id, name);
    }

    public boolean vote (Restaurant restaurant) {
        vote = true;
        return vote;
    }

    public void unVote() {
        vote = false;
    }

}
