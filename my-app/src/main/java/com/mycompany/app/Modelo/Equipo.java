package com.mycompany.app.Modelo;

public class Equipo {

    private int id;
    private String nombre;
    private String descripcion;
    private int id_categoria;

    public Equipo(int id, String nombre, String descripcion, int id_categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_categoria = id_categoria;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
