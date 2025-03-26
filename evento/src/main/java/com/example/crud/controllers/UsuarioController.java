package com.example.crud.controllers;

import com.example.crud.domain.usuario.RequestUsuario;
import com.example.crud.domain.usuario.Usuario;
import com.example.crud.domain.usuario.UsuarioRepository;
import com.example.crud.domain.usuario.RequestUsuario;
import com.example.crud.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @GetMapping
    public ResponseEntity getAllUsuarios(){
        var allUsuarios = repository.findAll();
        return ResponseEntity.ok(allUsuarios);
    }

    @PostMapping
    public ResponseEntity registerUsuario(@RequestBody @Valid RequestUsuario data){
        Usuario newUsuario = new Usuario(data);
        repository.save(newUsuario);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateUsuario(@RequestBody @Valid RequestUsuario data){
        Optional<Usuario> optionalUsuario = repository.findById(UUID.fromString(String.valueOf(data.id())));
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(data.nome());
            usuario.setEmail(data.email());
            usuario.setSenha(data.senha());
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUsuarioById(@PathVariable UUID id){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        return optionalUsuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsuario(@PathVariable UUID id){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if (optionalUsuario.isPresent()){
            repository.delete(optionalUsuario.get());
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
