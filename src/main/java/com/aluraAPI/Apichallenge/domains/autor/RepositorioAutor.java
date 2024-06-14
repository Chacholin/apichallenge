package com.aluraAPI.Apichallenge.domains.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface RepositorioAutor extends JpaRepository<Autor, UUID> {
    UserDetails findByAlias(String alias);
}
