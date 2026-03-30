package com.OneMillionCopyPrueba.application.service;

import com.OneMillionCopyPrueba.domain.model.Lead;
import com.OneMillionCopyPrueba.domain.port.in.GetLeadByIdUseCase;
import com.OneMillionCopyPrueba.domain.port.out.LeadRepositoryPort;

public class GetLeadByIdService implements GetLeadByIdUseCase {
private final LeadRepositoryPort repository;

    public GetLeadByIdService(LeadRepositoryPort repository) {
        this.repository = repository;
    }
    @Override
    public Lead getById(Long id) {
       return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead no encontrado con id: " + id));
    }
    
}
