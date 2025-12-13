package com.yachtclub.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "partners")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Partner extends User {

    @Column(name = "dni", unique = true, nullable = false, length = 20)
    private String dni;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Boat> boats;

}
