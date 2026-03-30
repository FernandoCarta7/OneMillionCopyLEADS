package com.OneMillionCopyPrueba.infrastructure.input.rest;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.OneMillionCopyPrueba.domain.exception.DuplicateEmailException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateEmail(DuplicateEmailException ex) {
        return ex.getMessage();
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidEnum(HttpMessageNotReadableException ex) {

        if (ex.getMessage().contains("Fuente")) {
            return Map.of(
                "error", "FUENTE_INVALIDA",
                "message", "La fuente debe ser: INSTAGRAM, FACEBOOK, LANDING_PAGE, REFERIDO, OTRO"
            );
        }

        return Map.of(
            "error", "JSON_INVALIDO",
            "message", "Error en el formato del request"
        );
    }



}
