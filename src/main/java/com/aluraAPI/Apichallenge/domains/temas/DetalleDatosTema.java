package com.aluraAPI.Apichallenge.domains.temas;

import java.time.LocalDateTime;
import java.util.UUID;

public record DetalleDatosTema(Long id, String titulo, String mensaje, UUID autor, LocalDateTime fechaCreacion, Integer curso, String estado){
    public DetalleDatosTema(Temas temas) {
        this(temas.getId(), temas.getTitulo(), temas.getMensaje(), temas.getAutor().getId(), temas.getFechaCreacion(), temas.getCurso().getId(), temas.getEstado().toString());
    }
}
