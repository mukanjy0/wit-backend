package com.example.wit.entities.player.utils;

import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.player.domain.Player;
import com.example.wit.entities.player.domain.PlayerRepository;
import com.example.wit.entities.player.domain.category.Category;
import com.example.wit.entities.player.dto.PlayerResponse;
import com.example.wit.entities.player.dto.PlayerUpdate;
import com.example.wit.entities.team.domain.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
//import org.springframework.security.web.header.writers.CrossOriginEmbedderPolicyHeaderWriter;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class PlayerUtils {
    public static Player updatePlayer(Player original, PlayerUpdate toUpdate) {
       if (toUpdate.getUsername() != null) original.setUsername(toUpdate.getUsername());
       if (toUpdate.getPoints() != null) original.setPoints(toUpdate.getPoints());
       if (toUpdate.getRating() != null) original.setRating(toUpdate.getRating());
       if (toUpdate.getCurrentCategoryId() != null) original.setCurrentCategory(Stream.of(Category.values())
               .filter(cat -> cat.id().equals(toUpdate.getCurrentCategoryId()))
               .findFirst()
               .orElseThrow(IllegalArgumentException::new));
       if (toUpdate.getAvatarUrl() != null) original.setAvatarUrl(toUpdate.getAvatarUrl());

       return original;
    }
}
