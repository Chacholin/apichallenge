package com.aluraAPI.Apichallenge.domains.curso;

import jakarta.validation.constraints.NotNull;

public record ActualizarDatosCurso(
        @NotNull(message = "El id no puede ser erroneo")
        Integer id, String nombre, String descripcion) {
}
