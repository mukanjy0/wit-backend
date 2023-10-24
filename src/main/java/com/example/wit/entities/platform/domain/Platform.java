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
    @Column(length = 24)
    private String name;
    @Column(nullable = false)
    private String url;
    @OneToMany(mappedBy = "platform")
    Set<Account> accounts;
}
