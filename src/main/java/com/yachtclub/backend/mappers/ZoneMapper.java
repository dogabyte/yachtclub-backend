package com.yachtclub.backend.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.yachtclub.backend.dtos.ZoneDTO;
import com.yachtclub.backend.entities.Zone;

import com.yachtclub.backend.services.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface ZoneMapper extends BaseMapper<Zone, ZoneDTO> {

    @Mapping(target = "employeeZones", ignore = true)
    @Mapping(target = "moorings", ignore = true)
    ZoneDTO toDTO(Zone zone);

    @Mapping(target = "employeeZones", ignore = true)
    @Mapping(target = "moorings", ignore = true)
    Zone toEntity(ZoneDTO zoneDTO);

    List<ZoneDTO> toDTOs(List<Zone> zones);

    List<Zone> toEntities(List<ZoneDTO> zoneDTOs);
}
