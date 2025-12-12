package com.yachtclub.backend.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MooringDTO {
    private Long id;
    private Long partnerId;
    @jakarta.validation.constraints.NotNull(message = "Zone ID is required")
    private Long zoneId;

    private String vesselId;

    @jakarta.validation.constraints.PositiveOrZero(message = "Water consumption must be positive or zero")
    private Double waterConsumption;

    @jakarta.validation.constraints.PositiveOrZero(message = "Electricity consumption must be positive or zero")
    private Double electricityConsumption;

    private Boolean maintenanceService;
    private String assignmentDate;
    private String purchaseDate;
}
