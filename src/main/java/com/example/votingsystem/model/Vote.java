package com.example.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "votes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vote {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = BaseEntity.START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

//    @Column("user_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    protected User user;

////    @Column("restaurant_id")
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    protected Restaurant restaurant;

//    @Column("menu_id")
    @ManyToOne
    @JsonBackReference
    private Menu menu;

//    @Column("date_time")
    @DateTimeFormat
    protected LocalDateTime dateTime;



}
