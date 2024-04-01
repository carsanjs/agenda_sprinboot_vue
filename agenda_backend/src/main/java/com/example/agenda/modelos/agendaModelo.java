package com.example.agenda.modelos;

import org.springframework.data.annotation.Id;

public class agendaModelo {
    

    @Id
    private int id;
    private String titulo;
    private String fecha;
    private String contenido;


    public agendaModelo() {
    }


    public agendaModelo(int id, String titulo, String fecha, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.fecha = fecha;
        this.contenido = contenido;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getFecha() {
        return fecha;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getContenido() {
        return contenido;
    }


    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


    

    


    
}
