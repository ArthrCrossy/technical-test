package com.example.demo.controller;

import com.example.demo.model.Topico;
import com.example.demo.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
@CrossOrigin(origins = "*")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @GetMapping
    public ResponseEntity<List<Topico>> listarTodos() {
        List<Topico> topicos = topicoRepository.findAll();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Topico>> listarAtivos() {
        List<Topico> topicos = topicoRepository.findByAtivoTrue();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscarPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        return topico.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Topico>> buscarPorNome(@RequestParam String nome) {
        List<Topico> topicos = topicoRepository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome);
        return ResponseEntity.ok(topicos);
    }

    @PostMapping
    public ResponseEntity<Topico> criar(@RequestBody Topico topico) {
        try {
            Topico novoTopico = topicoRepository.save(topico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoTopico);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody Topico topico) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id);

        if (topicoExistente.isPresent()) {
            Topico topicoAtualizado = topicoExistente.get();
            topicoAtualizado.setNome(topico.getNome());
            topicoAtualizado.setDescricao(topico.getDescricao());
            topicoAtualizado.setAtivo(topico.getAtivo());

            return ResponseEntity.ok(topicoRepository.save(topicoAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Topico> desativar(@PathVariable Long id) {
        Optional<Topico> topicoExistente = topicoRepository.findById(id);

        if (topicoExistente.isPresent()) {
            Topico topico = topicoExistente.get();
            topico.setAtivo(false);
            return ResponseEntity.ok(topicoRepository.save(topico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}