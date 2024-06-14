package com.aluraAPI.Apichallenge.controller;

import com.aluraAPI.Apichallenge.domains.autor.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/autores")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Autor", description = "Gestiona los autores del foro")


public class ControladorAutor {

    @Autowired
    private RepositorioAutor repositorioAutor;

    @Operation(summary = "Lista todos los autores")
    @GetMapping
    public ResponseEntity<Page<ListadoDatosAutor>> listar (@PageableDefault(size =4, sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(repositorioAutor.findAll(paginacion).map(ListadoDatosAutor::new));
    }

    @Operation(summary = "Actualiza los datos de un autor existente")
    @Transactional
    @PutMapping
    public ResponseEntity<RespuestaDatosAutor> actualizar(@RequestBody @Valid ActualizarDatosAutor actualizarDatosAutor) {
        System.out.println(actualizarDatosAutor);
        Autor autor = repositorioAutor.getReferenceById(actualizarDatosAutor.id());
        autor.actualizarDatos(actualizarDatosAutor);
        return ResponseEntity.ok(new RespuestaDatosAutor(autor.getId(), autor.getNombre(), autor.getAlias() ,autor.getEmail(), autor.getFechaRegistro()));
    }

    @Operation(summary = "Muestra un autor por su id")
    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDatosAutor> buscarPorId(@PathVariable UUID id) {
        Autor autor =  repositorioAutor.getReferenceById(id);
        var datosRespuestaAutor = new RespuestaDatosAutor(autor.getId(), autor.getNombre(), autor.getAlias(), autor.getEmail(), autor.getFechaRegistro());
        return ResponseEntity.ok(datosRespuestaAutor);
    }

}
