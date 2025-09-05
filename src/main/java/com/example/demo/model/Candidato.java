package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "candidato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidato_seq")
    @SequenceGenerator(name = "candidato_seq", sequenceName = "candidato_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrevistas> entrevistas;

    @OneToMany(mappedBy = "candidato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Candidato(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Candidato(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
}