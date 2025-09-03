<<<<<<< HEAD

CREATE TABLE avaliacoes (
                            id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
=======
CREATE TABLE avaliacoes (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
>>>>>>> origin/main
                            entrevista_id BIGINT NOT NULL,
                            candidatos_id BIGINT NOT NULL,
                            nota FLOAT NOT NULL,
                            observacoes TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (entrevista_id) REFERENCES entrevistas(id),
                            FOREIGN KEY (candidatos_id) REFERENCES candidatos(id)
);