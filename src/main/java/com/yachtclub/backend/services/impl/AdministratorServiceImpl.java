package com.yachtclub.backend.services.impl;

import com.yachtclub.backend.dtos.AdministratorDTO;
import com.yachtclub.backend.entities.Administrator;
import com.yachtclub.backend.mappers.AdministratorMapper;
import com.yachtclub.backend.repositories.AdministratorRepository;
import com.yachtclub.backend.services.base.BaseMapper;
import com.yachtclub.backend.services.base.BaseServiceImpl;
import com.yachtclub.backend.services.interfaces.AdministratorService;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdministratorServiceImpl extends BaseServiceImpl<Administrator, AdministratorDTO, Long>
        implements AdministratorService {

    private final AdministratorRepository adminRepo;
    private final AdministratorMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected JpaRepository<Administrator, Long> getRepository() {
        return adminRepo;
    }

    @Override
    protected BaseMapper<Administrator, AdministratorDTO> getMapper() {
        return mapper;
    }

    @Override
    protected String getResourceName() {
        return "Administrator";
    }

    @Override
    protected void setEntityId(Administrator entity, Long id) {
        entity.setId(id);
    }

    @Override
    public AdministratorDTO create(AdministratorDTO dto) {
        // Encrypt password before saving
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return super.create(dto);
    }

    @Override
    public AdministratorDTO update(Long id, AdministratorDTO dto) {
        // Encrypt password before updating if it's being changed
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return super.update(id, dto);
    }

    // Custom method specific to Administrator
    @Override
    public Optional<Administrator> getByUsername(String username) {
        log.debug("ADMIN SERVICE: searching username={}", username);
        return adminRepo.findByUsername(username);
    }
}
