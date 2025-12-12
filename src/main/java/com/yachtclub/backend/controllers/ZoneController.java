package com.yachtclub.backend.controllers;

import com.yachtclub.backend.dtos.ZoneDTO;
import com.yachtclub.backend.services.interfaces.ZoneService;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ConfigurationProperties
@RequestMapping("${api.endpoint.zones}")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping
    public ResponseEntity<List<ZoneDTO>> getAll() {
        return ResponseEntity.ok(zoneService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(zoneService.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ZoneDTO> getByName(@PathVariable String name) {
        return zoneService.getByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ZoneDTO> create(@jakarta.validation.Valid @RequestBody ZoneDTO dto) {
        return ResponseEntity.status(201).body(zoneService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZoneDTO> update(@PathVariable Long id, @jakarta.validation.Valid @RequestBody ZoneDTO dto) {
        return ResponseEntity.ok(zoneService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        zoneService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteByName(@PathVariable String name) {
        zoneService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }
}
