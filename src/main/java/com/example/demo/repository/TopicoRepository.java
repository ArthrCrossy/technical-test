
package com.example.demo.repository;

import com.example.demo.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByAtivoTrue();

    List<Topico> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);

    @Query("SELECT t FROM Topico t WHERE t.ativo = :ativo ORDER BY t.createdAt DESC")
    List<Topico> findByAtivoOrderByCreatedAtDesc(@Param("ativo") Boolean ativo);
}
