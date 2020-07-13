package com.mycompany.app.Conexion;

import com.mycompany.app.Excepciones.Excepciones;
import com.mycompany.app.Modelo.DatosConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.sql.*;

public class Conexion {

    private static Conexion instancia;
    private Connection con;
    private String url = "";
    private String username = "";
    private String password = "";

    private String gestor, host, port, basedatos;

    private Conexion(File config){
        JSONObject obj;
        String linea = null;

        try {
            FileReader fileReader = new FileReader(config);
            BufferedReader bf = new BufferedReader(fileReader);

            while ((linea = bf.readLine())!= null){
                obj = (JSONObject) new JSONParser().parse(linea);
                if (obj.get("Gestor").toString().equals("None")){
                    this.url = obj.get("RutaTemp").toString();
                    if (url.equals("Memory")){
                        this.con = DriverManager.getConnection("jdbc:sqlite::memory:");
                    }else{
                        this.con = DriverManager.getConnection("jdbc:sqlite:"+url);
                    }
                }else{
                    this.host = obj.get("Host").toString();
                    this.port = obj.get("Puerto").toString();
                    this.basedatos = obj.get("Base de Datos").toString();
                    this.username = obj.get("Usuario").toString();
                    this.password = obj.get("Contrasena").toString();
                    if (obj.get("Gestor").toString().equals("Postgres")){
                        this.gestor = "postgresql";
                    }else if (obj.get("Gestor").toString().equals("MySQL")){
                        this.gestor = "mysql";
                    }
                    url = "jdbc:"+gestor+"://"+host+":"+port+"/"+basedatos;
                    this.con = DriverManager.getConnection(url, username, password);
                }

            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstance(File config){
        if (instancia == null){
            instancia = new Conexion(config);
        }
        return instancia;
    }

    public Connection getCon(){
        return con;
    }


    //conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/GestionHoteles", "postgres","1234");




}