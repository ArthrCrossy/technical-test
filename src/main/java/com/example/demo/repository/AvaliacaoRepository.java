package com.example.demo.repository;

import com.example.demo.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByEntrevistaId(Long entrevistaId);

    List<Avaliacao> findByCandidatoId(Long candidatoId);

    List<Avaliacao> findByTopicoId(Long topicoId);

    List<Avaliacao> findByCandidatoIdAndEntrevistaId(Long candidatoId, Long entrevistaId);

    List<Avaliacao> findByNotaGreaterThanEqual(Float nota);

    List<Avaliacao> findByNotaLessThanEqual(Float nota);

    List<Avaliacao> findByNotaBetween(Float notaMin, Float notaMax);

    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.candidato.id = :candidatoId")
    Float findAverageNotaByCandidatoId(@Param("candidatoId") Long candidatoId);

    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.entrevista.id = :entrevistaId")
    Float findAverageNotaByEntrevistaId(@Param("entrevistaId") Long entrevistaId);

    @Query("SELECT a FROM Avaliacao a WHERE a.observacoes IS NOT NULL AND a.observacoes != ''")
    List<Avaliacao> findAvaliacoesWithObservacoes();

    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.candidato.id = :candidatoId")
    Long countByCandidatoId(@Param("candidatoId") Long candidatoId);

    @Query("SELECT COUNT(a) FROM Avaliacao a WHERE a.entrevista.id = :entrevistaId")
    Long countByEntrevistaId(@Param("entrevistaId") Long entrevistaId);

    @Query("SELECT a FROM Avaliacao a ORDER BY a.nota DESC")
    List<Avaliacao> findTopAvaliacoesByNota();

    List<Avaliacao> findAllByOrderByCreatedAtDesc();

    List<Avaliacao> findByTopicoIdOrderByNotaDesc(Long topicoId);
}