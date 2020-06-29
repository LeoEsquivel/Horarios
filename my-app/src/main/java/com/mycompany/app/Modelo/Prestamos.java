package com.mycompany.app.Modelo;

public class Prestamos {

    public static class PKPres{
        private String clv_usuario;
        private int idcarrera;

        public PKPres(String clv_usuario, int idcarrera) {
            this.clv_usuario = clv_usuario;
            this.idcarrera = idcarrera;
        }

        public String getClv_usuario() {
            return clv_usuario;
        }

        public void setClv_usuario(String clv_usuario) {
            this.clv_usuario = clv_usuario;
        }

        public int getIdcarrera() {
            return idcarrera;
        }

        public void setIdcarrera(int idcarrera) {
            this.idcarrera = idcarrera;
        }
    }

    private PKPres pkPres;

    public PKPres getPkPres() {
        return pkPres;
    }

    public void setPkPres(PKPres pkPres) {
        this.pkPres = pkPres;
    }
}
