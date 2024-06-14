package com.aluraAPI.Apichallenge.domains.autor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor implements UserDetails {
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Id
    private UUID id;
    private String nombre;
    private String alias;
    private String email;
    private String clave;
    private LocalDateTime fechaRegistro;


    public Autor(RegistrosDatosAutor registrosDatosAutor) {
        this.id = UUID.randomUUID();
        this.nombre =registrosDatosAutor.nombre;
        this.alias = registrosDatosAutor.alias;
        this.email = registrosDatosAutor.alias;
        this.clave = new BCryptPasswordEncoder().encode(registrosDatosAutor.clave);
        this.fechaRegistro = LocalDateTime.now();
    }

    public void actualizarDatos(@Valid ActualizarDatosAutor datosActualizarAutor) {
        if (datosActualizarAutor.nombre() != null) {
            this.nombre = datosActualizarAutor.nombre();
        }
        if (datosActualizarAutor.email() != null) {
            this.email = datosActualizarAutor.email();
        }
        if (datosActualizarAutor.clave() != null) {
            this.clave = datosActualizarAutor.clave();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return alias;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}