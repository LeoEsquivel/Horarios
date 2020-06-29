package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.GrupoDAO;
import com.mycompany.app.Modelo.Grupo;
import com.mycompany.app.Modelo.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGGrupoDAO implements GrupoDAO {

    static final String INSERT = "INSERT INTO grupos VALUES (?, ?)";
    static final String UPDATE = "UPDATE grupos SET clv_grupo = ?, turno = ? WHERE clv_grupo = ?";
    static final String DELETE = "DELETE FROM grupo WHERE clv_grupo = ?";
    static final String ALL = "SELECT * FROM grupo";
    static final String BUSCAR = "SELECT * FROM grupo WHERE clv_grupo = ?";

    Connection con;

    public PGGrupoDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void Insertar(Grupo p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getClv_grupo());
            ps.setBoolean(2, p.getTurno());
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
    public void Modificar(Grupo p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getClv_grupo());
            ps.setBoolean(2, p.getTurno());
            ps.setString(3, p.getClv_grupo());
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
    public void Eliminar(Grupo p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DELETE);
            ps.setString(1, p.getClv_grupo());
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

    private Grupo CrearInstancia(ResultSet rs) throws SQLException{
        String clv_gp = rs.getString("clv_grupo");
        Boolean turno = rs.getBoolean("turno");
        Grupo g = new Grupo(clv_gp, turno);
        return g;
    }

    @Override
    public List<Grupo> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Grupo> g = new ArrayList<>();
        try {
            ps = con.prepareStatement(ALL);
            rs = ps.executeQuery();
            while (rs.next()){
                g.add(CrearInstancia(rs));
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
        return g;
    }

    @Override
    public Grupo Buscar(String id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Grupo g = null;
        try {
            ps = con.prepareStatement(BUSCAR);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                g = CrearInstancia(rs);
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
        return g;
    }
}
