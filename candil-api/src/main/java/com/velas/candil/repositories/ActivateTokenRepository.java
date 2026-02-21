package com.velas.candil.repositories;

import com.velas.candil.entities.ActivateToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivateTokenRepository extends JpaRepository<ActivateToken, Long> {
    Optional<ActivateToken> findByToken(String token);
}
