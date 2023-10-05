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
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "platform_name", referencedColumnName = "name")
    Platform platform;
    @Id
    @Column(length = 24)
    private String handle;
    @ManyToOne
    Player player;
    private Integer rating;
    private String url;
}
