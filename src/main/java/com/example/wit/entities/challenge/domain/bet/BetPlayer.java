package com.example.wit.entities.challenge.domain.bet;

import com.example.wit.entities.player.domain.Player;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BetPlayer {
    @EmbeddedId
    BetPlayerId id;
    @Column(nullable = false)
    private Integer amount;
    @ManyToOne
    @MapsId("betId")
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    Bet bet;
    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    Player player;
    @Embeddable
    @Getter @Setter @EqualsAndHashCode @NoArgsConstructor @AllArgsConstructor
    static public class BetPlayerId implements Serializable {
        private Long betId;
        private Long playerId;
    }
}
