package com.mycompany.app.Modelo;

public class Login {

    private String clv_usuario;
    private String pass_usuario;
    private String tipo_usuario;

    public Login(String clv_usuario, String pass_usuario, String tipo_usuario) {
        this.clv_usuario = clv_usuario;
        this.pass_usuario = pass_usuario;
        this.tipo_usuario = tipo_usuario;
    }

    public String getClv_usuario() {
        return clv_usuario;
    }

    public void setClv_usuario(String clv_usuario) {
        this.clv_usuario = clv_usuario;
    }

    public String getPass_usuario() {
        return pass_usuario;
    }

    public void setPass_usuario(String pass_usuario) {
        this.pass_usuario = pass_usuario;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
}
