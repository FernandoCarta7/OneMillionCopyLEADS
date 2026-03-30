package com.OneMillionCopyPrueba.application.service;

import com.OneMillionCopyPrueba.domain.model.Lead;
import com.OneMillionCopyPrueba.domain.port.in.CreateLeadUseCase;
import com.OneMillionCopyPrueba.domain.port.out.LeadRepositoryPort;

public class CreateLeadService implements CreateLeadUseCase {

    private final LeadRepositoryPort repository;

    public CreateLeadService(LeadRepositoryPort repositoryPort){
        this.repository = repositoryPort;
    }

    @Override
    public Lead createLead(Lead lead) {
        if (repository.existsByEmail(lead.getEmail())) {
            throw new RuntimeException("El email ya existe: " + lead.getEmail() );
        }

        return repository.save(lead);
    }
    
}
