package com.mycompany.app.Modelo;

public class MateriaUsuario {

    public static class PKMatU{
        private String clv_materia;
        private String clv_plan;
        private String clv_usuario;

        public PKMatU(String clv_materia, String clv_plan, String clv_usuario) {
            this.clv_materia = clv_materia;
            this.clv_plan = clv_plan;
            this.clv_usuario = clv_usuario;
        }

        public String getClv_materia() {
            return clv_materia;
        }

        public void setClv_materia(String clv_materia) {
            this.clv_materia = clv_materia;
        }

        public String getClv_plan() {
            return clv_plan;
        }

        public void setClv_plan(String clv_plan) {
            this.clv_plan = clv_plan;
        }

        public String getClv_usuario() {
            return clv_usuario;
        }

        public void setClv_usuario(String clv_usuario) {
            this.clv_usuario = clv_usuario;
        }
    }

    private PKMatU pkMatU;
    private int puntos_confianza;
    private  int puntos_director;

    public MateriaUsuario(PKMatU pkMatU, int puntos_confianza, int puntos_director) {
        this.pkMatU = pkMatU;
        this.puntos_confianza = puntos_confianza;
        this.puntos_director = puntos_director;
    }

    public MateriaUsuario() {
    }

    public PKMatU getPkMatU() {
        return pkMatU;
    }

    public void setPkMatU(PKMatU pkMatU) {
        this.pkMatU = pkMatU;
    }

    public int getPuntos_confianza() {
        return puntos_confianza;
    }

    public void setPuntos_confianza(int puntos_confianza) {
        this.puntos_confianza = puntos_confianza;
    }

    public int getPuntos_director() {
        return puntos_director;
    }

    public void setPuntos_director(int puntos_director) {
        this.puntos_director = puntos_director;
    }
}
