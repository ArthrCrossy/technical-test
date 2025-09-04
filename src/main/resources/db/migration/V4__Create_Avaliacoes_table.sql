CREATE TABLE avaliacoes (
                            id BIGINT PRIMARY KEY,
                            candidato_id BIGINT,
                            nota INTEGER NOT NULL CHECK (nota >= 0 AND nota <= 10),
                            comentario TEXT,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                            CONSTRAINT fk_avaliacao_candidato
                                FOREIGN KEY (candidato_id)
                                    REFERENCES candidato(id)
                                    ON DELETE CASCADE
);
