package com.OneMillionCopyPrueba.infrastructure.output.persistence.adapter;

import java.time.LocalDate;

import com.OneMillionCopyPrueba.domain.model.Lead;
import com.OneMillionCopyPrueba.domain.port.out.LeadRepositoryPort;
import com.OneMillionCopyPrueba.infrastructure.output.persistence.entity.LeadEntity;
import com.OneMillionCopyPrueba.infrastructure.output.persistence.repository.JpaLeadRepository;

public class LeadRepositoryAdapter implements LeadRepositoryPort {


    private final JpaLeadRepository repository;

    public LeadRepositoryAdapter(JpaLeadRepository repository) {
        this.repository = repository;
    }


    @Override
    public Lead save(Lead lead) {
        LeadEntity entity = mapToEntity(lead);
        LeadEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }


    private LeadEntity mapToEntity(Lead lead) {
        LeadEntity entity = new LeadEntity();
        entity.setNombre(lead.getNombre());
        entity.setEmail(lead.getEmail());
        entity.setTelefono(lead.getTelefono());
        entity.setFuente(lead.getFuente());
        entity.setProductoInteres(lead.getProductoInteres());
        entity.setPresupuesto(lead.getPresupuesto());
        entity.setFecha_creacion(LocalDate.now());
        return entity;
    }
    private Lead mapToDomain(LeadEntity entity) {
        return new Lead(
                entity.getNombre(),
                entity.getEmail(),
                entity.getTelefono(),
                entity.getFuente(),
                entity.getProductoInteres(),
                entity.getPresupuesto(),
                entity.getFecha_creacion()
        );
    }
    
}
