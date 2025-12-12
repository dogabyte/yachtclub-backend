package com.yachtclub.backend.controllers;

import com.yachtclub.backend.dtos.EmployeeZoneDTO;
import com.yachtclub.backend.services.interfaces.EmployeeZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("${api.endpoint.employee-zones}")
@RequiredArgsConstructor
public class EmployeeZoneController {

    private final EmployeeZoneService employeeZoneService;

    @GetMapping
    public ResponseEntity<List<EmployeeZoneDTO>> getAll() {
        return ResponseEntity.ok(employeeZoneService.getAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeZoneDTO> create(@RequestBody EmployeeZoneDTO dto) {
        return ResponseEntity.status(201).body(employeeZoneService.create(dto));
    }

    @DeleteMapping("/{employeeId}/{zoneId}")
    public ResponseEntity<Void> delete(@PathVariable Long employeeId, @PathVariable Long zoneId) {
        employeeZoneService.delete(employeeId, zoneId);
        return ResponseEntity.noContent().build();
    }
}
