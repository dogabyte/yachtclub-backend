package com.yachtclub.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class User {

 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user") 
    protected Long id;

    protected String name;
    protected String address;
    protected String phone;

    @Column(unique = true, nullable = false)
    protected String username;

    @Column(nullable = false)
    protected String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;
}
