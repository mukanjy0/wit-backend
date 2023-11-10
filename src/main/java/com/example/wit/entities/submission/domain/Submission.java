package com.example.wit.entities.submission.domain;

import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.problem.domain.Problem;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    Problem problem;
    @ManyToOne(optional = false)
    Player player;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    private String result;
    @Column(nullable = false)
    private Boolean inPractice;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String code;
}
