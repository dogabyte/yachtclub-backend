package com.yachtclub.backend.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.AttributeOverride;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "employees")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@AttributeOverride(name = "id", column = @Column(name = "id_employee"))
public class Employee extends User {

    @Column(name = "code", unique = true)
    private Long code;

    private String specialization;

    @Builder.Default
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeZone> employeeZones = new ArrayList<>();

}
