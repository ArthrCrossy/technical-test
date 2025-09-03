CREATE TABLE topicos (
<<<<<<< HEAD
                         id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
=======
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
>>>>>>> origin/main
                         nome VARCHAR(255) NOT NULL,
                         descricao TEXT,
                         ativo BOOLEAN DEFAULT TRUE,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
