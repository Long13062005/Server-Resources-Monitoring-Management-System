package com.hunglevi.server.repository;

import com.hunglevi.server.entities.AlertRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRulesRepository extends JpaRepository<AlertRules, Long> {
}
