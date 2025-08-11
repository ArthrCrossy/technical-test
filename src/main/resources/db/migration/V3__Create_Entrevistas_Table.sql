CREATE TABLE entrevistas (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             candidato_id BIGINT NOT NULL,
                             entrevistador_id BIGINT NOT NULL,
                             data_entrevista DATETIME NOT NULL,
                             observacoes TEXT,
                             status ENUM('AGENDADA', 'REALIZADA', 'CANCELADA') DEFAULT 'AGENDADA',
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             FOREIGN KEY (candidato_id) REFERENCES candidatos(id),
                             FOREIGN KEY (entrevistador_id) REFERENCES entrevistadores(id)
);