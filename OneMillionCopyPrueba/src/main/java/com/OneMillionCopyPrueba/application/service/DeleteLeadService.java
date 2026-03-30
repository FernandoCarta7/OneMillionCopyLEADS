package com.OneMillionCopyPrueba.application.service;

import com.OneMillionCopyPrueba.domain.exception.LeadNotFoundException;
import com.OneMillionCopyPrueba.domain.port.in.DeleteLeadUseCase;
import com.OneMillionCopyPrueba.domain.port.out.LeadRepositoryPort;

public class DeleteLeadService implements DeleteLeadUseCase {

   private final LeadRepositoryPort repository;

    public DeleteLeadService(LeadRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new LeadNotFoundException(id);
        }

        repository.deleteById(id);
    }
    
}
