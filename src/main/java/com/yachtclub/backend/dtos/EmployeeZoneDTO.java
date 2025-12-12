package com.yachtclub.backend.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmployeeZoneDTO {

    // Primero la zona, según tu EmbeddedId
    private Long zoneId;
    private Long employeeId;
    private Integer boatCount;
    private String zoneName;
    private String employeeName;

}
