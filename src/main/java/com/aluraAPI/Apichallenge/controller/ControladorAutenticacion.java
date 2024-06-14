package com.aluraAPI.Apichallenge.controller;

import com.aluraAPI.Apichallenge.infras.security.TokenService;
import com.aluraAPI.Apichallenge.domains.autor.*;
import com.aluraAPI.Apichallenge.infras.security.DatosJWTtoken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@Tag(name = "Autenticacion", description = "Login y registro de usuarios, da acceso al resto de endpoint")
@RestController
public class ControladorAutenticacion {



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RepositorioAutor repositorioAutor;

    @PostMapping("/login")
    @Operation(summary = "Autentica un usuario y devuelve un token JWT")
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionDatosAutor credenciales) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(credenciales.alias(), credenciales.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((Autor) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

    @PostMapping("/registro")
    @Operation(summary = "De favor Registrar un nuevo autor en la base de datos ☺")
    @Transactional
    public ResponseEntity<RespuestaDatosAutor> registrar(@RequestBody @Valid RegistrosDatosAutor registrosDatosAutor, UriComponentsBuilder uriComponentsBuilder) {
        Autor autor = repositorioAutor.save(new Autor(registrosDatosAutor));
        RespuestaDatosAutor respuestaDatosAutor = new RespuestaDatosAutor(autor.getId(), autor.getNombre(), autor.getAlias(), autor.getEmail(), autor.getFechaRegistro());
        URI location = uriComponentsBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(location).body(respuestaDatosAutor);
    }
}
