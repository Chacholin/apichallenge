package com.aluraAPI.Apichallenge.controller;
import com.aluraAPI.Apichallenge.domains.autor.RespuestaDatosAutor;
import com.aluraAPI.Apichallenge.domains.curso.*;
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

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Cursos", description = "Gestiona los cursos del foro")

public class ControladorCurso {

    @Autowired
    private RepositorioCurso repositorioCurso;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo curso en la base de datos")
    public ResponseEntity<RespuestaDatosAutor> crear(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso) {
        Curso curso = repositorioCurso.save(new Curso(datosRegistroCurso));
        RespuestaDatosAutor respuestaDatosAutor = new RespuestaDatosAutor(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getEstado());
        return ResponseEntity.ok(respuestaDatosAutor);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un curso existente")
    public ResponseEntity<RespuestaDatosCurso> actualizar(@RequestBody @Valid ActualizarDatosCurso actualizarDatosCurso) {
        Curso curso = repositorioCurso.getReferenceById(actualizarDatosCurso.id());
        curso.actualizarDatos(actualizarDatosCurso);
        return ResponseEntity.ok(new RespuestaDatosCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getEstado()));
    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de cursos")
    public ResponseEntity<Page<ListadosDatosCurso>> listar(@PageableDefault(size =4, sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(repositorioCurso.findByActivoTrue(paginacion).map(ListadosDatosCurso::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Desactiva un curso existente")
    public ResponseEntity eliminar(@PathVariable Integer id) {
        Curso curso = repositorioCurso.getReferenceById(id);
        curso.desactivarCurso();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un curso por su id")
    public ResponseEntity<RespuestaDatosCurso> buscarPorId(@PathVariable Integer id) {
        Curso curso = repositorioCurso.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaDatosCurso(curso.getId(), curso.getNombre(), curso.getDescripcion(), curso.getEstado()));
    }
}
