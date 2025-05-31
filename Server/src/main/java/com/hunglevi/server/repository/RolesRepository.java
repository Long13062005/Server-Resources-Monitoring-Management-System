package com.hunglevi.server.repository;

import com.hunglevi.server.entities.Roles;
import com.hunglevi.server.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);

}
