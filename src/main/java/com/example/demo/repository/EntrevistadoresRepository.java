package com.example.demo.repository;


import com.example.demo.model.Entrevistadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntrevistadoresRepository extends JpaRepository<Entrevistadores, Long> {



}
