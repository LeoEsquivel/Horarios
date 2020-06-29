package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.ProfesorDAO;
import com.mycompany.app.Modelo.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGProfesorDAO implements ProfesorDAO {

    final String INSERT = "INSERT INTO usuarios VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE usuarios SET clv_usuario = ?, idcarrera = ?, nombre_usuario = ?, nivel_ads = ?, contrato = ? WHERE clv_usuario = ?";
    final String DELETE = "DELETE FROM usuarios WHERE clv_usuario = ?";
    final String ALL = "SELECT * FROM usuarios";
    final String BUSCAR = "SELECT * FROM usuarios WHERE clv_usuario = ?";

    private Connection con;

    public PGProfesorDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void Insertar(Profesor p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getClv_usuario());
               ps.setInt(2, p.getIdcarrera());
            ps.setString(3, p.getNombre_usuario());
            ps.setString(4, p.getNivel_ads());
            ps.setString(5, p.getContrato());
            if (ps.executeUpdate() == 0) {
                throw new DAOException("La informacion es posible que no se haya guardado");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en la sentencia SQL", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException("Error al cerrar", e);
                }
            }

        }
    }

    @Override
    public void Modificar(Profesor p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getClv_usuario());
            ps.setInt(2, p.getIdcarrera());
            ps.setString(3, p.getNombre_usuario());
            ps.setString(4, p.getNivel_ads());
            ps.setString(5, p.getContrato());
            ps.setString(6, p.getClv_usuario());
            if (ps.executeUpdate() == 0) {
                throw new DAOException("La informacion es posible que no se haya guardado");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en la sentencia SQL", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException("Error al cerrar", e);
                }
            }
        }

    }

    @Override
    public void Eliminar(Profesor p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DELETE);
            ps.setString(1, p.getClv_usuario());
            if (ps.executeUpdate() == 0) {
                throw new DAOException("La informacion es posible que no se haya guardado");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en la sentencia SQL", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException("Error al cerrar", e);
                }
            }
        }
    }

    private Profesor Crearinstancia(ResultSet rs) throws SQLException {
        String clv = rs.getString("clv_usuario");
        int idcarrera = rs.getInt("idcarrera");
        String nameuser = rs.getString("nombre_usuario");
        String nivelads = rs.getString("nivel_ads");
        String contrato = rs.getString("contrato");
        Profesor prof = new Profesor();
        return prof;
    }

    @Override
    public List<Profesor> All() throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Profesor> p = new ArrayList<>();
        try {
            ps = con.prepareStatement(ALL);
            rs = ps.executeQuery();
            while (rs.next()){
                p.add(Crearinstancia(rs));
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
        return p;
    }

    @Override
    public Profesor Buscar(String clv) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Profesor p = null;
        try {
            ps = con.prepareStatement(BUSCAR);
            ps.setString(1, clv);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = Crearinstancia(rs);
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
        return p;
    }
}