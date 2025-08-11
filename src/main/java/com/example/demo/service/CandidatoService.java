package com.example.demo.service;

import com.example.demo.model.Candidato;
import com.example.demo.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Transactional(readOnly = true)
    public List<Candidato> listarTodos() {
        return candidatoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Candidato> buscarPorId(Long id) {
        return candidatoRepository.findById(id);
    }

    public Candidato salvar(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    public void deletar(Long id) {
        candidatoRepository.deleteById(id);
    }

    public Candidato atualizarDadosBasicos(Long id, String nome, String email, String telefone) {
        Optional<Candidato> candidatoOpt = buscarPorId(id);
        if (!candidatoOpt.isPresent()) {
            throw new IllegalArgumentException("Candidato não encontrado");
        }

        Candidato candidato = candidatoOpt.get();
        candidato.setNome(nome);
        candidato.setEmail(email);
        candidato.setTelefone(telefone);

        return salvar(candidato);
    }
}
