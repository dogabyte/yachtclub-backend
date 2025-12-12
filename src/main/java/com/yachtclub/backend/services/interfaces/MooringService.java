package com.yachtclub.backend.services.interfaces;

import com.yachtclub.backend.dtos.MooringDTO;
import java.util.List;

public interface MooringService {
    List<MooringDTO> getAll();

    MooringDTO getById(Long id);

    MooringDTO create(MooringDTO dto);

    MooringDTO update(Long id, MooringDTO dto);

    void delete(Long id);

    List<MooringDTO> findByZone(String zoneName);
}
