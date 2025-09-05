package com.example.demo.controller;

import com.example.demo.model.Entrevistas;
import com.example.demo.model.StatusEntrevista;
import com.example.demo.repository.EntrevistasRepository;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.EntrevistadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrevistas")
@CrossOrigin(origins = "*")
public class EntrevistasController {

    @Autowired
    private EntrevistasRepository entrevistasRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EntrevistadoresRepository entrevistadoresRepository;

    @GetMapping
    public ResponseEntity<List<Entrevistas>> listarTodas() {
        List<Entrevistas> entrevistas = entrevistasRepository.findAll();
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrevistas> buscarPorId(@PathVariable Long id) {
        Optional<Entrevistas> entrevista = entrevistasRepository.findById(id);
        return entrevista.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Entrevistas>> buscarPorStatus(@PathVariable StatusEntrevista status) {
        List<Entrevistas> entrevistas = entrevistasRepository.findByStatus(status);
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/candidato/{candidatoId}")
    public ResponseEntity<List<Entrevistas>> buscarPorCandidato(@PathVariable Long candidatoId) {
        return candidatoRepository.findById(candidatoId)
                .map(candidato -> {
                    List<Entrevistas> entrevistas = entrevistasRepository.findByCandidato(candidato);
                    return ResponseEntity.ok(entrevistas);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/entrevistador/{entrevistadorId}")
    public ResponseEntity<List<Entrevistas>> buscarPorEntrevistador(@PathVariable Long entrevistadorId) {
        return entrevistadoresRepository.findById(entrevistadorId)
                .map(entrevistador -> {
                    List<Entrevistas> entrevistas = entrevistasRepository.findByEntrevistador(entrevistador);
                    return ResponseEntity.ok(entrevistas);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/proximas")
    public ResponseEntity<List<Entrevistas>> buscarProximas() {
        List<Entrevistas> entrevistas = entrevistasRepository.findProximasEntrevistas(LocalDateTime.now());
        return ResponseEntity.ok(entrevistas);
    }

    @PostMapping
    public ResponseEntity<Entrevistas> criar(@RequestBody Entrevistas entrevista) {
        try {
            // Validar se candidato e entrevistador existem
            if (!candidatoRepository.existsById(entrevista.getCandidato().getId())) {
                return ResponseEntity.badRequest().build();
            }

            if (!entrevistadoresRepository.existsById(entrevista.getEntrevistador().getId())) {
                return ResponseEntity.badRequest().build();
            }

            Entrevistas novaEntrevista = entrevistasRepository.save(entrevista);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaEntrevista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrevistas> atualizar(@PathVariable Long id, @RequestBody Entrevistas entrevista) {
        Optional<Entrevistas> entrevistaExistente = entrevistasRepository.findById(id);

        if (entrevistaExistente.isPresent()) {
            Entrevistas entrevistaAtualizada = entrevistaExistente.get();

            if (entrevista.getDataEntrevista() != null) {
                entrevistaAtualizada.setDataEntrevista(entrevista.getDataEntrevista());
            }

            if (entrevista.getObservacoes() != null) {
                entrevistaAtualizada.setObservacoes(entrevista.getObservacoes());
            }

            if (entrevista.getStatus() != null) {
                entrevistaAtualizada.setStatus(entrevista.getStatus());
            }

            return ResponseEntity.ok(entrevistasRepository.save(entrevistaAtualizada));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Entrevistas> atualizarStatus(@PathVariable Long id, @RequestParam StatusEntrevista status) {
        Optional<Entrevistas> entrevistaExistente = entrevistasRepository.findById(id);

        if (entrevistaExistente.isPresent()) {
            Entrevistas entrevista = entrevistaExistente.get();
            entrevista.setStatus(status);
            return ResponseEntity.ok(entrevistasRepository.save(entrevista));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (entrevistasRepository.existsById(id)) {
            entrevistasRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
