package com.example.demo.service;

import com.example.demo.model.Entrevistadores;
import com.example.demo.repository.EntrevistadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntrevistadoresService {

    @Autowired
    private EntrevistadoresRepository entrevistadoresRepository;

    @Transactional(readOnly = true)
    public List<Entrevistadores> findAll() {
        return entrevistadoresRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Entrevistadores> findById(Long id) {
        return entrevistadoresRepository.findById(id);
    }

    @Transactional()
    public Entrevistadores save(Entrevistadores entrevistador) {
        return entrevistadoresRepository.save(entrevistador);
    }

    @Transactional()
    public void deleteById(Long id) {
        entrevistadoresRepository.deleteById(id);
    }

    @Transactional()
    public List<Entrevistadores> findByNomeContaining(String entrevistador){
        return entrevistadoresRepository.buscarPorNomeOuEmail(entrevistador);
    }


}
