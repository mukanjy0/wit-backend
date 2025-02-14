package com.example.wit.entities.player.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Boolean existsPlayerByUsername(String username);
    Boolean existsPlayerByEmail(String email);
    Optional<Player> findPlayerByUsername(String username);
}
