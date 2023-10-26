package com.example.wit.entities.team.utils;

import com.example.wit.entities.team.domain.Team;
import com.example.wit.entities.team.dto.TeamRequest;
import org.springframework.stereotype.Component;

@Component
public class TeamUtils {
    public static Team updateTeam(Team original, TeamRequest toUpdate) {
        if (toUpdate.getName() != null) original.setName(toUpdate.getName());
        if (toUpdate.getRating() != null) original.setRating(toUpdate.getRating());

        return original;
    }
}
