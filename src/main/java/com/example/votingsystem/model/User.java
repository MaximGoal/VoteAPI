package com.example.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    @Email
    @NotBlank
    private String email;

    @Size(min = 5, max = 20)
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    // enable @CollectionTable
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
//            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role")})
    @ElementCollection(fetch = FetchType.EAGER)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    @DateTimeFormat
    private Date registered = new Date();


    private boolean enabled = true;

    @OneToMany
    @JsonManagedReference
    private List<Vote> votes;

    public User(int id, String name, String email, String password, Role role, Role ... roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        EnumSet.of(role, roles);
        new Date();
    }

    public User(Integer id, String name, String email, String password, Set<Role> roles, Date registered, boolean enabled, List<Vote> votes) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.registered = registered;
        this.enabled = enabled;
        setVotes(votes);
    }

    public User (User u) {
        this(u.id, u.name, u.email, u.password, u.roles, u.registered, u.enabled, u.votes);
    }

    @Transactional
    // transfer vote function from User to Service
    public boolean vote (Restaurant restaurant) {
        LocalDateTime dateTime = LocalDateTime.now();

        // determine 11:00 vote deadline
        if (dateTime.isAfter(LocalDateTime.of(
                dateTime.getYear(),
                dateTime.getMonth(),
                dateTime.getDayOfMonth(),
                11, 0, 0))) {
            return false;
        }

        Vote vote = new Vote(null,
                this,
                restaurant.getMenu().get(dateTime.toLocalDate()),
                dateTime
        );
        return true;
    }

    public boolean isVoted (LocalDate date) {
        return votes.stream()
                .map(Vote::getDateTime)
                .map(LocalDateTime::toLocalDate)
                .anyMatch(date1 -> date1.isEqual(date));
    }

    @Transactional
    // transfer unvote function from User to Service
    public void unVote() {
    }

}
