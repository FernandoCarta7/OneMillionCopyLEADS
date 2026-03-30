package com.OneMillionCopyPrueba.application.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;


import com.OneMillionCopyPrueba.domain.model.Fuente;
import com.OneMillionCopyPrueba.domain.model.Lead;
import com.OneMillionCopyPrueba.domain.port.in.GetLeadsUseCase;
import com.OneMillionCopyPrueba.domain.port.out.LeadRepositoryPort;

public class GetLeadsService implements GetLeadsUseCase {
    private final LeadRepositoryPort repository;

    public GetLeadsService(LeadRepositoryPort repo) {
        this.repository = repo;
    }

    @Override
    public Page<Lead> getLeads(int page, int limit, Fuente fuente, LocalDateTime fecha_creacion_inicio, LocalDateTime fecha_creacion_fin) {

        return repository.findAll(page, limit, fuente, fecha_creacion_inicio, fecha_creacion_fin);
    }
}
