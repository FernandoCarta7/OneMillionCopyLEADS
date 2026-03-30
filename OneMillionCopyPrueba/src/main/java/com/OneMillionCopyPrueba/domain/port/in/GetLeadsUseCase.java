package com.OneMillionCopyPrueba.domain.port.in;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.OneMillionCopyPrueba.domain.model.Fuente;
import com.OneMillionCopyPrueba.domain.model.Lead;

public interface GetLeadsUseCase {

    Page<Lead> getLeads(
            int page,
            int limit,
            Fuente fuente,
            LocalDateTime fecha_creacion_inicio,
            LocalDateTime fecha_creacion_fin);
}
