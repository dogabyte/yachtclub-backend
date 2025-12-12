package com.yachtclub.backend.dtos;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data

@NoArgsConstructor
@AllArgsConstructor

public class BoatDTO {
    @Schema(example = "1")
    private Long id;

    @Schema(example = "10")
    @jakarta.validation.constraints.NotNull(message = "Owner ID is required")
    private Long ownerId;

    @Schema(example = "REY-123")
    @jakarta.validation.constraints.NotBlank(message = "Vessel number is required")
    private String vesselNumber;

    @Schema(example = "The Black Pearl")
    @jakarta.validation.constraints.NotBlank(message = "Name is required")
    private String name;

    @Schema(example = "Sailboat")
    @jakarta.validation.constraints.NotBlank(message = "Type is required")
    private String type;

    @Schema(example = "12.5")
    @jakarta.validation.constraints.NotNull(message = "Length is required")
    @jakarta.validation.constraints.Positive(message = "Length must be positive")
    private Double length;

    @Schema(example = "4.2")
    @jakarta.validation.constraints.NotNull(message = "Beam is required")
    @jakarta.validation.constraints.Positive(message = "Beam must be positive")
    private Double beam;

    @Schema(example = "2.1")
    @jakarta.validation.constraints.NotNull(message = "Draft is required")
    @jakarta.validation.constraints.Positive(message = "Draft must be positive")
    private Double draft;

    // Zone information from mooring
    @Schema(example = "1")
    private Long zoneId;

    @Schema(example = "Zona A - Veleros Grandes")
    private String zoneName;

}
