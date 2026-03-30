package com.OneMillionCopyPrueba.domain.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "leads")
public class Lead {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    private String telefono;

    @NotBlank(message = "La fuente es obligatoria")
    private Fuente fuente;
    
    private String productoInteres;

    @Positive(message = "El presupuesto debe ser mayor a 0")
    private Double presupuesto;
    
    private LocalDate fecha_creacion;

}
