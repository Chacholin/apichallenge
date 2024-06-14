package com.aluraAPI.Apichallenge.domains.autor;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AutenticacionDatosAutor(String alias, String clave)  {
}
