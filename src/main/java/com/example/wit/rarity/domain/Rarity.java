package com.example.wit.rarity.domain;

import com.example.wit.badge.domain.Badge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Rarity {
    @Id
    @Column(length = 20)
    private String name;
    @Column(length = 6)
    private String color;
    @OneToMany(mappedBy = "rarity")
    Set<Badge> badges;
}
