package com.example.wit.entities.platform.domain;

import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Short> {
    Optional<Platform> findPlatformByName(String name);
    Boolean existsPlatformByName(String name);
    Boolean existsPlatformByUrl(String url);
}
