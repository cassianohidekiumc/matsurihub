package com.matsurihub.api.domain.evento;

import com.matsurihub.api.domain.usuario.Usuario;
import com.matsurihub.api.domain.usuario.UsuarioResponse;
import com.matsurihub.api.domain.evento.Endereco;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record EventoResponse(
        UUID id,
        String titulo,
        String descricao,
        LocalDateTime dataHora,
        Boolean remoto,
        String eventUrl,
        EnderecoResponse endereco,
        UsuarioResponse organizador,
        Set<UsuarioResponse> participantes
) {
    public EventoResponse(Evento evento){
        this(
                evento.getId(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getDataHora(),
                evento.getRemoto(),
                evento.getEventUrl(),
                new EnderecoResponse(evento.getEndereco()),
                new UsuarioResponse(evento.getOrganizador()),
                evento.getParticipantes()
                        .stream()
                        .map(UsuarioResponse::new)
                        .collect(Collectors.toSet())
        );
    }
}
