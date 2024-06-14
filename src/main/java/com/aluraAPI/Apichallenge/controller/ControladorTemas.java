package com.aluraAPI.Apichallenge.controller;

import com.aluraAPI.Apichallenge.domains.temas.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topicos", description = "Gestiona los topicos del foro")


public class ControladorTemas {
    @Autowired
    private RepositorioTemas repositorioTemas;

    @Autowired
    private ResgistrarTemasServicio resgistrarTemasServicio;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo topico en la base de datos")
    public ResponseEntity<DetalleDatosTema> crear(@RequestBody @Valid RegistroDatosTema datosRegistroTopico) {
        var response = resgistrarTemasServicio.registrar(datosRegistroTopico);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un topico existente")
    public ResponseEntity<DetalleDatosTema> actualizar(@RequestBody @Valid ActualizarDatosTema actualizarDatosTema) {
        Temas temas = repositorioTemas.getReferenceById(AbstractXmlApplicationContext.id());
        temas.actualizarDatos(actualizarDatosTema);
        return ResponseEntity.ok(new DetalleDatosTema(temas.getId(),temas.getTitulo(), temas.getMensaje(), temas.getAutor().getId(),  temas.getFechaCreacion(), temas.getCurso().getId(), temas.getEstado().toString()));
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de topicos")
    public ResponseEntity<Page<DetalleDatosTema>> listar(@PageableDefault(size =4, sort = "fechaCreacion") Pageable paginacion) {
        return ResponseEntity.ok(repositorioTemas.findAll(paginacion).map(DetalleDatosTema::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Desactiva un topico existente")
    public ResponseEntity eliminar(@PathVariable Long id) {
        Temas temas = repositorioTemas.getReferenceById(id);
        temas.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un topico por su id")
    public ResponseEntity<DetalleDatosTema> buscarPorId(@PathVariable Long id) {
        Temas temas = repositorioTemas.getReferenceById(id);
        return ResponseEntity.ok(new DetalleDatosTema(temas.getId(),temas.getTitulo(), temas.getMensaje(), temas.getAutor().getId(),  temas.getFechaCreacion(), temas.getCurso().getId(), temas.getEstado().toString()));
    }
}
