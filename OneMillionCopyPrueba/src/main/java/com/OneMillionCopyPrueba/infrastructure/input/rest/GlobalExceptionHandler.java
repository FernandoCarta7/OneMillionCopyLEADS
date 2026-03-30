package com.OneMillionCopyPrueba.infrastructure.input.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.OneMillionCopyPrueba.domain.exception.DuplicateEmailException;
import com.OneMillionCopyPrueba.domain.exception.LeadNotFoundException;

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
                    "message", "La fuente debe ser: INSTAGRAM, FACEBOOK, LANDING_PAGE, REFERIDO, OTRO");
        }

        return Map.of(
                "error", "JSON_INVALIDO",
                "message", "Error en el formato del request");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgument(IllegalArgumentException ex) {
        return Map.of(
                "error", "VALIDACION_NEGOCIO",
                "message", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return Map.of(
                "error", "VALIDATION_ERROR",
                "messages", errores);
    }

    @ExceptionHandler(LeadNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(LeadNotFoundException ex) {
        return Map.of(
                "error", "NOT_FOUND",
                "message", ex.getMessage());
    }

}
