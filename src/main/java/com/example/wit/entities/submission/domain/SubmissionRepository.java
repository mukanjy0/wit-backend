package com.example.wit.entities.submission.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Optional<Submission> findSubmissionByProblemIdAndPlayerIdAndDateTime(Long problemId, Long playerId, LocalDateTime dateTime);
}
