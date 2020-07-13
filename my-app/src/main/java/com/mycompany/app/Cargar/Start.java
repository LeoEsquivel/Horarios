package com.mycompany.app.Cargar;

import com.mycompany.app.Conexion.Conexion;
import com.mycompany.app.DAO.DAO;
import com.mycompany.app.DAO.PG.PGAulaDAO;
import com.mycompany.app.Excepciones.Excepciones;
import com.mycompany.app.Modelo.Aula;
import com.mycompany.app.ODATA.ExcelBueno;

import java.io.File;
import java.sql.SQLException;

public class Start {
    private final static File Excel = new File("src/main/Resources/Libro1.xlsx");
    private final static File Json = new File("src/main/Resources/Datos.json");
    private final static File Config = new File("src/main/Resources/Config.json");

    public static void cargar() throws Excepciones, SQLException {
        //Conexion c = Conexion.getInstance(Config);
        //JSONData j = new JSONData();
        //j.LeerJson(Json);
        ExcelBueno l = new ExcelBueno(Excel);


    }
}
