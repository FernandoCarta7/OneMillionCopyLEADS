package com.OneMillionCopyPrueba.domain.port.in;

import com.OneMillionCopyPrueba.domain.model.Lead;
public interface GetLeadByIdUseCase {
    Lead getById(Long id);
}