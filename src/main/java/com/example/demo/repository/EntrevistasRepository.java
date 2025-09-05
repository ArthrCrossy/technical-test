package com.example.demo.repository;

import com.example.demo.model.Entrevistas;
import com.example.demo.model.StatusEntrevista;
import com.example.demo.model.Candidato;
import com.example.demo.model.Entrevistadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EntrevistasRepository extends JpaRepository<Entrevistas, Long> {

    List<Entrevistas> findByStatus(StatusEntrevista status);

    List<Entrevistas> findByCandidato(Candidato candidato);

    List<Entrevistas> findByEntrevistador(Entrevistadores entrevistador);

    List<Entrevistas> findByDataEntrevistaBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT e FROM Entrevistas e WHERE e.dataEntrevista >= :dataInicio ORDER BY e.dataEntrevista ASC")
    List<Entrevistas> findProximasEntrevistas(@Param("dataInicio") LocalDateTime dataInicio);

    @Query("SELECT e FROM Entrevistas e WHERE e.candidato.id = :candidatoId AND e.status = :status")
    List<Entrevistas> findByCandidatoIdAndStatus(@Param("candidatoId") Long candidatoId, @Param("status") StatusEntrevista status);
}
