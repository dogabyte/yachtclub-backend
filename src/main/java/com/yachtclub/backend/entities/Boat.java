package com.yachtclub.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "boats")

public class Boat {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_boat")
     private Long id;

     @Column(unique = true, nullable = false, length = 50)
     private String vesselNumber;

     @Column(name = "name", nullable = false, length = 100)
     private String name;

     @Column(name = "type", nullable = false, length = 50)
     private String type;

     @Column(name = "length", nullable = false)
     private Double length;

     @Column(name = "beam", nullable = false)
     private Double beam;

     @Column(name = "draft", nullable = false)
     private Double draft;

     @ManyToOne
     @JoinColumn(name = "id_owner", nullable = false)
     private Partner owner;

     @OneToOne(mappedBy = "boat")
     private Mooring mooring;

}
