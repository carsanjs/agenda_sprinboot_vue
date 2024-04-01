package com.example.agenda.servicios;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agenda.modelos.usuarioModelo;
import com.example.agenda.repositorios.usuarioRepo;

@Service
public class usuarioServicio {


    @Autowired
    usuarioRepo repositorio;

    public boolean guardarUsuario(usuarioModelo usuario){

        try {
            usuarioModelo respuesta = repositorio.save(usuario);
            if(respuesta != null)
                return true;
            else return false;
        } catch (Exception e) {
            return false;
        }

    }

    public ArrayList<usuarioModelo> Login(String correo, String contrasena){
        ArrayList<usuarioModelo> login = repositorio.findByCorreoAndContrasena(correo,contrasena);
        return login;
     }

    public ArrayList<usuarioModelo> consultarUsuarios(){
        try {
            return (ArrayList<usuarioModelo>)repositorio.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    
}
