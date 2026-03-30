package com.OneMillionCopyPrueba.domain.port.out;

import com.OneMillionCopyPrueba.domain.model.Lead;

public interface LeadRepositoryPort {
    Lead save(Lead lead);
    boolean existsByEmail(String email);
}
