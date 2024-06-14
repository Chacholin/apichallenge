package com.aluraAPI.Apichallenge.infras.errores;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String msg) {
        super(msg);
    }
}
