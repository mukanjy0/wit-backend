package com.example.wit.entities.player.utils;

import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerRepository;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.web.header.writers.CrossOriginEmbedderPolicyHeaderWriter;
import org.springframework.stereotype.Component;

@Component
public class PlayerUtils {
    @Autowired
    private PlayerRepository repository;
    @Autowired

    public static Player updatePlayer(Player original, PlayerUpdate toUpdate) {
       if (toUpdate.getPoints() != null) original.setPoints(toUpdate.getPoints());
       if (toUpdate.getRating() != null) original.setRating(toUpdate.getRating());
       if (toUpdate.getCurrentCategory() != null) original.setCurrentCategory(toUpdate.getCurrentCategory());
       if (toUpdate.getBestCategory() != null) original.setBestCategory(toUpdate.getBestCategory());
       return original;
    }
}
