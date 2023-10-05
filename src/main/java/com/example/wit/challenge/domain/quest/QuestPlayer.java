package com.example.wit.challenge.domain.quest;

import com.example.wit.player.domain.Player;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class QuestPlayer {
    @EmbeddedId
    QuestPlayerId id;
    @Column(length = 20)
    private String status;
    @ManyToOne
    @MapsId("questId")
    @JoinColumn(name = "quest_id", referencedColumnName = "id")
    Player player;
    @ManyToOne
    @MapsId("questId")
    @JoinColumn(name = "quest_id", referencedColumnName = "id")
    Quest quest;

    @Embeddable
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
    static public class QuestPlayerId implements Serializable {
        @Column(name = "quest_id")
        private Long questId;
        @Column(name = "player_id")
        private Long plyaerId;
    }
}
