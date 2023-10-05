package com.example.wit.submission.domain;

import com.example.wit.player.domain.Player;
import com.example.wit.problem.domain.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "submission")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Submission {
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "player_id", referencedColumnName = "id")
    Player player;
    @Id
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "problem_id", referencedColumnName = "id")
    Problem problem;
    @Id
    private LocalDateTime dateTime;
    private String result;
    private Boolean inPractice;
    @Column(columnDefinition = "TEXT")
    private String code;
}
