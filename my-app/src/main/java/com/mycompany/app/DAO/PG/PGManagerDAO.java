package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.*;
import com.mycompany.app.Modelo.CategoriasEquipo;

import java.sql.*;
import java.util.List;

public class PGManagerDAO implements DAOManager{
    protected static Connection con = null;
    public Statement st;

    public PGManagerDAO(Connection con) {
        this.con = con;
    }

     private AulaDAO aulas = null;
     private AulaEquipoDAO aulasequipos = null;
     private CarreraDAO carreras = null;
     private CategoriasEquipoDAO categoriasEquipos = null;
     private DisponibilidadDAO disponibilidades = null;
     private EquipoDAO equipos = null;
     private GrupoDAO grupos = null;
     private GrupoMateriaProfesorDAO grupoMateriaProfesores = null;
     private LoginDAO logins = null;
     private MateriaDAO materias = null;
     private MateriaUsuarioDAO materiasUsuarios = null;
     private PlanEstudiosDAO planEstudios = null;
     private PrestamosDAO prestamos = null;
     private ProfesorDAO profesores = null;
     private UsoAulaGrupoDAO usoAulaGrupos = null;

     public PGManagerDAO(){
         try {
             Class.forName("org.postgresql.Driver");
             con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProyectoHorarios", "postgres","1234");
             if(con != null){
                 System.out.println("Conexion realizada");
                 st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
    @Override
    public AulaDAO getAlumnoDAO() {
        if(aulas==null){
            aulas = new PGAulaDAO(con);
        }
        return aulas;
    }

    @Override
    public AulaEquipoDAO getAulaEquipoDAO() {
        if (aulasequipos == null){
            aulasequipos = new PGAulaEquipoDAO(con);
        }
        return aulasequipos;
    }

    @Override
    public CarreraDAO getCarreraDAO() {
        if(carreras == null){
            carreras = new PGCarreraDAO(con);
        }
        return carreras;
    }

    @Override
    public CategoriasEquipoDAO getCategoriasEQuipoDAO() {
        if(this.categoriasEquipos == null){
            this.categoriasEquipos = new PGCategoriasEquipoDAO(con);
        }
        return this.categoriasEquipos;
    }

    @Override
    public DisponibilidadDAO getDisponibilidadDAO() {
        if (disponibilidades == null){
            disponibilidades = new PGDisponibilidadDAO(con);
        }
        return disponibilidades;
    }

    @Override
    public EquipoDAO getEquipoDAO() {
        if(equipos == null){
            equipos = new PGEquipoDAO(con);
        }
        return equipos;
    }

    @Override
    public GrupoDAO getGrupoDAO() {
        if(grupos == null){
            grupos = new PGGrupoDAO(con);
        }
        return grupos;
    }

    @Override
    public GrupoMateriaProfesorDAO getGrupoMateriaDAO() {
        if(grupoMateriaProfesores == null){
            grupoMateriaProfesores = new PGGmpDAO(con);
        }
        return grupoMateriaProfesores;
    }

    @Override
    public LoginDAO getLoginDAO() {
        if(logins == null){
            logins = new PgLoginDAO(con);
        }
        return logins;
    }

    @Override
    public MateriaDAO getMateriaDAO() {
        if(materias == null){
            materias = new PGMateriaDAO(con);
        }
        return materias;
    }

    @Override
    public MateriaUsuarioDAO getMateriaUsuarioDAO() {
        if(materiasUsuarios == null){
            materiasUsuarios = new PGMateriaUserDAO(con);
        }
        return materiasUsuarios;
    }

    @Override
    public PlanEstudiosDAO getPlanEstudiosDAO() {
        if (planEstudios == null) {
            planEstudios = new PGPlanEstudiosDAO(con);
        }
        return planEstudios;
    }

    @Override
    public PrestamosDAO getPrestamosDAO() {
        if(prestamos == null){
            prestamos = new PGPrestamosDAO(con);
        }
        return prestamos;
    }

    @Override
    public ProfesorDAO getProfesorDAO() {
        if(profesores == null){
            profesores = new PGProfesorDAO(con);
        }
        return profesores;
    }

    @Override
    public UsoAulaGrupoDAO getUsoAulaGrupoDAO() {
        if(usoAulaGrupos == null){
            usoAulaGrupos = new PGUsoAulaGrupoDAO(con);
        }
        return usoAulaGrupos;
    }

    public static void main3(String[] args) throws SQLException, DAOException {
        PGManagerDAO man = new PGManagerDAO(con);
        System.out.println("Paso 1");
        //Equipo e = new Equipo(12,1,"Mesa","Mesa de madera");

        CategoriasEquipo c = new CategoriasEquipo(1, "Equipo de computo", "AASSSFFGHJ");

        man.categoriasEquipos.Insertar(c);
        System.out.println("Paso 2");
        List<CategoriasEquipo> equipos = man.getCategoriasEQuipoDAO().All();
        System.out.println(equipos.get(1).getNombre());
    }
}
