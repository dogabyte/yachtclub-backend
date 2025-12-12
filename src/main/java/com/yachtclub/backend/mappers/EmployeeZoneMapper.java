package com.yachtclub.backend.mappers;

import com.yachtclub.backend.dtos.EmployeeZoneDTO;
import com.yachtclub.backend.entities.EmployeeZone;

import java.util.List;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeZoneMapper {

    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(source = "boatCount", target = "quantityOfBoats")
    @Mapping(target = "id", ignore = true)
    EmployeeZone toEntity(EmployeeZoneDTO dto);

    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "zone.name", target = "zoneName")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "employee.name", target = "employeeName")
    @Mapping(source = "quantityOfBoats", target = "boatCount")
    EmployeeZoneDTO toDTO(EmployeeZone entity);

    List<EmployeeZoneDTO> toDtoList(List<EmployeeZone> entities);
}
