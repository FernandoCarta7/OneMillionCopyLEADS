package com.OneMillionCopyPrueba.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Fuente {
    INSTAGRAM,
    FACEBOOK,
    LANDING_PAGE,
    REFERIDO,
    OTRO;

    @JsonCreator
    public static Fuente from(String value) {
        return Fuente.valueOf(value.toUpperCase());
    }
}
