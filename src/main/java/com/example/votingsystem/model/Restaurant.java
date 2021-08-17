package com.example.votingsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
//@Table(name = "restaurants")
@NoArgsConstructor
@Getter
public class Restaurant extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Map<LocalDate, Menu> menu = new HashMap<>();

//    @OneToMany
//    private Map<LocalDate, List<Vote>> votes;


}
