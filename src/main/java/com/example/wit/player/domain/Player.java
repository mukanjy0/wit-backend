package com.example.wit.player.domain;

import com.example.wit.account.domain.Account;
import com.example.wit.badge.domain.Badge;
import com.example.wit.challenge.domain.bet.BetPlayer;
import com.example.wit.career.domain.Career;
import com.example.wit.category.domain.Category;
import com.example.wit.challenge.domain.quest.QuestPlayer;
import com.example.wit.challenge.domain.versus.Versus;
import com.example.wit.submission.domain.Submission;
import com.example.wit.team.domain.Team;
import com.example.wit.university.domain.University;
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
//@SequenceGenerator(
//        name = "PlayerSeq",
//        sequenceName = "PLAYER_SEQ",
//        initialValue = 0,
//        allocationSize = 1
//)
public class Player implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = false)
    Category currentCategory;
    @ManyToOne(optional = false)
    Category bestCategory;
    @ManyToOne(optional = false)
    Career career;
    @ManyToOne(optional = false)
    University university;
    @ManyToOne
    Team team;
    @Column(length = 24, nullable = false)
    private String username;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer points;
    @Column(columnDefinition = "INTEGER DEFAULT 0")
    private Integer rating;
    private LocalDate registrationDate;
    @Enumerated(EnumType.STRING)
    private Role role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
