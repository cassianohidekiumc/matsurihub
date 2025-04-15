package com.matsurihub.api.domain.usuario;

import java.util.UUID;

public record UsuarioResponse(UUID id, String nome, String email) {
    public UsuarioResponse(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
