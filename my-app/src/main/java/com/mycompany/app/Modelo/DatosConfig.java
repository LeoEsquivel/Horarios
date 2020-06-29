package com.mycompany.app.Modelo;

public class DatosConfig {
    private String gestor;
    private String user;
    private String passwort;
    private String baseDatos;
    private int host;
    private String rutaTemp;

    public DatosConfig(String gestor, String user, String passwort, String baseDatos, int host) {
        this.gestor = gestor;
        this.user = user;
        this.passwort = passwort;
        this.baseDatos = baseDatos;
        this.host = host;
    }

    public DatosConfig() {

    }

    public DatosConfig(String rutaTemp) {
        this.rutaTemp = rutaTemp;
    }

    public String getGestor() {
        return gestor;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public String getRutaTemp() {
        return rutaTemp;
    }

    public void setRutaTemp(String rutaTemp) {
        this.rutaTemp = rutaTemp;
    }
}
