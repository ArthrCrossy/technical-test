package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "entrevistadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrevistadores {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entrevistadores_seq")
    @SequenceGenerator(name = "entrevistadores_seq", sequenceName = "entrevistadores_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(length = 50)
    private String cargo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "entrevistador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrevistas> entrevistas;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
