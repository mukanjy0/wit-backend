package com.example.wit.entities.platform.domain;

import com.example.wit.entities.account.domain.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(length = 24, unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String url;
    @OneToMany(mappedBy = "platform")
    Set<Account> accounts;
}
