package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.PlanEstudiosDAO;
import com.mycompany.app.Modelo.Grupo;
import com.mycompany.app.Modelo.PlanEstudios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGPlanEstudiosDAO implements PlanEstudiosDAO {

    final String INSERT = "INSERT INTO plan_estudios VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE plan_estudios SET clv_plan = ?, nombre_plan = ?, nivel = ?, idcarrera =? WHERE clv_plan = ?";
    final String DELETE = "DELETE FROM plan_estudios WHERE clv_plan = ?";
    final String ALL = "SELECT * FROM plan_estudios";
    final String BUSCAR = "SELECT * FROM plan_estudis WHERE clv_plan = ?";

    Connection con;

    public PGPlanEstudiosDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void Insertar(PlanEstudios p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getClv_plan());
            ps.setString(2, p.getNombre_plan());
            ps.setString(3, p.getNivel());
            ps.setInt(4, p.getId_carrera());
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
    public void Modificar(PlanEstudios p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getClv_plan());
            ps.setString(2, p.getNombre_plan());
            ps.setString(3, p.getNivel());
            ps.setInt(4, p.getId_carrera());
            ps.setString(5, p.getClv_plan());
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
    public void Eliminar(PlanEstudios p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DELETE);
            ps.setString(1, p.getClv_plan());
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

    private PlanEstudios CrearInstancia(ResultSet rs) throws SQLException{
        String clvplan = rs.getString("clv_plan");
        String nplan = rs.getString("nombre_plan");
        String nivel = rs.getString("nivel");
        int idCarr = rs.getInt("idcarrera");
        PlanEstudios p = new PlanEstudios(clvplan, nplan, nivel, idCarr);
        return p;
    }
    @Override
    public List<PlanEstudios> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PlanEstudios> p = new ArrayList<>();
        try {
            ps = con.prepareStatement(ALL);
            rs = ps.executeQuery();
            p.add(CrearInstancia(rs));
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
    public PlanEstudios Buscar(String id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PlanEstudios g = null;
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
