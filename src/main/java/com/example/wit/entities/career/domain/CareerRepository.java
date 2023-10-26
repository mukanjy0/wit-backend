package com.example.wit.entities.career.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CareerRepository extends JpaRepository<Career, Short> {
    Optional<Career> findCareerByName(String name);
    Boolean existsCareerByName(String name);
}
