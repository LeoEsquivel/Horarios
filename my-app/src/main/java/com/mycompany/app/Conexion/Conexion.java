package com.mycompany.app.Conexion;

import com.mycompany.app.Modelo.DatosConfig;

import java.sql.*;

public class Conexion {

    private Connection con;

    public Conexion(){
        super();
    }

    public Conexion(DatosConfig dc){
        try{
            con = DriverManager.getConnection("jdbc:"+dc.getGestor()+"://localhost:"+dc.getHost()+"/"+dc.getBaseDatos()+"", dc.getUser(), dc.getPasswort());
            if(con != null){
                System.out.println("Conexion realiazada en "+dc.getGestor());
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Conexion(String url){
        try{
            if(url.equals("Memory")){
                con = DriverManager.getConnection("jdbc:sqlite::memory:");
            }else{
                con = DriverManager.getConnection("jdbc:sqlite:"+url);
                System.out.println("Conexion realizada en SQLite");
                System.out.println("Ruta de la base de datos: "+ url);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getCon (){
        return con;
    }


}