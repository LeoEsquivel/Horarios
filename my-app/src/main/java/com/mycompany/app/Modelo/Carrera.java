package com.mycompany.app.Modelo;

public class Carrera {

    private int idcarrera;
    private String nombre_carrera;

    public Carrera(int idcarrera, String nombre_carrera) {
        this.idcarrera = idcarrera;
        this.nombre_carrera = nombre_carrera;
    }

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }
}
