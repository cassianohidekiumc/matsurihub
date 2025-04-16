package com.matsurihub.api.domain.evento;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record EventoRequest(
        String titulo,
        String descricao,
        LocalDateTime dataHora,
        Boolean remoto,
        String eventUrl,
        EnderecoRequest endereco,
        UUID organizadorId,
        Set<UUID> participantesIds
) {
}
