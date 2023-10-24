package com.example.wit.entities.tag.domain;

import com.example.wit.entities.problem.domain.Problem;
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
    @Column(length = 10)
    private String acronym;
    @Column(length = 50, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "tags")
    Set<Problem> problems;
}
