CREATE TABLE entrevistas
(
    id               BIGINT PRIMARY KEY,
    candidato_id     BIGINT    NOT NULL,
    entrevistador_id BIGINT    NOT NULL,
    data_entrevista  TIMESTAMP NOT NULL,
    observacoes      TEXT,
    status           status_entrevista DEFAULT 'AGENDADA'
);