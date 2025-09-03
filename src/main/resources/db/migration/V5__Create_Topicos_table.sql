CREATE TABLE topicos (
                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         descricao TEXT,
                         ativo BOOLEAN DEFAULT TRUE,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
