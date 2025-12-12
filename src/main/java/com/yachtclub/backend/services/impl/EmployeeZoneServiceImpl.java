package com.yachtclub.backend.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.yachtclub.backend.dtos.EmployeeZoneDTO;
import com.yachtclub.backend.entities.Employee;
import com.yachtclub.backend.entities.EmployeeZone;
import com.yachtclub.backend.entities.EmployeeZoneId;
import com.yachtclub.backend.entities.Zone;
import com.yachtclub.backend.mappers.EmployeeZoneMapper;
import com.yachtclub.backend.repositories.EmployeeRepository;
import com.yachtclub.backend.repositories.EmployeeZoneRepository;
import com.yachtclub.backend.repositories.ZoneRepository;
import com.yachtclub.backend.services.interfaces.EmployeeZoneService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeZoneServiceImpl implements EmployeeZoneService {

    private final EmployeeZoneRepository employeeZoneRepository;
    private final EmployeeZoneMapper employeeZoneMapper;
    private final EmployeeRepository employeeRepository;
    private final ZoneRepository zoneRepository;

    @Override
    public List<EmployeeZoneDTO> getAll() {
        return employeeZoneMapper.toDtoList(employeeZoneRepository.findAll());
    }

    @Override
    public EmployeeZoneDTO create(EmployeeZoneDTO dto) {
        EmployeeZone entity = employeeZoneMapper.toEntity(dto);

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Zone zone = zoneRepository.findById(dto.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        entity.setEmployee(employee);
        entity.setZone(zone);

        return employeeZoneMapper.toDTO(employeeZoneRepository.save(entity));
    }

    @Override
    public void delete(Long employeeId, Long zoneId) {
        EmployeeZoneId id = new EmployeeZoneId(zoneId, employeeId);
        if (!employeeZoneRepository.existsById(id)) {
            throw new RuntimeException("EmployeeZone not found");
        }
        employeeZoneRepository.deleteById(id);
    }

}
