package com.yachtclub.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import com.yachtclub.backend.entities.Mooring;

import java.util.List;

@Repository
public interface MooringRepository extends JpaRepository<Mooring, Long> {

    @Query("SELECT m FROM Mooring m WHERE m.zone.name = :zoneName")
    List<Mooring> findByZoneName(@Param("zoneName") String zoneName);

    @Query("SELECT m FROM Mooring m WHERE m.boat.owner.id = :ownerId")
    List<Mooring> findByOwnerId(@Param("ownerId") Long ownerId);

    void deleteById(@NonNull Long id);

}
