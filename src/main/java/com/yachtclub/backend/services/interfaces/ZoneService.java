package com.yachtclub.backend.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.yachtclub.backend.dtos.ZoneDTO;

public interface ZoneService {
    List<ZoneDTO> getAll();

    ZoneDTO getById(Long id);

    ZoneDTO create(ZoneDTO dto);

    ZoneDTO update(Long id, ZoneDTO dto);

    void delete(Long id);

    Optional<ZoneDTO> getByName(String name);

    void deleteByName(String name);
}
