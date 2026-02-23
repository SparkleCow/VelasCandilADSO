package com.velas.candil.repositories;

import com.velas.candil.entities.user.Role;
import com.velas.candil.models.user.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleEnum name);
}
