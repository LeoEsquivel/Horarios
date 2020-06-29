package com.mycompany.app.Modelo;

public class GrupoMateriaProfesor {

    public GrupoMateriaProfesor() {

    }

    public static class PKGMP{
        private String clv_grupo;
        private String clv_materia;
        private String clv_usuario;

        public PKGMP(String clv_grupo, String clv_materia, String clv_usuario) {
            this.clv_grupo = clv_grupo;
            this.clv_materia = clv_materia;
            this.clv_usuario = clv_usuario;
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

        public String getClv_usuario() {
            return clv_usuario;
        }

        public void setClv_usuario(String clv_usuario) {
            this.clv_usuario = clv_usuario;
        }
    }
    private PKGMP PKgmp;
    public GrupoMateriaProfesor(PKGMP PKgmp){
        this.PKgmp = PKgmp;
    }

    public PKGMP getPKgmp() {
        return PKgmp;
    }

    public void setPKgmp(PKGMP PKgmp) {
        this.PKgmp = PKgmp;
    }
}
