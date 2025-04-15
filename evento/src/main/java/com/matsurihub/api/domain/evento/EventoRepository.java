package com.matsurihub.api.domain.evento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
}
