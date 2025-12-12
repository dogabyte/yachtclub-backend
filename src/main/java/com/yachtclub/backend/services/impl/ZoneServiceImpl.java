package com.yachtclub.backend.services.impl;

import com.yachtclub.backend.dtos.ZoneDTO;
import com.yachtclub.backend.entities.Zone;
import com.yachtclub.backend.exceptions.ResourceNotFoundException;
import com.yachtclub.backend.mappers.ZoneMapper;
import com.yachtclub.backend.repositories.ZoneRepository;
import com.yachtclub.backend.services.base.BaseMapper;
import com.yachtclub.backend.services.base.BaseServiceImpl;
import com.yachtclub.backend.services.interfaces.ZoneService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl extends BaseServiceImpl<Zone, ZoneDTO, Long> implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    protected JpaRepository<Zone, Long> getRepository() {
        return zoneRepository;
    }

    @Override
    protected BaseMapper<Zone, ZoneDTO> getMapper() {
        return zoneMapper;
    }

    @Override
    protected String getResourceName() {
        return "Zone";
    }

    @Override
    protected void setEntityId(Zone entity, Long id) {
        entity.setId(id);
    }

    // Custom methods specific to Zone
    @Override
    public Optional<ZoneDTO> getByName(String name) {
        return zoneRepository.findByName(name)
                .map(zoneMapper::toDTO);
    }

    @Override
    public void deleteByName(String name) {
        Zone zone = zoneRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "name", name));
        zoneRepository.delete(zone);
    }
}
