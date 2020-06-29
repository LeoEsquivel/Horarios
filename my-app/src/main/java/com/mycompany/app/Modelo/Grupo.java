package com.mycompany.app.Modelo;

public class Grupo {

    private String clv_grupo;
    private Boolean turno;

    public Grupo(String clv_grupo, Boolean turno) {
        this.clv_grupo = clv_grupo;
        this.turno = turno;
    }

    public String getClv_grupo() {
        return clv_grupo;
    }

    public void setClv_grupo(String clv_grupo) {
        this.clv_grupo = clv_grupo;
    }

    public Boolean getTurno() {
        return turno;
    }

    public void setTurno(Boolean turno) {
        this.turno = turno;
    }
}
