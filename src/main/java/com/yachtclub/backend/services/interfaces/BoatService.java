package com.yachtclub.backend.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.yachtclub.backend.dtos.BoatDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoatService {

    Page<BoatDTO> getAll(Pageable pageable);

    BoatDTO getById(Long id);

    Optional<BoatDTO> searchByRegistration(String registration);

    BoatDTO create(BoatDTO dto);

    BoatDTO update(Long id, BoatDTO dto);

    void delete(Long id);

    List<BoatDTO> findByPartnerId(Long partnerId);
}
