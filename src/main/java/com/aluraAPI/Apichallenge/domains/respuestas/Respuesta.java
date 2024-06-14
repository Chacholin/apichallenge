package com.aluraAPI.Apichallenge.domains.respuestas;
import com.aluraAPI.Apichallenge.domains.autor.Autor;
import com.aluraAPI.Apichallenge.domains.temas.Temas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenidoRespuesta;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Temas temas;


    public Respuesta(ResgistrosDatosRespuestas resgistrosDatosRespuestas, Autor autor, Temas temas ) {
        this.contenidoRespuesta = resgistrosDatosRespuestas.contenidoRespuesta();
        this.fechaCreacion = LocalDateTime.now();
        this.autor = autor;
        this.temas = temas;
    }

    public void actualizar(ActualizarDatosRespuesta actualizarDatosRespuesta) {
        this.contenidoRespuesta = actualizarDatosRespuesta.contenidoRespuesta();
    }
}
