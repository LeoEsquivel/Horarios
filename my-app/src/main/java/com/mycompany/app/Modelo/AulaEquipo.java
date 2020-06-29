package com.mycompany.app.Modelo;

public class AulaEquipo {

    public static class PKAulaE{
        private int id_equipo;
        private String id_aula;

        public PKAulaE(int id_equipo, String id_aula) {
            this.id_equipo = id_equipo;
            this.id_aula = id_aula;
        }

        public int getId_equipo() {
            return id_equipo;
        }

        public void setId_equipo(int id_equipo) {
            this.id_equipo = id_equipo;
        }

        public String getId_aula() {
            return id_aula;
        }

        public void setId_aula(String id_aula) {
            this.id_aula = id_aula;
        }
    }

    private PKAulaE pkAulaE;
    private int cantidad;

    public AulaEquipo(PKAulaE pkAulaE, int cantidad) {
        this.pkAulaE = pkAulaE;
        this.cantidad = cantidad;
    }

    public AulaEquipo() {

    }

    public PKAulaE getPkAulaE() {
        return pkAulaE;
    }

    public void setPkAulaE(PKAulaE pkAulaE) {
        this.pkAulaE = pkAulaE;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
