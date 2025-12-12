package com.yachtclub.backend.services.interfaces;

import com.yachtclub.backend.dtos.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> getAll();

    EmployeeDTO getById(Long id);

    EmployeeDTO create(EmployeeDTO dto);

    EmployeeDTO update(Long id, EmployeeDTO dto);

    void delete(Long id);

    Optional<EmployeeDTO> searchByUsername(String username);

    Optional<com.yachtclub.backend.entities.Employee> getByUsername(String username);

    java.util.List<com.yachtclub.backend.entities.EmployeeZone> getAssignedZones(Long id);
}
