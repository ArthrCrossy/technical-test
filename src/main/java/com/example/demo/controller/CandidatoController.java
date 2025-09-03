package com.example.demo.controller;

import com.example.demo.model.Candidato;
import com.example.demo.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidatos")
@CrossOrigin(origins = "*")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;

    @GetMapping
    public ResponseEntity<List<Candidato>> listarTodos() {
        List<Candidato> candidatos = candidatoService.listarTodos();
        return ResponseEntity.ok(candidatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> buscarPorId(@PathVariable Long id) {
        Optional<Candidato> candidato = candidatoService.buscarPorId(id);
        return candidato.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidato> criar(@RequestBody Candidato candidato) {
        Candidato novoCandidato = candidatoService.salvar(candidato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCandidato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidato> atualizar(@PathVariable Long id, @RequestBody Candidato candidato) {
        if (!candidatoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        candidato.setId(id);
        Candidato candidatoAtualizado = candidatoService.salvar(candidato);
        return ResponseEntity.ok(candidatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!candidatoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        candidatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}