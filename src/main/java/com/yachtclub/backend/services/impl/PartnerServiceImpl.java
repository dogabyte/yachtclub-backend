package com.yachtclub.backend.services.impl;

import com.yachtclub.backend.dtos.PartnerDTO;
import com.yachtclub.backend.entities.Partner;
import com.yachtclub.backend.exceptions.DuplicateResourceException;
import com.yachtclub.backend.mappers.PartnerMapper;
import com.yachtclub.backend.repositories.PartnerRepository;
import com.yachtclub.backend.services.base.BaseMapper;
import com.yachtclub.backend.services.base.BaseServiceImpl;
import com.yachtclub.backend.services.interfaces.PartnerService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnerServiceImpl extends BaseServiceImpl<Partner, PartnerDTO, Long> implements PartnerService {

    private final PartnerMapper partnerMapper;
    private final PartnerRepository partnerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected JpaRepository<Partner, Long> getRepository() {
        return partnerRepository;
    }

    @Override
    protected BaseMapper<Partner, PartnerDTO> getMapper() {
        return partnerMapper;
    }

    @Override
    protected String getResourceName() {
        return "Partner";
    }

    @Override
    protected void setEntityId(Partner entity, Long id) {
        entity.setId(id);
    }

    @Override
    public PartnerDTO create(PartnerDTO dto) {
        if (partnerRepository.existsByDni(dto.getDni())) {
            throw new DuplicateResourceException("Partner", "DNI", dto.getDni());
        }
        if (partnerRepository.existsByUsername(dto.getUsername())) {
            throw new DuplicateResourceException("Partner", "Username", dto.getUsername());
        }
        // Encrypt password before saving
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return super.create(dto);
    }

    @Override
    public PartnerDTO update(Long id, PartnerDTO dto) {
        // Encrypt password before updating if it's being changed
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return super.update(id, dto);
    }

    @Override
    public Optional<Partner> getByUsername(String username) {
        log.debug("PARTNER SERVICE: searching username={}", username);
        return partnerRepository.findByUsername(username);
    }
}
