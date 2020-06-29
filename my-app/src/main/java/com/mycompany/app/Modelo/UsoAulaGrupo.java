package com.mycompany.app.Modelo;

public class UsoAulaGrupo {

    public UsoAulaGrupo() {

    }

    public static class PKUag{
        private int dia;
        private int espacio_tiempo;
        private String id_aula;
        private String clv_grupo;
        private String clv_materia;

        public PKUag(int dia, int espacio_tiempo, String id_aula, String clv_grupo, String clv_materia) {
            this.dia = dia;
            this.espacio_tiempo = espacio_tiempo;
            this.id_aula = id_aula;
            this.clv_grupo = clv_grupo;
            this.clv_materia = clv_materia;
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

        public String getId_aula() {
            return id_aula;
        }

        public void setId_aula(String id_aula) {
            this.id_aula = id_aula;
        }

        public String getClv_grupo() {
            return clv_grupo;
        }

        public void setClv_grupo(String clv_grupo) {
            this.clv_grupo = clv_grupo;
        }

        public String getClv_materia() {
            return clv_materia;
        }

        public void setClv_materia(String clv_materia) {
            this.clv_materia = clv_materia;
        }
    }

    private PKUag pkUag;

    public UsoAulaGrupo(PKUag pkUag) {
        this.pkUag = pkUag;
    }

    public PKUag getPkUag() {
        return pkUag;
    }

    public void setPkUag(PKUag pkUag) {
        this.pkUag = pkUag;
    }
}
