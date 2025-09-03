ALTER TABLE avaliacoes
    ADD COLUMN topico_id BIGINT;

ALTER TABLE avaliacoes
    ADD CONSTRAINT fk_avaliacoes_topico
        FOREIGN KEY (topico_id) REFERENCES topicos(id);
