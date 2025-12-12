package com.yachtclub.backend.services.impl;

import com.yachtclub.backend.dtos.EmployeeDTO;
import com.yachtclub.backend.entities.Employee;
import com.yachtclub.backend.entities.EmployeeZone;
import com.yachtclub.backend.mappers.EmployeeMapper;
import com.yachtclub.backend.repositories.EmployeeRepository;
import com.yachtclub.backend.repositories.EmployeeZoneRepository;
import com.yachtclub.backend.services.base.BaseMapper;
import com.yachtclub.backend.services.base.BaseServiceImpl;
import com.yachtclub.backend.services.interfaces.EmployeeService;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeDTO, Long> implements EmployeeService {

    private final EmployeeZoneRepository employeeZoneRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected JpaRepository<Employee, Long> getRepository() {
        return employeeRepository;
    }

    @Override
    protected BaseMapper<Employee, EmployeeDTO> getMapper() {
        return employeeMapper;
    }

    @Override
    protected String getResourceName() {
        return "Employee";
    }

    @Override
    protected void setEntityId(Employee entity, Long id) {
        entity.setId(id);
    }

    @Override
    public EmployeeDTO create(EmployeeDTO dto) {
        // Encrypt password before saving
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return super.create(dto);
    }

    @Override
    public EmployeeDTO update(Long id, EmployeeDTO dto) {
        // Encrypt password before updating if it's being changed
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return super.update(id, dto);
    }

    // Custom methods specific to Employee
    @Override
    public Optional<EmployeeDTO> searchByUsername(String username) {
        log.debug("EMPLOYEE SERVICE: searching username={}", username);
        return employeeRepository.findByUsername(username).map(employeeMapper::toDTO);
    }

    @Override
    public Optional<Employee> getByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<EmployeeZone> getAssignedZones(Long id) {
        return employeeZoneRepository.findByEmployeeId(id);
    }
}
