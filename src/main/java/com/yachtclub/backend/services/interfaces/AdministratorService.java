package com.yachtclub.backend.services.interfaces;

import com.yachtclub.backend.dtos.AdministratorDTO;
import com.yachtclub.backend.entities.Administrator;

import java.util.List;
import java.util.Optional;

public interface AdministratorService {
    List<AdministratorDTO> getAll();

    AdministratorDTO getById(Long id);

    AdministratorDTO create(AdministratorDTO dto);

    AdministratorDTO update(Long id, AdministratorDTO dto);

    void delete(Long id);

    Optional<Administrator> getByUsername(String username);
}
