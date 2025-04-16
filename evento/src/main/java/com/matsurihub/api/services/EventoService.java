package com.matsurihub.api.services;

import com.matsurihub.api.domain.evento.*;
import com.matsurihub.api.domain.usuario.Usuario;
import com.matsurihub.api.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

        // Converte EnderecoRequest para Endereco, se presente
        Endereco endereco = null;
        if (request.endereco() != null) {
            endereco = new Endereco();
            endereco.setRua(request.endereco().rua());
            endereco.setNumero(request.endereco().numero());
            endereco.setComplemento(request.endereco().complemento());
            endereco.setBairro(request.endereco().bairro());
            endereco.setCidade(request.endereco().cidade());
            endereco.setEstado(request.endereco().estado());
            endereco.setCep(request.endereco().cep());
        }

        // Cria o evento com os dados do request
        Evento evento = new Evento(
                UUID.randomUUID(),
                request.titulo(),
                request.descricao(),
                request.dataHora(),
                request.remoto(),
                request.eventUrl(),
                endereco,  // Passa o endereco para a entidade Evento
                organizador,
                new HashSet<>()
        );

        // Salva o evento no banco de dados
        return eventoRepository.save(evento);
    }

    public List<Evento> listarTodosEventos(){
        return eventoRepository.findAll();
    }

    public Evento buscarEventoId(UUID id){
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));
    }

    @Transactional
    public Evento atualizarEvento(UUID id, EventoRequest request){
        Evento evento = buscarEventoId(id);

        Usuario organizador = usuarioRepository.findById(request.organizadorId())
                .orElseThrow(() -> new RuntimeException("O organizador não foi encontrado."));

        evento.setTitulo(request.titulo());
        evento.setDescricao(request.descricao());
        evento.setDataHora(request.dataHora());
        evento.setRemoto(request.remoto());
        evento.setEventUrl(request.eventUrl());
        evento.setOrganizador(organizador);

        if (request.endereco() != null) {
            EnderecoRequest enderecoRequest = request.endereco();
            Endereco endereco = evento.getEndereco();

            if (endereco == null) {
                endereco = new Endereco();
            }
            endereco.setRua(enderecoRequest.rua());
            endereco.setNumero(enderecoRequest.numero());
            endereco.setComplemento(enderecoRequest.complemento());
            endereco.setBairro(enderecoRequest.bairro());
            endereco.setCidade(enderecoRequest.cidade());
            endereco.setEstado(enderecoRequest.estado());
            endereco.setCep(enderecoRequest.cep());

            evento.setEndereco(endereco);
        }else {
            evento.setEndereco(null);
        }
        return eventoRepository.save(evento);
    }

    @Transactional
    public void deletarEvento(UUID id){
        Evento evento = buscarEventoId(id);
        eventoRepository.delete(evento);
    }
}
