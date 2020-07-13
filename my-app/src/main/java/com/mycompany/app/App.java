package com.mycompany.app;

import com.mycompany.app.Cargar.Start;
import com.mycompany.app.Excepciones.Excepciones;

import java.sql.SQLException;

public class App extends Start {

    public static void main(String args[]) throws Excepciones, SQLException {
        cargar();
    }
}
