package com.example.demo.controller;

import com.example.demo.model.Entrevistadores;
import com.example.demo.service.EntrevistadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrevistadores")
@CrossOrigin(origins = "*")
public class EntrevistadoresController {

    @Autowired
    private EntrevistadoresService entrevistadoresService;

    @GetMapping
    public ResponseEntity<List<Entrevistadores>> getAllEntrevistadores() {
        try {
            List<Entrevistadores> entrevistadores = entrevistadoresService.findAll();
            return new ResponseEntity<>(entrevistadores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrevistadores> getEntrevistadorById(@PathVariable Long id) {
        try {
            Optional<Entrevistadores> entrevistador = entrevistadoresService.findById(id);
            return entrevistador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
