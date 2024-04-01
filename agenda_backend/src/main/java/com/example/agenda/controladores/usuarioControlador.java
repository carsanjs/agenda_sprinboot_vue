package com.example.agenda.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agenda.modelos.usuarioModelo;
import com.example.agenda.servicios.usuarioServicio;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class usuarioControlador {


    @Autowired
    usuarioServicio servicios;

    @GetMapping(path="/verUsuarios")
    public ArrayList<usuarioModelo> VerUsuarios() {  
       return servicios.consultarUsuarios();
    }

    @PostMapping(value = "/guardar")
    public String guardarUsuario(@RequestBody usuarioModelo usuario) {
        try {

            if (usuario != null) {
                Boolean respuesta = servicios.guardarUsuario(usuario);

                if (respuesta)
                    return "{\"success\": true, \"message\": \"Usuario almacenado correctamente.\"}";
                else
                    return "{\"success\": false, \"message\": \"No se pudo almacenar el usuario, revisa los parametros.\"}";
            } else
                return "{\"success\": false, \"message\": \"No se puede almacenar un usuario vacio.\"}";
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Ocurrio un error al hacer la consulta.\"}";
        }
    }

    @GetMapping(path = "/login/{correo}/{contrasena}")
   public ArrayList<usuarioModelo> login(@PathVariable("correo") String correo, @PathVariable("contrasena") String contrasena) {
      return servicios.Login(correo,contrasena);
   }

  

}
