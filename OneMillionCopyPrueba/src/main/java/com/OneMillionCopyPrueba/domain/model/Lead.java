package com.OneMillionCopyPrueba.domain.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Table(name = "leads")
@AllArgsConstructor
public class Lead {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String nombre;

    @NotBlank(message = "El nombre es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    private String telefono;

    @NotNull(message = "La fuente es obligatoria")
    private Fuente fuente;

    private String productoInteres;

    @Positive(message = "El presupuesto debe ser mayor a 0")
    private Double presupuesto;

    private LocalDate fecha_creacion;

    

   

}
