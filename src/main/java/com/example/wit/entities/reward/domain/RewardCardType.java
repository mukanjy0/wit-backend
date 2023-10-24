package com.example.wit.entities.reward.domain;

import com.example.wit.entities.cardtype.domain.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RewardCardType {
    @EmbeddedId
    RewardCardTypeId id;
    @ManyToOne
    @MapsId("rewardId")
    @JoinColumn(name = "reward_id", referencedColumnName = "id")
    Reward reward;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "card_type_name", referencedColumnName = "name", insertable = false, updatable = false),
        @JoinColumn(name = "card_type_suit", referencedColumnName = "suit", insertable = false, updatable = false)
    })
    CardType cardType;
    @Column(nullable = false)
    private Short quantity;

    @Embeddable
    public class RewardCardTypeId implements Serializable {
        @Column(name = "reward_id")
        private Long rewardId;
        @Column(name = "card_type_name", length = 20)
        private String cardTypeName;
        @Column(name = "card_type_suit", length = 20)
        private String cardTypeSuit;
    }
}
