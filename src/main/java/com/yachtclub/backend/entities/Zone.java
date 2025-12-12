package com.yachtclub.backend.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zones")

public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "boat_type", nullable = false, length = 50)
    private String boatType;

    private Double depth;

    private Double width;

    private Integer boatCapacity;

    @OneToMany(mappedBy = "zone")
    private List<EmployeeZone> employeeZones;

    @OneToMany(mappedBy = "zone")
    private List<Mooring> moorings;

}
