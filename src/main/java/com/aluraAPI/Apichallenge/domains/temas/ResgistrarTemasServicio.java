package com.aluraAPI.Apichallenge.domains.temas;

import com.aluraAPI.Apichallenge.domains.autor.RepositorioAutor;
import com.aluraAPI.Apichallenge.domains.curso.RepositorioCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResgistrarTemasServicio {

    @Autowired
    private RepositorioTemas repositorioTemas;

    @Autowired
    private RepositorioCurso repositorioCurso;

    @Autowired
    private RepositorioAutor repositorioAutor;

    public DetalleDatosTema registrar(@Valid RegistroDatosTema datos) {
        if(!repositorioCurso.existsById(datos.cursoId())) {
            throw new IllegalArgumentException("El curso no existe");
        }
        if (!repositorioAutor.existsById(datos.autorId())) {
            throw new IllegalArgumentException("El autor no existe");
        }
        var curso = repositorioCurso.findById(datos.cursoId()).get();
        var autor = repositorioAutor.findById(datos.autorId()).get();

        var tema = new Temas(datos, autor, curso);

        repositorioTemas.save(tema);

        return new DetalleDatosTema(tema);

    }
}


