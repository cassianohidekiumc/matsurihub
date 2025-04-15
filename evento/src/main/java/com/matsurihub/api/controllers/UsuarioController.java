package com.matsurihub.api.controllers;

import com.matsurihub.api.domain.usuario.*;
import com.matsurihub.api.domain.usuario.RequestUsuario;
import com.matsurihub.api.domain.usuario.UsuarioRepository;
import com.matsurihub.api.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAllUsuarios(){
        var usuarios = service.listarTodos()
                .stream()
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> registerUsuario(@RequestBody @Valid RequestUsuario data){
        var novoUsuario = service.cadastrarUsuario(data);
        return ResponseEntity.ok(new UsuarioResponse(novoUsuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable UUID id){
        return service.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(new UsuarioResponse(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> updateUsuario(@PathVariable UUID id, @Valid RequestUsuario data){
        return service.atualizarUsuario(id, data)
                .map(usuario -> ResponseEntity.ok(new UsuarioResponse(usuario)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id){
        boolean deletado = service.deletarUsuario(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
