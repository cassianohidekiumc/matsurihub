package com.matsurihub.api.controllers;

import com.matsurihub.api.domain.evento.EnderecoResponse;
import com.matsurihub.api.domain.evento.Evento;
import com.matsurihub.api.domain.evento.EventoRequest;
import com.matsurihub.api.domain.evento.EventoResponse;
import com.matsurihub.api.domain.usuario.UsuarioResponse;
import com.matsurihub.api.services.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/evento")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    private EventoResponse toResponse(Evento evento){
        return new EventoResponse(
                evento.getId(),
                evento.getTitulo(),
                evento.getDescricao(),
                evento.getDataHora(),
                evento.getRemoto(),
                evento.getEventUrl(),
                evento.getEndereco() != null ? new EnderecoResponse(evento.getEndereco()) : null,
                new UsuarioResponse(evento.getOrganizador()),
                evento.getParticipantes()
                        .stream()
                        .map(UsuarioResponse::new)
                        .collect(Collectors.toSet())
        );
    }

    @PostMapping
    public ResponseEntity<EventoResponse> criarEvento(@ModelAttribute EventoRequest request){
        Evento evento = eventoService.criarEvento(request);
        return ResponseEntity.ok(toResponse(evento));
    }

    @GetMapping
    public String listarEvento(Model model) {
        List<Evento> eventos = eventoService.listarTodosEventos();
        model.addAttribute("eventos", eventos);
        return "evento";  // chama evento.html no templates!
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponse> buscarEventoId(@PathVariable UUID id){
        Evento evento = eventoService.buscarEventoId(id);
        return ResponseEntity.ok(toResponse(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponse> updateEvento(@PathVariable UUID id, @RequestBody EventoRequest request){
        Evento eventoAtualizado = eventoService.atualizarEvento(id, request);
        return ResponseEntity.ok(toResponse(eventoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable UUID id){
        eventoService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }

}
