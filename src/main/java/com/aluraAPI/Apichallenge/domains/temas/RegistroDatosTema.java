package com.aluraAPI.Apichallenge.domains.temas;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record RegistroDatosTema(
        @NotBlank(message = "El título del tema no puede estar en blanco")
        String titulo,
        @NotBlank(message = "El mensaje del tema no puede estar en blanco")
        String mensaje,
        @NotBlank(message = "El ID del autor no puede estar en blanco")
        UUID autorId,
        @NotBlank(message = "El nombre del curso no puede estar en blanco")
        Integer cursoId){
}