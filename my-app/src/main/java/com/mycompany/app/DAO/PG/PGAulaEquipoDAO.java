package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.AulaEquipoDAO;
import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.Modelo.AulaEquipo;
import com.mycompany.app.Modelo.MateriaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGAulaEquipoDAO implements AulaEquipoDAO {

    static final String INSERT = "INSERT INTO aula_equipo VALUES (?, ?, ?)";
    static final String UPDATE = "UPDATE aula_equipo SET id_equipo = ?, id_aula = ?, cantidad = ? WHERE id_equipo = ? AND id_aula = ?";
    static final String DELETE = "DELETE FROM aula_equipo WHERE id_equipo = ?, id_aula = ?";
    static final String ALL = "SELECT * FROM aula_equipo";
    static final String BUSCAR = "SELECT * FROM aula_equipo WHERE id_equipo = ?, id_aula = ?";

    private Connection con;
    public PGAulaEquipoDAO(Connection con) {
        this.con = con ;
    }

    @Override
    public void Insertar(AulaEquipo p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(INSERT);
            ps.setInt(1, p.getPkAulaE().getId_equipo());
            ps.setString(2, p.getPkAulaE().getId_aula());
            ps.setInt(3, p.getCantidad());
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
    public void Modificar(AulaEquipo p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(UPDATE);
            ps.setInt(1, p.getPkAulaE().getId_equipo());
            ps.setString(2, p.getPkAulaE().getId_aula());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, p.getPkAulaE().getId_equipo());
            ps.setString(5, p.getPkAulaE().getId_aula());
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
    public void Eliminar(AulaEquipo p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, p.getPkAulaE().getId_equipo());
            ps.setString(2, p.getPkAulaE().getId_aula());
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Es posible que los datos no se hayan guardado correctamente.");
            }
        }catch (Exception e){
            throw new DAOException("Error en la sentencia SQL", e);
        }finally {
            if(ps!= null){
                try {
                    ps.close();
                } catch (Exception e) {
                    throw new DAOException("Error al cerrar");
                }
            }
        }
    }

    private AulaEquipo CrearInstancia (ResultSet rs) throws SQLException{
        String clvaula = rs.getString("id_aula");
        int clvequip = rs.getInt("id_equipo");
        int cant = rs.getInt("cantidad");

        AulaEquipo ae = new AulaEquipo();
        AulaEquipo.PKAulaE pk = new AulaEquipo.PKAulaE(clvequip, clvaula);
        ae.setPkAulaE(pk);
        ae.setCantidad(cant);
        return ae;

    }
    @Override
    public List<AulaEquipo> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AulaEquipo> a = new ArrayList<>();
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
    public AulaEquipo Buscar(AulaEquipo.PKAulaE id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        AulaEquipo a = null;
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
