package com.example.agenda.repositorios;

import java.util.ArrayList;
 import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.agenda.modelos.usuarioModelo;


public interface usuarioRepo extends MongoRepository<usuarioModelo, Long> {

    public Optional<usuarioModelo> findByCorreo(String correo);
    public ArrayList<usuarioModelo> findByCorreoAndContrasena(String correo, String contrasena);
}
