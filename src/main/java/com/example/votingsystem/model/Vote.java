package com.example.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.example.votingsystem.util.DateTimeUtil.patternDateTime;

@Entity
@Table(name = "votes")
@NoArgsConstructor
@Setter
@Getter
public class Vote {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = BaseEntity.START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

//    org.hibernate.AnnotationException: @Column(s) not allowed on a @ManyToOne property: com.example.votingsystem.model.Vote.user
//    @Column(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
//    @JsonBackReference
    protected User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    @JsonBackReference
    private Menu menu;

    @Column(name = "date_time")
    @DateTimeFormat(pattern = patternDateTime)
    protected LocalDateTime dateTime;

    public Vote(User user, Menu menu, LocalDateTime dateTime) {
        this.user = user;
        this.menu = menu;
        this.dateTime = dateTime;
    }

    public Vote(Integer id, User user, Menu menu, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.menu = menu;
        this.dateTime = dateTime.truncatedTo(ChronoUnit.SECONDS);
    }
}
