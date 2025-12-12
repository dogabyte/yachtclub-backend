package com.yachtclub.backend.services.impl;

import com.yachtclub.backend.dtos.BoatDTO;
import com.yachtclub.backend.entities.Boat;
import com.yachtclub.backend.entities.Partner;
import com.yachtclub.backend.exceptions.DuplicateResourceException;
import com.yachtclub.backend.exceptions.ResourceNotFoundException;
import com.yachtclub.backend.mappers.BoatMapper;
import com.yachtclub.backend.repositories.BoatRepository;
import com.yachtclub.backend.repositories.PartnerRepository;
import com.yachtclub.backend.services.base.BaseMapper;
import com.yachtclub.backend.services.base.BaseServiceImpl;
import com.yachtclub.backend.services.interfaces.BoatService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoatServiceImpl extends BaseServiceImpl<Boat, BoatDTO, Long> implements BoatService {

    private final BoatRepository boatRepository;
    private final PartnerRepository partnerRepository;
    private final BoatMapper boatMapper;

    @Override
    protected JpaRepository<Boat, Long> getRepository() {
        return boatRepository;
    }

    @Override
    protected BaseMapper<Boat, BoatDTO> getMapper() {
        return boatMapper;
    }

    @Override
    protected String getResourceName() {
        return "Boat";
    }

    @Override
    protected void setEntityId(Boat entity, Long id) {
        entity.setId(id);
    }

    // Override getAll to support pagination
    @Override
    public Page<BoatDTO> getAll(Pageable pageable) {
        return boatRepository.findAll(pageable)
                .map(boatMapper::toDTO);
    }

    // Custom method for searching by registration
    @Override
    public Optional<BoatDTO> searchByRegistration(String registration) {
        return boatRepository.findByVesselNumber(registration)
                .map(boatMapper::toDTO);
    }

    // Override create to add validation and partner assignment
    @Override
    public BoatDTO create(BoatDTO dto) {
        // Check for duplicate vessel number
        if (boatRepository.findByVesselNumber(dto.getVesselNumber()).isPresent()) {
            throw new DuplicateResourceException("Boat", "vesselNumber", dto.getVesselNumber());
        }

        // Convert DTO to entity
        Boat entity = boatMapper.toEntity(dto);

        // Assign partner
        Partner partner = partnerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Partner", "id", dto.getOwnerId()));
        entity.setOwner(partner);

        // Save and return
        Boat saved = boatRepository.save(entity);
        return boatMapper.toDTO(saved);
    }

    // Override update to handle partner assignment
    @Override
    public BoatDTO update(Long id, BoatDTO dto) {
        Boat existing = boatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Boat", "id", id));

        // Update fields
        existing.setName(dto.getName());
        existing.setType(dto.getType());
        existing.setLength(dto.getLength());
        existing.setBeam(dto.getBeam());
        existing.setDraft(dto.getDraft());
        existing.setVesselNumber(dto.getVesselNumber());

        // Update partner
        Partner partner = partnerRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Partner", "id", dto.getOwnerId()));
        existing.setOwner(partner);

        Boat updated = boatRepository.save(existing);
        return boatMapper.toDTO(updated);
    }

    // Custom method to find boats by partner
    @Override
    public List<BoatDTO> findByPartnerId(Long partnerId) {
        return boatRepository.findByOwnerId(partnerId)
                .stream()
                .map(boatMapper::toDTO)
                .collect(Collectors.toList());
    }
}
