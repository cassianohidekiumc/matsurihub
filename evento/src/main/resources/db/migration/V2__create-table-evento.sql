CREATE TABLE evento (
    id UUID PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_hora TIMESTAMP NOT NULL,
    remoto BOOLEAN NOT NULL,

    -- Campos embutidos c/ @Embbeded de Endereco --
    cep VARCHAR(9),
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    estado VARCHAR(2),

    organizador_id UUID,
    FOREIGN KEY (organizador_id) REFERENCES usuario(id)
);

CREATE TABLE evento_participantes (
    evento_id UUID NOT NULL,
    usuario_id UUID NOT NULL,
    PRIMARY KEY (evento_id, usuario_id),
    FOREIGN KEY (evento_id) REFERENCES evento(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);