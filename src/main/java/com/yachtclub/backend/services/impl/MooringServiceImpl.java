package com.yachtclub.backend.services.impl;

import com.yachtclub.backend.dtos.MooringDTO;
import com.yachtclub.backend.entities.Mooring;
import com.yachtclub.backend.mappers.MooringMapper;
import com.yachtclub.backend.repositories.MooringRepository;
import com.yachtclub.backend.services.base.BaseMapper;
import com.yachtclub.backend.services.base.BaseServiceImpl;
import com.yachtclub.backend.services.interfaces.MooringService;
import com.yachtclub.backend.services.validation.EntityValidationService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MooringServiceImpl extends BaseServiceImpl<Mooring, MooringDTO, Long> implements MooringService {

    private final MooringRepository mooringRepository;
    private final MooringMapper mooringMapper;
    private final EntityValidationService validationService;

    @Override
    protected JpaRepository<Mooring, Long> getRepository() {
        return mooringRepository;
    }

    @Override
    protected BaseMapper<Mooring, MooringDTO> getMapper() {
        return mooringMapper;
    }

    @Override
    protected String getResourceName() {
        return "Mooring";
    }

    @Override
    protected void setEntityId(Mooring entity, Long id) {
        entity.setId(id);
    }

    // Override create to add validation
    @Override
    public MooringDTO create(MooringDTO dto) {
        // Validate zone exists
        validationService.validateZoneExists(dto.getZoneId());

        // Validate boat exists if provided
        if (dto.getVesselId() != null) {
            validationService.validateBoatExists(dto.getVesselId());
        }

        // Use base implementation
        return super.create(dto);
    }

    // Custom method to find moorings by zone
    @Override
    public List<MooringDTO> findByZone(String zoneName) {
        List<Mooring> moorings = mooringRepository.findByZoneName(zoneName);
        return moorings.stream()
                .map(mooringMapper::toDTO)
                .collect(Collectors.toList());
    }
}
