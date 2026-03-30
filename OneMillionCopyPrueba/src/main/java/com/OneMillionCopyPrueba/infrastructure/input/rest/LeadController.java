package com.OneMillionCopyPrueba.infrastructure.input.rest;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.OneMillionCopyPrueba.domain.model.Fuente;
import com.OneMillionCopyPrueba.domain.model.Lead;
import com.OneMillionCopyPrueba.domain.port.in.CreateLeadUseCase;
import com.OneMillionCopyPrueba.domain.port.in.GetLeadsUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/leads")
public class LeadController {
    private final CreateLeadUseCase useCase;
    private final GetLeadsUseCase useCaseGet;

    public LeadController(CreateLeadUseCase useCase,
        GetLeadsUseCase useCaseGet
    ) {
        this.useCase = useCase;
        this.useCaseGet = useCaseGet;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lead create(@Valid @RequestBody Lead lead) {
        return useCase.createLead(lead);
    }

    @GetMapping
    public Page<Lead> getLeads(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit,

            @RequestParam(required = false) Fuente fuente,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha_creacion_inicio,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha_creacion_fin) {
        return useCaseGet.getLeads(page, limit, fuente, fecha_creacion_inicio, fecha_creacion_fin);
    }
}
