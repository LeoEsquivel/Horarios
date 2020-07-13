package com.mycompany.app.DAO;

import com.mycompany.app.Excepciones.Excepciones;

import java.util.List;

public interface DAO <T, K> { //T=Clase guardada, K=Identificador(PK)
    void Insertar(T p) throws Excepciones;

    void Modificar(T p) throws Excepciones;

    void Eliminar(T p) throws Excepciones;

    List<T> All() throws Excepciones;

    T Buscar(K id) throws Excepciones;
}
