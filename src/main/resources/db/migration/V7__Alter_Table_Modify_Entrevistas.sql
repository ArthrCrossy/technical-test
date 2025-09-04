ALTER TABLE entrevistas
    ADD CONSTRAINT fk_entrevistas_candidato
        FOREIGN KEY (candidato_id) REFERENCES candidato(id);

ALTER TABLE entrevistas
    ADD CONSTRAINT fk_entrevistas_entrevistador
        FOREIGN KEY (entrevistador_id) REFERENCES candidato(id);
