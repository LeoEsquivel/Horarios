package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.PrestamosDAO;
import com.mycompany.app.Modelo.Disponibilidad;
import com.mycompany.app.Modelo.Prestamos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGPrestamosDAO implements PrestamosDAO {

    static final String INSERT = "INSERT INTO prestamos VALUES (?, ?)";
    static final String UPDATE = "UPDATE prestamos SET clv_usuario = ?, idcarrera = ? WHERE clv_usuarios = ? AND idcarrera = ?";
    static final String DELETE = "DELETE FROM prestamos WHERE clv_usuarios = ? AND idcarrera = ?";
    static final String ALL = "SELECT * FROM prestamos";
    static final String BUSCAR = "SELECT * FROM prestamos WHERE clv_usuarios = ? AND idcarrera = ?";

    private Connection con;

    public PGPrestamosDAO(Connection con) {
        this.con = con ;
    }

    @Override
    public void Insertar(Prestamos p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getPkPres().getClv_usuario());
            ps.setInt(2, p.getPkPres().getIdcarrera());

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
    public void Modificar(Prestamos p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getPkPres().getClv_usuario());
            ps.setInt(2, p.getPkPres().getIdcarrera());
            ps.setString(3, p.getPkPres().getClv_usuario());
            ps.setInt(4, p.getPkPres().getIdcarrera());
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
    public void Eliminar(Prestamos p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(DELETE);
            ps.setString(1, p.getPkPres().getClv_usuario());
            ps.setInt(2, p.getPkPres().getIdcarrera());
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

    private Prestamos CrearInstancia (ResultSet rs) throws SQLException {
        String clvuser = rs.getString("clv_usuario");
        int idcar = rs.getInt("idcarrera");

        Prestamos p = new Prestamos();
        Prestamos.PKPres pk = new Prestamos.PKPres(clvuser, idcar);
        p.setPkPres(pk);
        return p;
    }

    @Override
    public List<Prestamos> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Prestamos> a = new ArrayList<>();
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
    public Prestamos Buscar(Prestamos.PKPres id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Prestamos a = null;
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
        return a;    }
}
