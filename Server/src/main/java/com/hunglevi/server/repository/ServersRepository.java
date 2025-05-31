package com.hunglevi.server.repository;

import com.hunglevi.server.entities.Servers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServersRepository extends JpaRepository<Servers, Long> {
}
