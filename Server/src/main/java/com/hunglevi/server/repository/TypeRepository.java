package com.hunglevi.server.repository;

import com.hunglevi.server.entities.Level;
import com.hunglevi.server.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
}
