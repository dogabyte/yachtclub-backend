package com.yachtclub.backend.repositories;

import com.yachtclub.backend.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByUsername(String username);

    boolean existsByUsername(String username);
}
