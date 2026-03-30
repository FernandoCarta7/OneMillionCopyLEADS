package com.OneMillionCopyPrueba.infrastructure.output.persistence.entity;

import java.time.LocalDate;

import com.OneMillionCopyPrueba.domain.model.Fuente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "leads", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
public class LeadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private Fuente fuente;
    private String productoInteres;
    private Double presupuesto;
    private LocalDate fecha_creacion;

}
