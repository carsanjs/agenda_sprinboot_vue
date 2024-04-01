package com.example.agenda.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.agenda.modelos.contactoModelo;

public interface contactoRepo extends MongoRepository<contactoModelo, Integer>{
    
}
