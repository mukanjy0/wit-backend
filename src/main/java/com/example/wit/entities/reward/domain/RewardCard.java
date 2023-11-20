package com.example.wit.entities.reward.domain;

import com.example.wit.entities.card.domain.Card;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RewardCard {
    @EmbeddedId
    RewardCardTypeId id;
    @ManyToOne
    @MapsId("rewardId")
    @JoinColumn(name = "reward_id", referencedColumnName = "id")
    Reward reward;
    @ManyToOne
    @MapsId("cardId")
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    Card card;
    @Column(nullable = false)
    private Short quantity;

    @Embeddable
    @Getter @Setter
    @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
    public class RewardCardTypeId implements Serializable {
        private Long rewardId;
        private Short cardId;
    }
}
