package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrevista_id", nullable = false)
    private Entrevistas entrevista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidatos_id", nullable = false)
    private Candidato candidato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Column(nullable = false)
    private int nota;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Avaliacao(Entrevistas entrevista, Candidato candidato, Topico topico, int nota) {
        this.entrevista = entrevista;
        this.candidato = candidato;
        this.topico = topico;
        this.nota = nota;
    }
}