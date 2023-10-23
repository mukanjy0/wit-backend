package com.example.wit.contest.domain;

import com.example.wit.challenge.domain.bet.Bet;
import com.example.wit.division.domain.Division;
import com.example.wit.problem.domain.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
//@SequenceGenerator(
//        name = "ContestSeq",
//        sequenceName = "CONTEST_SEQ",
//        initialValue = 0,
//        allocationSize = 1
//)
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ContestSeq")
    private Long id;
    private Character division;
    @Column(nullable = false)
    private LocalDateTime start_datetime;
    @Column(nullable = false)
    private LocalDateTime end_datetime;
    @Column(length = 10)
    private String duration;
    private String editorialUrl;
    @ManyToMany
    Set<Problem> problems;
    @OneToMany(mappedBy = "contest")
    Set<Bet> bets;
}
