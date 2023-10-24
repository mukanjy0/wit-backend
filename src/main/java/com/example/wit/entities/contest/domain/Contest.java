package com.example.wit.contest.domain;

import com.example.wit.challenge.domain.bet.Bet;
import com.example.wit.problem.domain.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Character division;
    @Column(nullable = false)
    private LocalDateTime start_datetime;
    @Column(nullable = false)
    private LocalDateTime end_datetime;
    private Integer durationSeconds;
    private String editorialUrl;
    @ManyToMany
    Set<Problem> problems;
    @OneToMany(mappedBy = "contest")
    Set<Bet> bets;
}
