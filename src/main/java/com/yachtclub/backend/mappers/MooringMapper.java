package com.yachtclub.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yachtclub.backend.dtos.MooringDTO;
import com.yachtclub.backend.entities.Mooring;

import com.yachtclub.backend.services.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface MooringMapper extends BaseMapper<Mooring, MooringDTO> {

    @Mapping(target = "partnerId", source = "boat.owner.id")
    @Mapping(target = "zoneId", source = "zone.id")
    @Mapping(source = "boat.vesselNumber", target = "vesselId")
    MooringDTO toDTO(Mooring mooring);

    @Mapping(target = "boat", ignore = true)
    @Mapping(target = "zone", ignore = true)
    Mooring toEntity(MooringDTO dto);
}
