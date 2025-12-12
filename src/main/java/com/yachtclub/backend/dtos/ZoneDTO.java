package com.yachtclub.backend.dtos;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneDTO {
    private Long id;
    @jakarta.validation.constraints.NotBlank(message = "Name is required")
    private String name;

    @jakarta.validation.constraints.NotBlank(message = "Boat type is required")
    private String boatType;

    @jakarta.validation.constraints.NotNull(message = "Depth is required")
    @jakarta.validation.constraints.Positive(message = "Depth must be positive")
    private Double depth;

    @jakarta.validation.constraints.NotNull(message = "Width is required")
    @jakarta.validation.constraints.Positive(message = "Width must be positive")
    private Double width;

    @jakarta.validation.constraints.NotNull(message = "Boat capacity is required")
    @jakarta.validation.constraints.Positive(message = "Boat capacity must be positive")
    private Integer boatCapacity;

    private List<EmployeeZoneDTO> employeeZones;
    private List<MooringDTO> moorings;
}
