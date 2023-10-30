package com.example.wit.entities.reward.domain;

import com.example.wit.entities.challenge.domain.Challenge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer points;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer lowerBound;
    @Column(nullable = false)
    private Integer upperBound;
    @OneToMany(mappedBy = "reward")
    Set<RewardCardType> cards;
    @ManyToMany(mappedBy = "rewards")
    Set<Challenge> challenges;
}
