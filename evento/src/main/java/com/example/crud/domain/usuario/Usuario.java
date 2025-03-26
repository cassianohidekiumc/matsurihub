package com.example.crud.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name="usuario")
@Entity(name="usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String email;

    private String senha;

//    @OneToMany(mappedBy = "usuario")
//    private List<ParticipacaoEvento> participacoes;
// precisa criar a classe ParticipacaoEvento -> vou fazer isso posteriormente, assim que
// entregar o primeiro crud funcional, conforme solicitado

    public Usuario(RequestUsuario requestUsuario){
        this.nome = requestUsuario.nome();
        this.email = requestUsuario.email();
        this.senha = requestUsuario.senha();

    }

}

