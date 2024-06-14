package com.aluraAPI.Apichallenge.infras.errores;

public class ValidacionExcepcion extends RuntimeException {
    public ValidacionExcepcion(String msg) {
        super(msg);
    }
}
