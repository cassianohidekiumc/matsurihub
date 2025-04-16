package com.matsurihub.api.domain.evento;

public record EnderecoResponse(
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
    public EnderecoResponse(Endereco endereco){
        this(
                endereco != null ? endereco.getRua() : null,
                endereco != null ? endereco.getNumero() : null,
                endereco != null ? endereco.getComplemento() : null,
                endereco != null ? endereco.getBairro() : null,
                endereco != null ? endereco.getCidade() : null,
                endereco != null ? endereco.getEstado() : null,
                endereco != null ? endereco.getCep() : null
        ); // construtor validando se recebe nulo ou não e deixa passar msm se receber, estava bloqueando
    }
}
