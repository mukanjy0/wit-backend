package com.example.wit.entities.player.domain;

import com.example.wit.entities.account.domain.Account;
import com.example.wit.entities.badge.domain.Badge;
import com.example.wit.entities.challenge.domain.bet.BetPlayer;
import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.challenge.domain.quest.QuestPlayer;
import com.example.wit.entities.challenge.domain.versus.Versus;
import com.example.wit.entities.contest.domain.Contest;
import com.example.wit.entities.player.domain.category.Category;
import com.example.wit.entities.player.domain.role.Role;
import com.example.wit.entities.submission.domain.Submission;
import com.example.wit.entities.team.domain.Team;
import com.example.wit.entities.university.domain.University;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Player implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 24, unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private Integer points;
    @Column(nullable = false)
    private Integer rating;
    private Category bestCategory;
    private Category currentCategory;
    private String avatarUrl;
    private LocalDate registrationDate;
    private Role role;
    @ManyToOne(optional = false)
    Team team;
    @ManyToOne(optional = false)
    Career career;
    @ManyToOne(optional = false)
    University university;

    @OneToMany(mappedBy = "player")
    Set<Account> accounts;
    @OneToMany(mappedBy = "player")
    Set<Submission> submissions;
    @OneToMany(mappedBy = "player")
    Set<Contest> contests;
    @OneToMany(mappedBy = "player")
    Set<PlayerCard> cards;
    @ManyToMany
    Set<Badge> badges;
    @OneToMany(mappedBy = "player")
    Set<BetPlayer> bets;
    @OneToMany(mappedBy = "challenger")
    Set<Versus> startedVersus;
    @OneToMany(mappedBy = "challenged")
    Set<Versus> acceptedVersus;
    @OneToMany(mappedBy = "player")
    Set<QuestPlayer> quests;
    @ManyToMany
    Set<Player> friend;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.role()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
