package com.example.wit.reward.domain;

import com.example.wit.challenge.domain.Challenge;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer points;
    private Integer lowerBound;
    private Integer upperBound;
    @OneToMany(mappedBy = "reward")
    Set<RewardCardType> cards;
    @ManyToMany(mappedBy = "rewards")
    Set<Challenge> challenges;
}
