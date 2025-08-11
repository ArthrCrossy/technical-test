CREATE TABLE candidatos (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL,
                            email VARCHAR(255) NOT NULL,
                            telefone VARCHAR(20),
                            data_nascimento DATE,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);