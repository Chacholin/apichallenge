package com.aluraAPI.Apichallenge.domains.respuestas;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ResgistrosDatosRespuestas(
        @NotBlank(message = "El contenido de la respuesta no puede estar en blanco")
        String contenidoRespuesta,
        @NotBlank(message = "El ID del autor no puede estar en blanco")
        UUID autorId,
        @NotBlank(message = "El ID del tópico no puede estar en blanco")
        Long topicoId) {
}