package com.example.agenda.servicios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agenda.modelos.agendaModelo;
import com.example.agenda.repositorios.agendaRepo;

@Service
public class agendaServicio {


    @Autowired
    agendaRepo repositorio;


    public boolean guardarAgenda(agendaModelo Agenda){
        try {
            agendaModelo respuesta = repositorio.save(Agenda);
            if(respuesta != null)
                return true;
            else return false;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<agendaModelo> consultarAgendas(){
        try {
            return (ArrayList<agendaModelo>)repositorio.findAll();
        } catch (Exception e) {
            return null;
        }
    }


    public Optional<agendaModelo> consultarAgenda(Integer id){

        try {
            if(!repositorio.existsById(id))return null;
            else return repositorio.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminarAgenda(Integer id){
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
