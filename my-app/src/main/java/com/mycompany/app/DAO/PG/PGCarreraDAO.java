package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.CarreraDAO;
import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.Modelo.Carrera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGCarreraDAO implements CarreraDAO {

    static final String INSERT = "INSERT INTO carrera VALUES (?, ?)";
    static final String UPDATE = "UPDATE carrera SET idcarrera = ?, nombre_carrera = ? WHERE idcarrera = ?";
    static final String DELETE = "DELETE FROM carrera WHERE idcarrera = ?";
    static final String ALL = "SELECT * FROM carrera";
    static final String BUSCAR = "SELECT * FROM carrera WHERE idcarrera = ?";

    Connection con;

    public PGCarreraDAO(Connection con){
        this.con = con;
    }

    @Override
    public void Insertar(Carrera p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(INSERT);
               ps.setInt(1, p.getIdcarrera());
            ps.setString(2, p.getNombre_carrera());
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
    public void Modificar(Carrera p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setInt(1, p.getIdcarrera());
            ps.setString(2, p.getNombre_carrera());
            ps.setInt(3, p.getIdcarrera());
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
    public void Eliminar(Carrera p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, p.getIdcarrera());
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

    private Carrera CrearInstancia(ResultSet rs) throws SQLException{
        int id = rs.getInt("idcarrera");
        String nombreCar = rs.getString("nombre_carrera");
        Carrera c = new Carrera(id, nombreCar);
        return  c;
    }

    @Override
    public List<Carrera> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Carrera> c = new ArrayList<>();
        try {
            ps = con.prepareStatement(ALL);
            rs = ps.executeQuery();
            if (rs.next()) {
                CrearInstancia(rs);
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
        return c;
    }

    @Override
    public Carrera Buscar(Integer id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Carrera c = null;
        try {
            ps = con.prepareStatement(BUSCAR);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                c = CrearInstancia(rs);
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
        return c;
    }
}
