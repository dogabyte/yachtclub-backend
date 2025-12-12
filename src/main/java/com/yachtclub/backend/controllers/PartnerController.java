package com.yachtclub.backend.controllers;

import com.yachtclub.backend.dtos.PartnerDTO;
import com.yachtclub.backend.services.interfaces.PartnerService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ConfigurationProperties
@RequestMapping("${api.endpoint.partners}")
@RequiredArgsConstructor

public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping
    public ResponseEntity<List<PartnerDTO>> getAll() {
        return ResponseEntity.ok(partnerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartnerDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(partnerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PartnerDTO> create(@jakarta.validation.Valid @RequestBody PartnerDTO dto) {
        PartnerDTO created = partnerService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartnerDTO> update(@PathVariable Long id,
            @jakarta.validation.Valid @RequestBody PartnerDTO dto) {
        return ResponseEntity.ok(partnerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        partnerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
