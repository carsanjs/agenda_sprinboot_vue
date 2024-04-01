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

import com.example.agenda.modelos.contactoModelo;
import com.example.agenda.servicios.contactoServicio;
import com.google.gson.Gson;

@RestController
@RequestMapping("/Contactos")
@CrossOrigin("*")
public class contactoControlador {
    

    @Autowired
    contactoServicio servicios;


    @GetMapping(path="/vercontactos")
    public ArrayList<contactoModelo> VerUsuarios() {  
       return servicios.consultarContactos();
    }


    @GetMapping("/Contacto/{id}")
    public String consultarContacto(@PathVariable("id") Integer id) {
        try {
            Optional<contactoModelo> Contacto = servicios.consultarContacto(id);

            Gson json = new Gson();

            if (Contacto == null)
                return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
            else {

                return "{\"success\": true, \"message\": \"Consulta realizada correctamente.\", \"data\": "
                        + json.toJson(Contacto.get())
                        + "}";
            }
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
        }
    }

    @PostMapping(value = "/guardar")
    public String guardarContacto(@RequestBody contactoModelo Contacto) {
        try {

            if (Contacto != null) {
                Boolean respuesta = servicios.guardarContacto(Contacto);

                if (respuesta)
                    return "{\"success\": true, \"message\": \"Contacto almacenado correctamente.\"}";
                else
                    return "{\"success\": false, \"message\": \"No se pudo almacenar el Contacto, revisa los parametros.\"}";
            } else
                return "{\"success\": false, \"message\": \"No se puede almacenar un Contacto vacio.\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
        }
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public String eliminarContacto(@PathVariable Integer id) {
        try {
            boolean response = servicios.eliminarContacto(id);
            if (response)
                return "{\"success\": true, \"message\": \"Contacto eliminado correctamente.\"}";
            else
                return "{\"success\": false, \"message\": \"No se pudo eliminar el Contacto, revisa los parametros.\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
        }
    }
}
