package com.OneMillionCopyPrueba.domain.exception;

public class LeadNotFoundException extends RuntimeException {
    public LeadNotFoundException(Long id) {
        super("Lead no encontrado con id: " + id);
    }
    
}
