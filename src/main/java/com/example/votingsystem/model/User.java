package com.example.votingsystem.model;

import com.example.votingsystem.service.VoteApiService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

import static com.example.votingsystem.util.DateTimeUtil.patternDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
public class User extends BaseEntity {

    @Email
    @NotBlank
    private String email;

    @Size(min = 5, max = 20)
    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role")})
    @Column(name = "role") // from table "user_roles"
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    @DateTimeFormat(pattern = patternDate)
    private LocalDate registered = LocalDate.now();

    private boolean enabled = true;

    public String getName() {return this.name;}

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
                mappedBy = "user")
    private List<Vote> votes = new ArrayList<>();

    @Transient
    private VoteApiService voteApiService = new VoteApiService();

    public User(int id, String name, String email, String password, Role role, Role ... roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        EnumSet.of(role, roles);
        new Date();
    }

    public User(Integer id, String name, String email, String password, Set<Role> roles, LocalDate registered, boolean enabled) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.registered = registered;
        this.enabled = enabled;
    }

    public User (User u) {
        this(u.id, u.name, u.email, u.password, u.roles, u.registered, u.enabled);
    }

//    public void vote(Restaurant restaurant) {
//        voter.vote(this, restaurant);
//    }

//    public void unvote(Restaurant restaurant) {
//        voter.unVote(restaurant, LocalDateTime.now(), this);
//    }

    public boolean isVoted (LocalDate date) {
        return voteApiService.getVotes().stream()
                .map(Vote::getDate)
//                .map(LocalDateTime::toLocalDate)
                .anyMatch(date1 -> date1.isEqual(date));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(registered, user.registered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, roles, registered, enabled);
    }
}
