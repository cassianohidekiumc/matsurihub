package com.matsurihub.api.domain.usuario;

import java.util.UUID;

public record UsuarioResponse(UUID id, String nome, String email, Integer idade, String cidade) {
    public UsuarioResponse(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getIdade(), usuario.getCidade());
    }
}
