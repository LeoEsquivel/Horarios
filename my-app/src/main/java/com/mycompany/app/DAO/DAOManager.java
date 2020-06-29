package com.mycompany.app.DAO;

public interface DAOManager {

    AulaDAO getAlumnoDAO();
    AulaEquipoDAO getAulaEquipoDAO();
    CarreraDAO getCarreraDAO();
    CategoriasEquipoDAO getCategoriasEQuipoDAO();
    DisponibilidadDAO getDisponibilidadDAO();
    EquipoDAO getEquipoDAO();
    GrupoDAO getGrupoDAO();
    GrupoMateriaProfesorDAO getGrupoMateriaDAO();
    LoginDAO getLoginDAO();
    MateriaDAO getMateriaDAO();
    MateriaUsuarioDAO getMateriaUsuarioDAO();
    PlanEstudiosDAO getPlanEstudiosDAO();
    PrestamosDAO getPrestamosDAO();
    ProfesorDAO getProfesorDAO();
    UsoAulaGrupoDAO getUsoAulaGrupoDAO();
}
