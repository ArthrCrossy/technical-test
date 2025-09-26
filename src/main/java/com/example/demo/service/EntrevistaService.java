package com.example.demo.service;

import com.example.demo.model.Candidato;
import com.example.demo.model.Entrevistadores;
import com.example.demo.model.Entrevistas;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.EntrevistadoresRepository;
import com.example.demo.repository.EntrevistasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntrevistaService {

    @Autowired
    private EntrevistasRepository entrevistasRepository;
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private EntrevistadoresRepository entrevistadoresRepository;

    @Transactional
    public Entrevistas criarEntrevista(Long candidatoId, Long entrevistadorId, String observacao) {
        Candidato candidato = candidatoRepository.findById(candidatoId)
                .orElseThrow(() -> new IllegalArgumentException("Candidato não encontrado"));

        Entrevistadores entrevistador = entrevistadoresRepository.findById(entrevistadorId)
                .orElseThrow(() -> new IllegalArgumentException("Entrevistador não encontrado"));

        Entrevistas entrevista = new Entrevistas();
        entrevista.setCandidato(candidato);
        entrevista.setEntrevistador(entrevistador);

        return entrevistasRepository.save(entrevista);
    }
}