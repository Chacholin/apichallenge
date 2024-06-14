package com.aluraAPI.Apichallenge.domains.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositorioCurso extends JpaRepository<Curso, Integer> {
    @Query("""
            SELECT c
            FROM Curso c
            WHERE c.estado = 'ACTIVO'
            """)
    Page<Curso> findByActivoTrue(Pageable paginacion);
}
