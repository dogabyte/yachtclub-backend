package com.yachtclub.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "moorings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mooring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mooring")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_zone", nullable = false)
    private Zone zone;

    @OneToOne
    @JoinColumn(name = "boat_registration", referencedColumnName = "vesselNumber", nullable = false)
    private Boat boat;

    @Column(name = "water_consumption", nullable = true)
    private Double waterConsumption;

    @Column(name = "electricity_consumption", nullable = true)
    private Double electricityConsumption;

    @Column(name = "maintenance_service", nullable = true)
    private Boolean maintenanceService;

    @Column(name = "assignment_date", nullable = false)
    private LocalDate assignmentDate;

    // Can be null if the mooring was not purchased, only assigned
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

}
