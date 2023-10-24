package com.example.wit.player.domain;

import com.example.wit.cardtype.domain.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PlayerCardType {
    @EmbeddedId
    PlayerCardTypeId id;
    @ManyToOne
    @MapsId("playerId")
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    Player player;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "card_type_name", referencedColumnName = "name"),
        @JoinColumn(name = "card_type_suit", referencedColumnName = "suit")
    })
    CardType cardType;
    @Column(columnDefinition = "SMALLINT DEFAULT 1")
    private Short quantity;

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
    static public class PlayerCardTypeId implements Serializable {
        private Long playerId;
        @Column(length = 20, name = "card_type_name", insertable = false, updatable = false)
        private String cardTypeName;
        @Column(length = 20, name = "card_type_suit",insertable = false, updatable = false)
        private String cardTypeSuit;
    }
}
