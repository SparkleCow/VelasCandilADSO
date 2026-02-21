package com.velas.candil.repositories;

import com.velas.candil.entities.ActivateToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivateTokenRepository extends JpaRepository<ActivateToken, Long> {
}
