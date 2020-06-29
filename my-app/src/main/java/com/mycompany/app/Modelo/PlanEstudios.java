package com.mycompany.app.Modelo;

public class PlanEstudios {
    private String clv_plan;
    private String nombre_plan;
    private String nivel;
    private int id_carrera;

    public PlanEstudios(String clv_plan, String nombre_plan, String nivel, int id_carrera) {
        this.clv_plan = clv_plan;
        this.nombre_plan = nombre_plan;
        this.nivel = nivel;
        this.id_carrera = id_carrera;
    }

    public String getClv_plan() {
        return clv_plan;
    }

    public void setClv_plan(String clv_plan) {
        this.clv_plan = clv_plan;
    }

    public String getNombre_plan() {
        return nombre_plan;
    }

    public void setNombre_plan(String nombre_plan) {
        this.nombre_plan = nombre_plan;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }
}
