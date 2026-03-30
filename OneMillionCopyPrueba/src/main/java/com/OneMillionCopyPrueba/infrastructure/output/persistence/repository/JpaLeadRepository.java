package com.OneMillionCopyPrueba.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OneMillionCopyPrueba.infrastructure.output.persistence.entity.LeadEntity;

public interface JpaLeadRepository extends JpaRepository <LeadEntity, Long> {

    boolean existsByEmail(String email);
    
}
