CREATE TABLE candidato (
                            id BIGINT PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            email VARCHAR(150) UNIQUE NOT NULL,
                            telefone VARCHAR(20),
                            data_nascimento DATE,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

