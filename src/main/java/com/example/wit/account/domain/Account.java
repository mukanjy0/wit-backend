package com.example.wit.account.domain;

import com.example.wit.platform.domain.Platform;
import com.example.wit.player.domain.Player;
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
    @Column(length = 24)
    private String handle;
    @ManyToOne
    Platform platform;
    @ManyToOne
    Player player;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer rating;
    @Column(nullable = false)
    private String url;
}
