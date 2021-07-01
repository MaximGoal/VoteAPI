package com.example.votingsystem.repository;

import com.example.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepo extends JpaRepository<User, Long> {
}
