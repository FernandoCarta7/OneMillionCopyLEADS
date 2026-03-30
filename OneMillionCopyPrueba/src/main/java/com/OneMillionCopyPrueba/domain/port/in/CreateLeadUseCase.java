package com.OneMillionCopyPrueba.domain.port.in;

import com.OneMillionCopyPrueba.domain.model.Lead;

public interface CreateLeadUseCase {
    Lead createLead(Lead lead);
}
