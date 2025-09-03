CREATE TYPE status_entrevista AS ENUM ('AGENDADA', 'REALIZADA', 'CANCELADA');

CREATE TABLE entrevistas (
                             id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             candidato_id BIGINT NOT NULL,
                             entrevistador_id BIGINT NOT NULL,
                             data_entrevista TIMESTAMP NOT NULL,
                             observacoes TEXT,
                             status status_entrevista DEFAULT 'AGENDADA',
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (candidato_id) REFERENCES candidatos(id),
                             FOREIGN KEY (entrevistador_id) REFERENCES entrevistadores(id)
);