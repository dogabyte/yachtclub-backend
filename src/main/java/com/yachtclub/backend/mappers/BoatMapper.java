package com.yachtclub.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yachtclub.backend.dtos.BoatDTO;
import com.yachtclub.backend.entities.Boat;

import com.yachtclub.backend.services.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface BoatMapper extends BaseMapper<Boat, BoatDTO> {
    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "mooring.zone.id", target = "zoneId")
    @Mapping(source = "mooring.zone.name", target = "zoneName")
    BoatDTO toDTO(Boat boat);

    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mooring", ignore = true)
    Boat toEntity(BoatDTO dto);

    List<BoatDTO> toDTOList(List<Boat> boats);
}
