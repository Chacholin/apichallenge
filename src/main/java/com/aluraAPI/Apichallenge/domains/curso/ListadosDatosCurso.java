package com.aluraAPI.Apichallenge.domains.curso;

public record ListadosDatosCurso(Integer id, String nombre, String descripcion) {
    public ListadosDatosCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getDescripcion());
    }
}