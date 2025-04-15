package com.matsurihub.api.domain.evento;

import com.matsurihub.api.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record EventoResponse(
        UUID id,
        String titulo,
        String descricao,
        LocalDateTime dataHora,
        Boolean remoto,
        String eventUrl,
        Endereco endereco,
        Usuario organizador,
        Set<Usuario> participantes
) {
}
