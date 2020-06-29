package com.mycompany.app.DAO;

import com.mycompany.app.Modelo.Profesor;

import java.util.List;

public interface DAO <T, K> { //T=Clase guardada, K=Identificador(PK)
    void Insertar(T p) throws DAOException;

    void Modificar(T p) throws DAOException;

    void Eliminar(T p) throws DAOException;

    List<T> All() throws DAOException;

    T Buscar(K id) throws DAOException;
}
