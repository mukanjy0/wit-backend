package com.example.wit.entities.challenge.domain.versus;

import com.example.wit.entities.challenge.domain.Challenge;
import com.example.wit.entities.player.domain.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Versus {
    @Id
    private Long id;
    @ManyToOne
    Player challenger;
    @ManyToOne
    Player challenged;
    private Boolean challengerWin;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    Challenge challenge;
}
