
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "entrevistas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrevistas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entrevistas_seq")
    @SequenceGenerator(name = "entrevistas_seq", sequenceName = "entrevistas_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrevistador_id", nullable = false)
    private Entrevistadores entrevistador;

    @Column(name = "data_entrevista", nullable = false)
    private LocalDateTime dataEntrevista;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEntrevista status = StatusEntrevista.AGENDADA;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "entrevista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = StatusEntrevista.AGENDADA;
        }
    }

    public Entrevistas(Candidato candidato, Entrevistadores entrevistador, LocalDateTime dataEntrevista) {
        this.candidato = candidato;
        this.entrevistador = entrevistador;
        this.dataEntrevista = dataEntrevista;
    }

}