package com.yachtclub.backend.mappers;

import com.yachtclub.backend.dtos.EmployeeDTO;
import com.yachtclub.backend.entities.Employee;

import java.util.List;

import org.mapstruct.*;

import com.yachtclub.backend.services.base.BaseMapper;

@Mapper(componentModel = "spring", uses = { EmployeeZoneMapper.class })
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {

    @Mapping(source = "employeeZones", target = "assignedZones")
    @Mapping(source = "specialization", target = "specialty")
    EmployeeDTO toDTO(Employee entity);

    @InheritInverseConfiguration
    @Mapping(target = "employeeZones", ignore = true)
    Employee toEntity(EmployeeDTO dto);

    List<EmployeeDTO> toDtoList(List<Employee> employees);
}
