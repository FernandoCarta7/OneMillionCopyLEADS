package com.OneMillionCopyPrueba.infrastructure.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.OneMillionCopyPrueba.infrastructure.output.persistence.entity.LeadEntity;

public interface JpaLeadRepository extends JpaRepository<LeadEntity, Long>, JpaSpecificationExecutor<LeadEntity> {

    boolean existsByEmail(String email);

}
