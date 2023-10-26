package com.example.wit.entities.university.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Short> {
    Optional<University> findUniversityByFullName(String fullName);
    Boolean existsUniversityByFullName(String fullName);
    Boolean existsUniversityByAcronym(String acronym);
}
