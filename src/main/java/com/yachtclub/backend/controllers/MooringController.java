package com.yachtclub.backend.controllers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yachtclub.backend.dtos.MooringDTO;
import com.yachtclub.backend.services.interfaces.MooringService;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.moorings}")
@ConfigurationProperties
public class MooringController {

    private final MooringService mooringService;

    public MooringController(MooringService mooringService) {
        this.mooringService = mooringService;
    }

    @GetMapping
    public ResponseEntity<List<MooringDTO>> getAll() {
        return ResponseEntity.ok(mooringService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MooringDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mooringService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MooringDTO> create(@jakarta.validation.Valid @RequestBody MooringDTO dto) {
        MooringDTO created = mooringService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MooringDTO> update(@PathVariable Long id,
            @jakarta.validation.Valid @RequestBody MooringDTO dto) {
        return ResponseEntity.ok(mooringService.update(id, dto));
    }

    @GetMapping("/by-zone/{zoneName}")
    public ResponseEntity<List<MooringDTO>> getByZone(@PathVariable String zoneName) {
        return ResponseEntity.ok(mooringService.findByZone(zoneName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mooringService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
