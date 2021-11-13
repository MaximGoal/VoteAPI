package com.example.votingsystem.testData;

import com.example.votingsystem.model.Role;
import com.example.votingsystem.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.example.votingsystem.model.BaseEntity.START_SEQ;

public class UserTestData {

    public static final int USER_ID = START_SEQ;

    public final static User admin = new User(START_SEQ+1, "Admin", "admin@mail.com", "password", Set.of(Role.ADMIN), LocalDate.now(), true);
    public final static User user1 = new User(START_SEQ+2, "Lakomka", "user01@mail.com", "password", Set.of(Role.USER), LocalDate.now(), true);
    public final static User user2 = new User(START_SEQ+3, "Biruk", "user02@mail.com", "password", Set.of(Role.USER), LocalDate.now(), true);
    public final static User user3 = new User(START_SEQ+3, "Borey", "user03@mail.com", "password", Set.of(Role.USER), LocalDate.now(), true);
    public final static User user4 = new User(START_SEQ+3, "Dobrinja", "user04@mail.com", "password", Set.of(Role.USER), LocalDate.now(), true);


    public final static List<User> users = List.of(admin, user1, user2, user3, user4);
}
