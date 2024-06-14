package com.aluraAPI.Apichallenge.domains.autor;

import java.time.LocalDateTime;
import java.util.UUID;

public record RespuestaDatosAutor (UUID id, String nombre, String alias, String email, LocalDateTime fechaRegistro) {
    public RespuestaDatosAutor(UUID id, String nombre, String alias, String email, LocalDateTime fechaRegistro) {
    }

    public RespuestaDatosAutor(Integer id, String nombre, String descripcion, String estado) {

    }
}
