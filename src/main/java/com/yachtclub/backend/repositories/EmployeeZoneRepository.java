package com.yachtclub.backend.repositories;

import com.yachtclub.backend.entities.EmployeeZone;
import com.yachtclub.backend.entities.EmployeeZoneId;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeZoneRepository extends JpaRepository<EmployeeZone, EmployeeZoneId> {
    List<EmployeeZone> findByEmployeeId(Long employeeId);
}
