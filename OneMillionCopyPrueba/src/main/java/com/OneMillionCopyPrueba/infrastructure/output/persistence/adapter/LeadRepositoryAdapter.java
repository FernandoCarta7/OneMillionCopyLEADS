package com.OneMillionCopyPrueba.infrastructure.output.persistence.adapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import com.OneMillionCopyPrueba.domain.model.Fuente;
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
        entity.setFechaCreacion(LocalDate.now());
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
                entity.getFechaCreacion());
    }

    public Page<Lead> findAll(int page, int limit, Fuente fuente,
            LocalDateTime fecha_creacion_inicio,
            LocalDateTime fecha_creacion_fin) {

        Pageable pageable = PageRequest.of(
                page,
                limit,
                Sort.by("fechaCreacion").descending());

        Specification<LeadEntity> spec = (root, query, cb) -> cb.conjunction();

        if (fuente != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("fuente"), fuente));
        }

        if (fecha_creacion_inicio != null && fecha_creacion_fin != null) {
            spec = spec.and((root, query, cb) -> cb.between(root.get("fechaCreacion"), fecha_creacion_inicio,
                    fecha_creacion_fin));
        }

        Page<LeadEntity> result = repository.findAll(spec, pageable);

        return result.map(this::mapToDomain);
    }

    @Override
    public Optional<Lead> findById(Long id) {
        return repository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
