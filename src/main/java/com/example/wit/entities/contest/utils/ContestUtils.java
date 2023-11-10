package com.example.wit.entities.contest.utils;

import com.example.wit.entities.contest.domain.Contest;
import com.example.wit.entities.contest.domain.division.Division;
import com.example.wit.entities.contest.dto.ContestRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Component
public class ContestUtils {
    public static Contest updateContest (Contest original, ContestRequest toUpdate) {
        Character divisionId = toUpdate.getDivision();
        if (divisionId != null) {
             Division division = Stream.of(Division.values())
                     .filter(div -> div.id().equals(divisionId))
                     .findFirst()
                     .orElseThrow(IllegalArgumentException::new);
             original.setDivision(division);
        }

        LocalDateTime start = toUpdate.getStartDatetime();
        if (start != null) original.setStartDatetime(start);

        LocalDateTime end = toUpdate.getEndDatetime();
        if (end != null) original.setEndDatetime(end);

        Integer seconds = toUpdate.getDurationMilliseconds();
        if (seconds != null) original.setDurationMilliseconds(seconds);

        String editorialUrl = toUpdate.getEditorialUrl();
        if (editorialUrl != null) original.setEditorialUrl(editorialUrl);

        return original;
    }
}
