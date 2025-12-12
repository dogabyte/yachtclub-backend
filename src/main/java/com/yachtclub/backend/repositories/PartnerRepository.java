package com.yachtclub.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yachtclub.backend.entities.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByUsername(String username);

    boolean existsByDni(String dni);

    boolean existsByUsername(String username);
}
