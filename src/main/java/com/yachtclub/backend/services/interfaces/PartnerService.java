package com.yachtclub.backend.services.interfaces;

import com.yachtclub.backend.dtos.PartnerDTO;
import com.yachtclub.backend.entities.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerService {
    List<PartnerDTO> getAll();

    PartnerDTO getById(Long id);

    PartnerDTO create(PartnerDTO dto);

    PartnerDTO update(Long id, PartnerDTO dto);

    void delete(Long id);

    Optional<Partner> getByUsername(String username);
}
