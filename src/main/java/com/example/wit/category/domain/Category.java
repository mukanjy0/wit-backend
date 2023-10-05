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
    @ManyToOne
    Division division;
    @Column(length = 6)
    private String color;
    private Integer lowerBound;
    private Integer upperBound;
    @OneToMany(mappedBy = "currentCategory")
    Set<Player> players;
    @OneToMany(mappedBy = "bestCategory")
    Set<Player> playersBest;
}
