package com.yachtclub.backend.mappers;

import com.yachtclub.backend.dtos.AdministratorDTO;
import com.yachtclub.backend.entities.Administrator;
import org.mapstruct.Mapper;

import com.yachtclub.backend.services.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface AdministratorMapper extends BaseMapper<Administrator, AdministratorDTO> {
    AdministratorDTO toDTO(Administrator administrator);

    Administrator toEntity(AdministratorDTO dto);
}
