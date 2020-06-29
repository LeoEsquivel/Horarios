package com.mycompany.app.Conexion;

import com.mycompany.app.Modelo.DatosConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class ObtenerDatosConfiguracion {
    public ObtenerDatosConfiguracion(File Json){
        DatosConfig dc = new DatosConfig();
        JSONObject obj;
        String linea;

        try{
            FileReader fileReader = new FileReader(Json);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((linea = bufferedReader.readLine() )!= null) {
                obj = (JSONObject) new JSONParser().parse(linea);

                if (obj.get("Gestor").toString().equals("NONE")){
                        Conexion c = new Conexion(
                                obj.get("RutaTemp").toString()
                        );
                }else if(obj.get("Gestor").toString().equals("Postgres")){
                    DatosConfig config = new DatosConfig(
                            "postgresql",
                            obj.get("Usuario").toString(),
                            obj.get("Password").toString(),
                            obj.get("Base de Datos").toString(),
                            Integer.parseInt(obj.get("Host").toString())
                    );
                    Conexion c = new Conexion(config);
                }else if(obj.get("Gestor").toString().equals("MySQL")){
                    DatosConfig config = new DatosConfig(
                            "mysql",
                            obj.get("Usuario").toString(),
                            obj.get("Password").toString(),
                            obj.get("Base de Datos").toString(),
                            Integer.parseInt(obj.get("Host").toString())
                    );
                    Conexion c = new Conexion(config);

                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
