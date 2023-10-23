package com.example.wit.team.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
//@SequenceGenerator(
//        name = "TeamSeq",
//        sequenceName = "TEAM_SEQ",
//        initialValue = 0,
//        allocationSize = 1
//)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 24, nullable = false)
    private String name;
    private Integer rank;
    private Integer rating;
}
