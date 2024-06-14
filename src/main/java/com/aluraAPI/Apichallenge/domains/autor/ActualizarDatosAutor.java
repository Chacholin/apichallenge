package com.aluraAPI.Apichallenge.domains.autor;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ActualizarDatosAutor(
        @NotNull(message = "El id no puede estar vacío")
        UUID id,
        String nombre,
        String email,
        String clave) {
}
