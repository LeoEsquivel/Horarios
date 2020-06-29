package com.mycompany.app.Modelo;

public class Disponibilidad {

    public Disponibilidad() {

    }

    public static class PKDis{
        private  int dia;
        private int espacio_tiempo;
        private String clv_usuario;

        public PKDis(int dia, int espacio_tiempo, String clv_usuario) {
            this.dia = dia;
            this.espacio_tiempo = espacio_tiempo;
            this.clv_usuario = clv_usuario;
        }

        public int getDia() {
            return dia;
        }

        public void setDia(int dia) {
            this.dia = dia;
        }

        public int getEspacio_tiempo() {
            return espacio_tiempo;
        }

        public void setEspacio_tiempo(int espacio_tiempo) {
            this.espacio_tiempo = espacio_tiempo;
        }

        public String getClv_usuario() {
            return clv_usuario;
        }

        public void setClv_usuario(String clv_usuario) {
            this.clv_usuario = clv_usuario;
        }
    }

    private PKDis id;
    public Disponibilidad(PKDis id){
        this.id = id;
    }

    public PKDis getId() {
        return id;
    }

    public void setId(PKDis id) {
        this.id = id;
    }
}
