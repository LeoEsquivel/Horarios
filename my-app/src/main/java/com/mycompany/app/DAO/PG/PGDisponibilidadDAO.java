package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.DisponibilidadDAO;
import com.mycompany.app.Modelo.Disponibilidad;
import com.mycompany.app.Modelo.MateriaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGDisponibilidadDAO implements DisponibilidadDAO {

    static final String INSERT = "INSERT INTO disponibilidad VALUES (?, ?, ?)";
    static final String UPDATE = "UPDATE disponibilidad SET dia= ?, espacio_tiempo = ?, clv_usuario = ? WHERE dia= ? AND espacio_tiempo = ? AND clv_usuario = ?";
    static final String DELETE = "DELETE FROM disponibilidad WHERE dia= ? AND espacio_tiempo = ? AND clv_usuario = ?";
    static final String ALL = "SELECT * FROM disponibilidad";
    static final String BUSCAR = "SELECT * FROM disponibilidad WHERE dia= ? AND espacio_tiempo = ? AND clv_usuario = ?";

    Connection con;

    public PGDisponibilidadDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void Insertar(Disponibilidad p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(INSERT);
            ps.setInt(1, p.getId().getDia());
            ps.setInt(2, p.getId().getEspacio_tiempo());
            ps.setString(3, p.getId().getClv_usuario());
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
    public void Modificar(Disponibilidad p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(UPDATE);
            ps.setInt(1, p.getId().getDia());
            ps.setInt(2, p.getId().getEspacio_tiempo());
            ps.setString(3, p.getId().getClv_usuario());
            ps.setInt(4, p.getId().getDia());
            ps.setInt(5, p.getId().getEspacio_tiempo());
            ps.setString(6, p.getId().getClv_usuario());
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
    public void Eliminar(Disponibilidad p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, p.getId().getDia());
            ps.setInt(2, p.getId().getEspacio_tiempo());
            ps.setString(3, p.getId().getClv_usuario());
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

    private Disponibilidad CrearInstancia (ResultSet rs)throws SQLException{
        int dia = rs.getInt("dia");
        int esptie = rs.getInt("espacio_tiempo");
        String clvuser = rs.getString("clv_usuario");
        Disponibilidad d = new Disponibilidad();
        Disponibilidad.PKDis pk = new Disponibilidad.PKDis(dia, esptie, clvuser);
        d.setId(pk);
        return d;
    }

    @Override
    public List<Disponibilidad> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Disponibilidad> a = new ArrayList<>();
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
    public Disponibilidad Buscar(Disponibilidad.PKDis id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Disponibilidad a = null;
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
