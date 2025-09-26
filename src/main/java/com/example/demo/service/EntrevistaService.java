package com.example.demo.service;

import com.example.demo.model.Candidato;
import com.example.demo.model.Entrevistadores;
import com.example.demo.model.Entrevistas;
import com.example.demo.model.StatusEntrevista;
import com.example.demo.repository.CandidatoRepository;
import com.example.demo.repository.EntrevistadoresRepository;
import com.example.demo.repository.EntrevistasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntrevistaService{

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EntrevistadoresRepository entrevistasRepository;


}
