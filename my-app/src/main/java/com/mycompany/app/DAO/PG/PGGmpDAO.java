package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.GrupoMateriaProfesorDAO;
import com.mycompany.app.Modelo.Disponibilidad;
import com.mycompany.app.Modelo.GrupoMateriaProfesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGGmpDAO implements GrupoMateriaProfesorDAO {

    static final String INSERT = "INSERT INTO grupo_materia_profesor VALUES (?, ?, ?)";
    static final String UPDATE = "UPDATE grupo_materia_profesor SET clv_grupo= ?, clv_materia = ?, clv_usuario = ? WHERE clv_grupo= ? AND clv_materia = ? AND clv_usuario = ?";
    static final String DELETE = "DELETE FROM grupo_materia_profesor WHERE clv_grupo= ? AND clv_materia = ? AND clv_usuario = ?";
    static final String ALL = "SELECT * FROM grupo_materia_profesor";
    static final String BUSCAR = "SELECT * FROM grupo_materia_profesor WHERE clv_grupo= ? AND clv_materia = ? AND clv_usuario = ?";

    Connection con;

    public PGGmpDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void Insertar(GrupoMateriaProfesor p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getPKgmp().getClv_grupo());
            ps.setString(2, p.getPKgmp().getClv_materia());
            ps.setString(3, p.getPKgmp().getClv_usuario());
            if(ps.executeUpdate()== 0){
                throw new DAOException("Es posible que los datos no hayan sido guardaos.");
            }
        }catch (Exception e){
            throw new DAOException("Error en la sentencia SQL", e);
        }finally {
            if (ps != null){
                try {
                    ps.close();
                } catch (Exception e) {
                    throw new DAOException("Error al cerrar.", e);
                }
            }
        }
    }

    @Override
    public void Modificar(GrupoMateriaProfesor p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getPKgmp().getClv_grupo());
            ps.setString(2, p.getPKgmp().getClv_materia());
            ps.setString(3, p.getPKgmp().getClv_usuario());
            ps.setString(4, p.getPKgmp().getClv_grupo());
            ps.setString(5, p.getPKgmp().getClv_materia());
            ps.setString(6, p.getPKgmp().getClv_usuario());
            if(ps.executeUpdate()== 0){
                throw new DAOException("Es posible que los datos no hayan sido guardaos.");
            }
        }catch (Exception e){
            throw new DAOException("Error en la sentencia SQL", e);
        }finally {
            if (ps != null){
                try {
                    ps.close();
                } catch (Exception e) {
                    throw new DAOException("Error al cerrar.", e);
                }
            }
        }
    }

    @Override
    public void Eliminar(GrupoMateriaProfesor p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(DELETE);
            ps.setString(1, p.getPKgmp().getClv_grupo());
            ps.setString(2, p.getPKgmp().getClv_materia());
            ps.setString(3, p.getPKgmp().getClv_usuario());
            if(ps.executeUpdate()== 0){
                throw new DAOException("Es posible que los datos no hayan sido guardaos.");
            }
        }catch (Exception e){
            throw new DAOException("Error en la sentencia SQL", e);
        }finally {
            if (ps != null){
                try {
                    ps.close();
                } catch (Exception e) {
                    throw new DAOException("Error al cerrar.", e);
                }
            }
        }
    }

    private GrupoMateriaProfesor CrearInstancia (ResultSet rs) throws SQLException{
        String clvgrup = rs.getString("clv_grupo");
        String clvmat = rs.getString("clv_materia");
        String clvuser = rs.getString("clv_usuario");

        GrupoMateriaProfesor gmp = new GrupoMateriaProfesor();
        GrupoMateriaProfesor.PKGMP pk = new GrupoMateriaProfesor.PKGMP(clvgrup, clvmat, clvuser);
        gmp.setPKgmp(pk);
        return gmp;
    }

    @Override
    public List<GrupoMateriaProfesor> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GrupoMateriaProfesor> a = new ArrayList<>();
        try {
            ps = con.prepareStatement(ALL);
            rs = ps.executeQuery();
            while (rs.next()){
                a.add(CrearInstancia(rs));
            }
        } catch (Exception e) {
            throw new DAOException("Error es la sentencia SQL", e);
        } finally {
            if (rs != null || ps != null) {
                try {
                    rs.close();
                    ps.close();
                }catch (Exception e) {
                    throw new DAOException("No ha podido cerrar correctamente");
                }
            }
        }
        return a;
    }

    @Override
    public GrupoMateriaProfesor Buscar(GrupoMateriaProfesor.PKGMP id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        GrupoMateriaProfesor a = null;
        try {
            ps = con.prepareStatement(BUSCAR);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                a = CrearInstancia(rs);
            } else {
                throw new DAOException("No se han encontrado el registro");
            }
        } catch (Exception e) {
            throw new DAOException("Error es la sentencia SQL", e);
        } finally {
            if (rs != null || ps != null) {
                try {
                    rs.close();
                    ps.close();
                }catch (Exception e) {
                    throw new DAOException("No ha podido cerrar correctamente");
                }
            }
        }
        return a;
    }
}
