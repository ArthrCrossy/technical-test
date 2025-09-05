package com.example.demo.repository;

import com.example.demo.model.Entrevistadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntrevistadoresRepository extends JpaRepository<Entrevistadores, Long> {

    List<Entrevistadores> findByNomeContainingIgnoreCase(String nome);

    Optional<Entrevistadores> findByEmail(String email);

    List<Entrevistadores> findByCargo(String cargo);

    List<Entrevistadores> findByCargoContainingIgnoreCase(String cargo);

    boolean existsByEmail(String email);

    List<Entrevistadores> findByCreatedAtBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Entrevistadores> findByCreatedAtAfter(LocalDateTime data);

    List<Entrevistadores> findByCargoOrderByNomeAsc(String cargo);

    @Query("SELECT DISTINCT e FROM Entrevistadores e JOIN e.entrevistas ent WHERE ent.status = 'AGENDADA'")
    List<Entrevistadores> findEntrevistadoresComEntrevistasAgendadas();

    @Query("SELECT e FROM Entrevistadores e WHERE e.nome ILIKE %:termo% OR e.email ILIKE %:termo%")
    List<Entrevistadores> buscarPorNomeOuEmail(@Param("termo") String termo);

    @Query("SELECT e.nome, COUNT(ent) FROM Entrevistadores e LEFT JOIN e.entrevistas ent GROUP BY e.id, e.nome")
    List<Object[]> contarEntrevistasPorEntrevistador();

    @Query("SELECT e FROM Entrevistadores e WHERE SIZE(e.entrevistas) >= :minEntrevistas")
    List<Entrevistadores> findEntrevistadoresComMinimoEntrevistas(@Param("minEntrevistas") int minEntrevistas);

    @Query("SELECT e FROM Entrevistadores e WHERE e.id NOT IN " +
            "(SELECT DISTINCT ent.entrevistador.id FROM Entrevistas ent " +
            "WHERE ent.dataEntrevista BETWEEN :inicio AND :fim AND ent.status = 'AGENDADA')")
    List<Entrevistadores> findEntrevistadoresDisponiveis(@Param("inicio") LocalDateTime inicio,
                                                         @Param("fim") LocalDateTime fim);

    @Query("SELECT e FROM Entrevistadores e WHERE e.cargo IN :cargos")
    List<Entrevistadores> findByCargos(@Param("cargos") List<String> cargos);

    @Query(value = "SELECT cargo, COUNT(*) as total FROM entrevistadores GROUP BY cargo ORDER BY total DESC",
            nativeQuery = true)
    List<Object[]> getEstatisticasPorCargo();

    List<Entrevistadores> findAllByOrderByCreatedAtDesc();

    Optional<Entrevistadores> findByNomeIgnoreCase(String nome);

    long countByCargo(String cargo);

    List<Entrevistadores> findTop5ByOrderByCreatedAtAsc();

    @Query("SELECT e FROM Entrevistadores e WHERE SIZE(e.entrevistas) = 0")
    List<Entrevistadores> findEntrevistadoresSemEntrevistas();
}