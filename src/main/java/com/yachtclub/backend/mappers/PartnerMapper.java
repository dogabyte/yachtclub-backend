package com.yachtclub.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.yachtclub.backend.dtos.PartnerDTO;
import com.yachtclub.backend.entities.Partner;

import com.yachtclub.backend.services.base.BaseMapper;

@Mapper(componentModel = "spring")
public interface PartnerMapper extends BaseMapper<Partner, PartnerDTO> {

    @Mapping(source = "joinDate", target = "registrationDate")
    @Mapping(target = "boats", ignore = true)
    PartnerDTO toDTO(Partner partner);

    @Mapping(source = "registrationDate", target = "joinDate")
    @Mapping(target = "boats", ignore = true)
    Partner toEntity(PartnerDTO partnerDTO);

    List<PartnerDTO> toDtoList(List<Partner> partners);
}
