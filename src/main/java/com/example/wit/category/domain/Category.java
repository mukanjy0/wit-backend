package com.example.wit.category.domain;

import com.example.wit.division.domain.Division;
import com.example.wit.player.domain.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.atn.BlockEndState;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    @Column(length = 24)
    private String name;
    @ManyToOne(optional = false)
    Division division;
    @Column(length = 6, nullable = false)
    private String color;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer lowerBound;
    @Column(nullable = false)
    private Integer upperBound;
    @OneToMany(mappedBy = "currentCategory")
    Set<Player> players;
    @OneToMany(mappedBy = "bestCategory")
    Set<Player> playersBest;
}
