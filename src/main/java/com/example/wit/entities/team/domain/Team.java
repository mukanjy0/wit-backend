package com.example.wit.entities.team.domain;

import com.example.wit.entities.player.domain.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
//@NamedStoredProcedureQuery(name = "Team.getRank", procedureName = "get_team_rank", parameters = {
//        @StoredProcedureParameter(mode = ParameterMode.IN, name = "t_id", type = Long.class),
//        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "rank", type = Long.class),
//}
//)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 24, nullable = false)
    private String name;
    private Integer rating;
    @OneToMany(mappedBy = "team")
    Set<Player> players;
}
