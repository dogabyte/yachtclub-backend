package com.yachtclub.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yachtclub.backend.dtos.BoatDTO;
import com.yachtclub.backend.services.interfaces.BoatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@ConfigurationProperties
@RequestMapping("${api.endpoint.boats}")
@RequiredArgsConstructor
@Tag(name = "Boat Controller", description = "Operations related to boats")
public class BoatController {

    private final BoatService boatService;

    @GetMapping
    @Operation(summary = "Get all boats", description = "Retrieve a paginated list of all boats")
    public ResponseEntity<Page<BoatDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(boatService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoatDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(boatService.getById(id));
    }

    @GetMapping("/search-by-registration/{registration}")
    public ResponseEntity<BoatDTO> searchByRegistration(@PathVariable String registration) {
        return boatService.searchByRegistration(registration)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a boat", description = "Register a new boat")
    public ResponseEntity<BoatDTO> create(@Valid @RequestBody BoatDTO dto) {
        return ResponseEntity.status(201).body(boatService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoatDTO> update(@PathVariable Long id, @Valid @RequestBody BoatDTO dto) {
        return ResponseEntity.ok(boatService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boatService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/partner/{partnerId}")
    public ResponseEntity<List<BoatDTO>> getByPartnerId(@PathVariable Long partnerId) {
        return ResponseEntity.ok(boatService.findByPartnerId(partnerId));
    }
}
