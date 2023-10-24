package com.example.wit.entities.player.dto;

import com.example.wit.entities.account.domain.Account;
import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.player.domain.Role;
import com.example.wit.entities.team.domain.Team;
import com.example.wit.entities.university.domain.University;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Accessors(fluent = true)
@Getter @Setter @RequiredArgsConstructor
public class PlayerResponse {
    final private String username;
    final private Integer points;
    final private Integer rating;
    final private Short currentCategory;
    final private Short bestCategory;
    final private LocalDate registrationDate;
    final private Short careerId;
    final private Short universityId;
    final private Long teamId;
}
