package com.OneMillionCopyPrueba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.OneMillionCopyPrueba.application.service.CreateLeadService;
import com.OneMillionCopyPrueba.domain.port.in.CreateLeadUseCase;
import com.OneMillionCopyPrueba.domain.port.out.LeadRepositoryPort;
import com.OneMillionCopyPrueba.infrastructure.output.persistence.adapter.LeadRepositoryAdapter;
import com.OneMillionCopyPrueba.infrastructure.output.persistence.repository.JpaLeadRepository;

@Configuration
public class BeanConfiguration {
    @Bean
    public LeadRepositoryPort leadRepositoryPort(JpaLeadRepository jpaRepository) {
        return new LeadRepositoryAdapter(jpaRepository);
    }

    @Bean
    public CreateLeadUseCase createLeadUseCase(LeadRepositoryPort repository) {
        return new CreateLeadService(repository);
    }
}
