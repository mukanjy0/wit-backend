package com.example.wit.entities.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByHandleAndPlatformId(String handle, Short platformId);
    Boolean existsAccountByHandleAndPlatformId(String handle, Short platformId);
    Boolean existsAccountByPlayerIdAndPlatformId(Long playerId, Short platformId);
}
