package com.example.wit.entities.player.domain;

import com.example.wit.entities.card.domain.Card;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PlayerCard{
    @EmbeddedId
    PlayerCardId id;
    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    Player player;
    @ManyToOne
    @MapsId("cardId")
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    Card card;
    private Short quantity;

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
    static public class PlayerCardId implements Serializable {
        private Long playerId;
        private Long cardId;
    }
}
