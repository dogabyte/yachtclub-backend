package com.yachtclub.backend.repositories;

import com.yachtclub.backend.entities.Boat;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {
    List<Boat> findByOwnerId(Long ownerId);

    boolean existsByVesselNumber(String vesselNumber);

    Optional<Boat> findByVesselNumber(String vesselNumber);
}
