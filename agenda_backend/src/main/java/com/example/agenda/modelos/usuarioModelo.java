package com.example.agenda.modelos;

import org.springframework.data.annotation.Id;

public class usuarioModelo{


    @Id
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
     private String contrasena_2;


    public usuarioModelo() {
    }

    
    public usuarioModelo(int id, String nombre, String correo, String contrasena, String contrasena_2) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.contrasena_2 = contrasena_2;
    }



    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getContrasena() {
        return contrasena;
    }


    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public String getContrasena_2() {
        return contrasena_2;
    }


    public void setContrasena_2(String contrasena_2) {
        this.contrasena_2 = contrasena_2;
    }


    


    

    


}