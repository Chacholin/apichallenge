package com.aluraAPI.Apichallenge.infras.security;

import com.aluraAPI.Apichallenge.domains.autor.RepositorioAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private RepositorioAutor repositorioAutor;

    @Override
    public UserDetails loadUserByUsername(String alias) throws UsernameNotFoundException {
        return repositorioAutor.findByAlias(alias);
    }
}
