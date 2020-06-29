package com.mycompany.app.Modelo;

public class Aula {

    private String id_aula;
    private String nombre;
    private String tipo;
    private int capacidad;
    private String descripcion;
    private String ubicacion;

    public Aula(String id_aula, String nombre, String tipo, int capacidad, String descripcion, String ubicacion) {
        this.id_aula = id_aula;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
    }

    public String getId_aula() {
        return id_aula;
    }

    public void setId_aula(String id_aula) {
        this.id_aula = id_aula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
