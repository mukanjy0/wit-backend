package com.example.wit.entities.tag.domain;

import com.example.wit.entities.problem.domain.Problem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(length = 10, nullable = false, unique = true)
    private String acronym;
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    Set<Problem> problems;
}
