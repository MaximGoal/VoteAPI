package com.example.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.example.votingsystem.util.DateTimeUtil.patternDate;
import static com.example.votingsystem.util.DateTimeUtil.patternDateTime;

@Entity
@Table(name = "votes")
@NoArgsConstructor
@Setter
@Getter
public class Vote {

    @Id
    @SequenceGenerator(name = "vote_seq", sequenceName = "vote_seq", allocationSize = 1, initialValue = BaseEntity.START_VOTE_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
    protected Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
//    @JsonBackReference
    protected User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;

    @Column(name = "date")
    @DateTimeFormat(pattern = patternDate)
    protected LocalDate date;

    public Vote(User user, Menu menu, LocalDate date) {
        this.user = user;
        this.menu = menu;
        this.date = date;
    }

    public Vote(Integer id, User user, Menu menu, LocalDate date) {
        this.id = id;
        this.user = user;
        this.menu = menu;
        this.date = date;
    }
}
