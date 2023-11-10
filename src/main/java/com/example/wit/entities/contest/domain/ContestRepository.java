package com.example.wit.entities.contest.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {
}
