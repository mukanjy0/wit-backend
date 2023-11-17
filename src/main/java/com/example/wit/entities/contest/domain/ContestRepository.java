package com.example.wit.entities.contest.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
    @Query(value = "SELECT * FROM get_past_contests(:page, :size)", nativeQuery = true)
    List<Contest> findPastContests(Integer page, Integer size);
    @Query(value = "SELECT * FROM get_upcoming_contests(:page, :size)", nativeQuery = true)
    List<Contest> findUpcomingContests(Integer page, Integer size);
}
