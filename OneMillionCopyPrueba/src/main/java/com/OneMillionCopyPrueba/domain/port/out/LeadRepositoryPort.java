package com.OneMillionCopyPrueba.domain.port.out;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.OneMillionCopyPrueba.domain.model.Fuente;
import com.OneMillionCopyPrueba.domain.model.Lead;

public interface LeadRepositoryPort {
    Lead save(Lead lead);
    boolean existsByEmail(String email);
    Page<Lead> findAll(
        int page,
        int limit,
        Fuente fuente,
        LocalDateTime fecha_creacion_inicio,
        LocalDateTime fecha_creacion_fin
    );
    Optional<Lead> findById(Long id);
}
