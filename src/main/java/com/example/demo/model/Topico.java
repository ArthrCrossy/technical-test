package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topico_seq")
    @SequenceGenerator(name = "topico_seq", sequenceName = "topico_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Topico(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
