package com.yachtclub.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_zone")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmployeeZone {

    @EmbeddedId
    private EmployeeZoneId id;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @ManyToOne
    @MapsId("zoneId")
    @JoinColumn(name = "id_zone")
    private Zone zone;

    @Column(name = "quantity_of_boats", nullable = false)
    private Integer quantityOfBoats;

}
