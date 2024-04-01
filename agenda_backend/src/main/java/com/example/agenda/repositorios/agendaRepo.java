package com.example.agenda.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.agenda.modelos.agendaModelo;

public interface agendaRepo extends MongoRepository<agendaModelo, Integer> {
    
}
