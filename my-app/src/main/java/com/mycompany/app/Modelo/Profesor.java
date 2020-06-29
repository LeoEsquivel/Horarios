package com.mycompany.app.Modelo;

public class Profesor {

    private String clv_usuario;
    private int idcarrera;
    private String nombre_usuario;
    private String nivel_ads;
    private String contrato;
    private String passwort;

    public Profesor(String clv_usuario, int idcarrera, String nombre_usuario, String nivel_ads, String contrato){
        this.clv_usuario = clv_usuario;
        this.idcarrera = idcarrera;
        this.nombre_usuario = nombre_usuario;
        this.nivel_ads = nivel_ads;
        this.contrato = contrato;
    }

    public Profesor(String clv_usuario, String passwort){
        this.clv_usuario = clv_usuario;
        this.passwort = passwort;
    }

    public Profesor() {

    }

    public String getClv_usuario(){
        return clv_usuario;
    }

    public void setClv_usuario(String clv_usuario){
        this.clv_usuario = clv_usuario;
    }

    public int getIdcarrera(){
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera){
        this.idcarrera = idcarrera;
    }

    public String getNombre_usuario(){
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario){
        this.nombre_usuario = nombre_usuario;
    }

    public String getNivel_ads(){
        return nivel_ads;
    }

    public void setNivel_ads(String nivel_ads){
        this.nivel_ads = nivel_ads;
    }

    public String getContrato(){
        return contrato;
    }

    public void setContrato(String contrato){
        this.contrato = contrato;
    }








}
