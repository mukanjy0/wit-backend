package com.example.wit.entities.challenge.domain.bet;

import com.example.wit.entities.challenge.domain.Challenge;
import com.example.wit.entities.contest.domain.Contest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Bet {
    @Id
    private Long id;
    @Column(nullable = false)
    private Integer pool;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    Challenge challenge;
    @ManyToOne
    Contest contest;
    @OneToMany(mappedBy = "bet")
    Set<BetPlayer> players;
}
