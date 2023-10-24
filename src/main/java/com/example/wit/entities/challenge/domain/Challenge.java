package com.example.wit.challenge.domain;

import com.example.wit.challenge.domain.bet.Bet;
import com.example.wit.career.domain.Career;
import com.example.wit.challenge.domain.quest.Quest;
import com.example.wit.challenge.domain.versus.Versus;
import com.example.wit.reward.domain.Reward;
import com.example.wit.rule.domain.Rule;
import com.example.wit.university.domain.University;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer lowerBound;
    @Column(nullable = false)
    private Integer upperBound;
    @ManyToMany
    Set<Reward> rewards;
    @ManyToMany
    Set<Rule> rules;
    @ManyToMany
    Set<Career> careers;
    @ManyToMany
    Set<University> universities;
    @OneToOne(mappedBy = "challenge", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Bet bet;
    @OneToOne(mappedBy = "challenge", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Versus versus;
    @OneToOne(mappedBy = "challenge", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Quest quest;
}
