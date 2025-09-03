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

}
