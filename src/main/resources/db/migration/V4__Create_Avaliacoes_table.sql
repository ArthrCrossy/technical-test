CREATE TABLE avaliacoes (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            entrevista_id BIGINT NOT NULL,
                            candidatos_id BIGINT NOT NULL,
                            nota FLOAT NOT NULL,
                            observacoes TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (entrevista_id) REFERENCES entrevistas(id),
                            FOREIGN KEY (candidatos_id) REFERENCES candidatos(id)
);