package com.yachtclub.backend.services.interfaces;

import java.util.List;

import com.yachtclub.backend.dtos.EmployeeZoneDTO;

public interface EmployeeZoneService {
    List<EmployeeZoneDTO> getAll();

    EmployeeZoneDTO create(EmployeeZoneDTO dto);

    void delete(Long employeeId, Long zoneId);
}
