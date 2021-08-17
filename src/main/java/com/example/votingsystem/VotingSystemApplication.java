package com.example.votingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VotingSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(VotingSystemApplication.class, args);

        System.out.println("Voting system was started.");

    }

}
