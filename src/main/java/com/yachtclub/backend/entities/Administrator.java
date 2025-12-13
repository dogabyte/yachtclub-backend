package com.yachtclub.backend.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@AttributeOverride(name = "id", column = @Column(name = "id_admin"))
public class Administrator extends User {
}