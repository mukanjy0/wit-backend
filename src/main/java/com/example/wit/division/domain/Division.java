package com.example.wit.division.domain;

import com.example.wit.contest.domain.Contest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Division {
    @Id
    private Short id;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer lowerBound;
    @Column(nullable = false)
    private Integer upperBound;
    @OneToMany(mappedBy = "division")
    Set<Category> category;
    @OneToMany(mappedBy = "division")
    Set<Contest> contests;
}
