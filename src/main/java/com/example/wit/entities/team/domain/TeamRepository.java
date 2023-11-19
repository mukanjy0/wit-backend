package com.example.wit.entities.team.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findTeamByName(String name);
    Boolean existsTeamByName(String name);
    @Query(value = "SELECT get_team_rank(:id);", nativeQuery = true)
    Long getTeamRank(Long id);
}
