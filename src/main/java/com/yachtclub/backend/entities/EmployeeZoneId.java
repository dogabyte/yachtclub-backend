package com.yachtclub.backend.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeZoneId implements Serializable {

    private Long zoneId;
    private Long employeeId;
}
