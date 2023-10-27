package com.example.wit.entities.player.domain;

import com.example.wit.entities.account.domain.Account;
import com.example.wit.entities.badge.domain.Badge;
import com.example.wit.entities.challenge.domain.bet.BetPlayer;
import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.challenge.domain.quest.QuestPlayer;
import com.example.wit.entities.challenge.domain.versus.Versus;
import com.example.wit.entities.submission.domain.Submission;
import com.example.wit.entities.team.domain.Team;
import com.example.wit.entities.university.domain.University;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
//public class Player implements UserDetails {
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 24, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Short currentCategory;
    @Column(nullable = false)
    private Short bestCategory;
    @ManyToOne(optional = false)
    Career career;
    @ManyToOne(optional = false)
    University university;
    @ManyToOne
    Team team;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer points;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer rating;
    private LocalDate registrationDate;
//    @Enumerated(EnumType.STRING)
//    private Role role;
    @OneToMany(mappedBy = "player")
    Set<Account> accounts;
    @OneToMany(mappedBy = "player")
    Set<Submission> submissions;
    @OneToMany(mappedBy = "player")
    Set<PlayerCardType> cards;
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
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority((role.name())));
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
