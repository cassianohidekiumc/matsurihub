package com.matsurihub.api.domain.evento;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matsurihub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "evento")
@Entity(name = "evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Evento {
        @Id @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        private String titulo;

        private String descricao;

        private LocalDateTime dataHora;

        private Boolean remoto;

        private String eventUrl;

        @Embedded
        private Endereco endereco;

        @ManyToOne
        @JoinColumn(name = "organizador_id")
        private Usuario organizador;

        @ManyToMany
        @JoinTable(
                name = "evento_participantes",
                joinColumns = @JoinColumn(name = "evento_id"),
                inverseJoinColumns = @JoinColumn(name = "usuario_id")
        )
        @JsonIgnore
        private Set<Usuario> participantes = new HashSet<>();

}