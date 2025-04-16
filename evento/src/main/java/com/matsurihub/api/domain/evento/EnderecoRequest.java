package com.matsurihub.api.domain.evento;

public record EnderecoRequest(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
