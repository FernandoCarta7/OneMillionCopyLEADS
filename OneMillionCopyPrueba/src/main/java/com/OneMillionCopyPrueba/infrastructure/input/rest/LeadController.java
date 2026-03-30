package com.OneMillionCopyPrueba.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.OneMillionCopyPrueba.domain.model.Lead;
import com.OneMillionCopyPrueba.domain.port.in.CreateLeadUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/leads")
public class LeadController {
    private final CreateLeadUseCase useCase;

    public LeadController(CreateLeadUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lead create(@Valid @RequestBody Lead lead) {
        return useCase.createLead(lead);
    }
}
