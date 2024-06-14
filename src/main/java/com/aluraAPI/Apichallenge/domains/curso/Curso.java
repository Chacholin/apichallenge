package com.aluraAPI.Apichallenge.domains.curso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Integer id;
    private String nombre;
    private String descripcion;
    private String estado;



    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre().toUpperCase();
        this.descripcion = datosRegistroCurso.descripcion();
        this.estado = "ACTIVO";
    }

    public void actualizarDatos(@Valid ActualizarDatosCurso datosActualizarCurso) {
        if (datosActualizarCurso.nombre() != null)
            this.nombre = datosActualizarCurso.nombre().toUpperCase();
        if (datosActualizarCurso.descripcion() != null)
            this.descripcion = datosActualizarCurso.descripcion();
    }

    public void desactivarCurso() {
        this.estado = "INACTIVO";
    }
}
