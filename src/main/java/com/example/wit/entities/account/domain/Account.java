package com.example.wit.entities.account.domain;

import com.example.wit.entities.platform.domain.Platform;
import com.example.wit.entities.player.domain.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 24, nullable = false)
    private String handle;
    @ManyToOne(optional = false)
    Platform platform;
    @ManyToOne(optional = false)
    Player player;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer rating;
    @Column(nullable = false)
    private String url;
}
