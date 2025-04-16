package com.matsurihub.api.domain.usuario;

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

    private Integer idade;

    private String cidade;

    public Usuario(RequestUsuario requestUsuario){
        this.nome = requestUsuario.nome();
        this.email = requestUsuario.email();
        this.senha = requestUsuario.senha();
        this.idade = requestUsuario.idade();
        this.cidade = requestUsuario.cidade();

    }

}

