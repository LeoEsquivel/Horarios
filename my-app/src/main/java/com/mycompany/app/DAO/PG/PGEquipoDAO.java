package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.EquipoDAO;
import com.mycompany.app.Modelo.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGEquipoDAO implements EquipoDAO {

    static final String INSERT = "INSERT INTO equipo VALUES (?, ?, ?, ?)";
    static final String UPDATE = "UPDATE equipo SET id_equipo = ?, id_categoria = ?, nombre = ?, descripcion = ?WHERE id_equipo = ?";
    static final String DELETE = "DELETE FROM equipo WHERE id_equipo = ?";
    static final String ALL = "SELECT * FROM equipo";
    static final String BUSCAR = "SELECT * FROM equipo WHERE id_equipo = ?";

    private Connection con;

    public PGEquipoDAO(Connection con){
        this.con = con;
    }

    @Override
    public void Insertar(Equipo p) throws DAOException {
        System.out.println("HOLA");
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(INSERT);
               ps.setInt(1, p.getId());
               ps.setInt(2, p.getId_categoria());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDescripcion());
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
    public void Modificar(Equipo p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
               ps.setInt(1, p.getId());
               ps.setInt(2, p.getId_categoria());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDescripcion());
               ps.setInt(5, p.getId());
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
    public void Eliminar(Equipo p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, p.getId());
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

    private Equipo CrearInstancia(ResultSet rs) throws SQLException{
        int id = rs.getInt("id_equipo");
        int id_cat = rs.getInt("id_categoria");
        String nombre = rs.getString("nombre");
        String desc = rs.getString("descripcion");
        Equipo a = new Equipo(id, nombre, desc, id_cat);
        return  a;

    }

    @Override
    public List<Equipo> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Equipo> eq = new ArrayList<>();
        try{
            ps = con.prepareStatement(ALL);
            rs = ps.executeQuery();
            while (rs.next()){
                eq.add(CrearInstancia(rs));
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
        return eq;
    }

    @Override
    public Equipo Buscar(Integer id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Equipo eq = null;
        try{
            ps = con.prepareStatement(BUSCAR);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                eq = CrearInstancia(rs);
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
        return eq;
    }
}
