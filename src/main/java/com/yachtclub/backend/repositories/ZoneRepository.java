package com.yachtclub.backend.repositories;

import com.yachtclub.backend.entities.Zone;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Optional<Zone> findByName(String name);

    void deleteByName(String name);
}
