package com.example.wit.entities.contest.domain;

import com.example.wit.entities.challenge.domain.bet.Bet;
import com.example.wit.entities.contest.domain.division.Division;
import com.example.wit.entities.problem.domain.Problem;
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
    Division division;
    @Column(nullable = false)
    private LocalDateTime startDatetime;
    @Column(nullable = false)
    private LocalDateTime endDatetime;
    private Integer durationMilliseconds;
    private String editorialUrl;
    @ManyToMany
    @JoinTable(name = "contest_problem",
            joinColumns = @JoinColumn(name = "contest_id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id"))
    Set<Problem> problems;
    @OneToMany(mappedBy = "contest")
    Set<Bet> bets;
}
