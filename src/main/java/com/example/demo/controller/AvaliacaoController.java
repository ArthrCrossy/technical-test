package com.example.demo.controller;

import com.example.demo.model.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping
    public ResponseEntity<List<Avaliacao>> getAllAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        return avaliacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAvaliacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacaoDetails) {
        Optional<Avaliacao> avaliacaoOptional = avaliacaoRepository.findById(id);

        if (avaliacaoOptional.isPresent()) {
            Avaliacao avaliacao = avaliacaoOptional.get();
            avaliacao.setNota(avaliacaoDetails.getNota());
            avaliacao.setObservacoes(avaliacaoDetails.getObservacoes());
            avaliacao.setEntrevista(avaliacaoDetails.getEntrevista());
            avaliacao.setCandidato(avaliacaoDetails.getCandidato());
            avaliacao.setTopico(avaliacaoDetails.getTopico());

            Avaliacao updatedAvaliacao = avaliacaoRepository.save(avaliacao);
            return ResponseEntity.ok(updatedAvaliacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        if (avaliacaoRepository.existsById(id)) {
            avaliacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/entrevista/{entrevistaId}")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByEntrevista(@PathVariable Long entrevistaId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByEntrevistaId(entrevistaId);
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/candidato/{candidatoId}")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByCandidato(@PathVariable Long candidatoId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByCandidatoId(candidatoId);
        return ResponseEntity.ok(avaliacoes);
    }
}