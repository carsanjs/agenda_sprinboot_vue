package com.example.agenda.servicios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agenda.modelos.contactoModelo;
import com.example.agenda.repositorios.contactoRepo;

@Service
public class contactoServicio {


    @Autowired
    contactoRepo repositorio;


    public boolean guardarContacto(contactoModelo Contacto){

        try {
            contactoModelo respuesta = repositorio.save(Contacto);
            if(respuesta != null)
                return true;
            else return false;
        } catch (Exception e) {
            return false;
        }

    }

    public ArrayList<contactoModelo> consultarContactos(){
        try {
            return (ArrayList<contactoModelo>)repositorio.findAll();
        } catch (Exception e) {
            return null;
        }
    }


    public Optional<contactoModelo> consultarContacto(Integer id){

        try {
            if(!repositorio.existsById(id))return null;
            else return repositorio.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminarContacto(Integer id){
        try {
            if(repositorio.existsById(id)){
                repositorio.deleteById(id);
                return true;
            }else return false;
        } catch (Exception e) {
            return false;
        }
    }
    
}
