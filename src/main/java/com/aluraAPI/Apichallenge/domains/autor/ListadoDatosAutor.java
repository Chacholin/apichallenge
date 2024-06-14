package com.aluraAPI.Apichallenge.domains.autor;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListadoDatosAutor(UUID id, String nombre, String alias, String email, LocalDateTime fechaRegistro) {
    public ListadoDatosAutor(Autor autor) {
        this(autor.getId(), autor.getNombre(), autor.getAlias(), autor.getEmail(), autor.getFechaRegistro());
    }
}
