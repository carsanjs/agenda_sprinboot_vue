package com.example.agenda.controladores;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agenda.modelos.agendaModelo;
import com.example.agenda.servicios.agendaServicio;
import com.google.gson.Gson;

@RestController
@RequestMapping("/Agendas")
@CrossOrigin("*")
public class agendaControlador {


    @Autowired
    agendaServicio servicios;

    @GetMapping(path="/verNotas")
    public ArrayList<agendaModelo> VerNotas() {  
       return servicios.consultarAgendas();
    }
    
    @GetMapping("/Agenda/{id}")
    public String consultarAgenda(@PathVariable("id") Integer id) {
        try {
            Optional<agendaModelo> Agenda = servicios.consultarAgenda(id);

            Gson json = new Gson();

            if (Agenda == null)
                return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
            else {

                return "{\"success\": true, \"message\": \"Consulta realizada correctamente.\", \"data\": "
                        + json.toJson(Agenda.get())
                        + "}";
            }
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
        }
    }

    @PostMapping(value = "/guardar")
    public String guardarAgenda(@RequestBody agendaModelo Agenda) {
        try {

            if (Agenda != null) {
                Boolean respuesta = servicios.guardarAgenda(Agenda);

                if (respuesta)
                    return "{\"success\": true, \"message\": \"Agenda almacenado correctamente.\"}";
                else
                    return "{\"success\": false, \"message\": \"No se pudo almacenar el Agenda, revisa los parametros.\"}";
            } else
                return "{\"success\": false, \"message\": \"No se puede almacenar un Agenda vacio.\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
        }
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public String eliminarAgenda(@PathVariable Integer id) {
        try {
            boolean response = servicios.eliminarAgenda(id);
            if (response)
                return "{\"success\": true, \"message\": \"Agenda eliminado correctamente.\"}";
            else
                return "{\"success\": false, \"message\": \"No se pudo eliminar el Agenda, revisa los parametros.\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
        }
    }
    
}
