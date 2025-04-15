package com.matsurihub.api.services;

import com.matsurihub.api.domain.evento.Evento;
import com.matsurihub.api.domain.evento.EventoRepository;
import com.matsurihub.api.domain.evento.EventoRequest;
import com.matsurihub.api.domain.usuario.Usuario;
import com.matsurihub.api.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Evento criarEvento(EventoRequest request){
        Usuario organizador = usuarioRepository.findById(request.organizadorId())
                .orElseThrow(() -> new RuntimeException("O organizador não foi encontrado."));

        Evento evento = new Evento(
                UUID.randomUUID(),
                request.titulo(),
                request.descricao(),
                request.dataHora(),
                request.remoto(),
                request.eventUrl(),
                request.endereco(),
                organizador,
                new HashSet<>()
        );

        return eventoRepository.save(evento);
    }
}
