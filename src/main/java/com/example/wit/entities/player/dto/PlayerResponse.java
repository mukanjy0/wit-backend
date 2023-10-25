package com.example.wit.entities.player.dto;

import com.example.wit.entities.account.domain.Account;
import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.player.domain.Role;
import com.example.wit.entities.team.domain.Team;
import com.example.wit.entities.university.domain.University;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PlayerResponse {
    private String username;
    private Integer points;
    private Integer rating;
    private Short currentCategory;
    private Short bestCategory;
    private LocalDate registrationDate;
    private Short careerId;
    private Short universityId;
    private Long teamId;
}
