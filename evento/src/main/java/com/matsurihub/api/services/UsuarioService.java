package com.matsurihub.api.services;

import com.matsurihub.api.domain.usuario.RequestUsuario;
import com.matsurihub.api.domain.usuario.Usuario;
import com.matsurihub.api.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
    public Optional<Usuario> buscarPorId(UUID id){
        return repository.findById(id);
    }

    @Transactional
    public Usuario cadastrarUsuario(RequestUsuario data){
        Usuario usuario = new Usuario(data);
        usuario.setSenha(passwordEncoder.encode(data.senha()));
        return repository.save(usuario);
    }

    @Transactional
    public Optional<Usuario> atualizarUsuario(UUID id, RequestUsuario data){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        optionalUsuario.ifPresent(usuario -> {
            usuario.setNome(data.nome());
            usuario.setEmail(data.email());
            usuario.setSenha(passwordEncoder.encode(data.senha()));
            usuario.setIdade(data.idade());
            usuario.setCidade(data.cidade());
        });
        return optionalUsuario;
    }

    @Transactional
    public boolean deletarUsuario(UUID id){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        optionalUsuario.ifPresent(repository::delete);
        return optionalUsuario.isPresent();
    }
}
