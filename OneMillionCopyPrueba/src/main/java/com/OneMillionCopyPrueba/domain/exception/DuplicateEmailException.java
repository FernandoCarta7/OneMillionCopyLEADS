package com.OneMillionCopyPrueba.domain.exception;

public class DuplicateEmailException extends RuntimeException  {
    public DuplicateEmailException(String email) {
        super("El email ya existe: " + email);
    }
}
