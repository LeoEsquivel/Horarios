package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.MateriaDAO;
import com.mycompany.app.Modelo.Grupo;
import com.mycompany.app.Modelo.Materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGMateriaDAO implements MateriaDAO {

    final String INSERT = "INSERT INTO materias VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE materias SET clv_materia = ?, nombre_materia = ?, creditos = ?, cuatrimestre = ?, posicion = ?, clv_plan = ?, horas_x-semana = ?, tipo_mateira = ? WHERE clv_materia = ?";
    final String DELETE = "DELETE FROM materias WHERE clv_materia = ?";
    final String ALL = "SELECT * FROM materias";
    final String BUSCAR = "SELECT * FROM materias WHERE clv_materia = ?";

    private Connection con;

    public PGMateriaDAO(Connection con) {
        this.con = con;
    }



    @Override
    public void Insertar(Materia p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getClv_materia());
            ps.setString(2, p.getNombre_materia());
            ps.setInt(3, p.getCreditos());
            ps.setInt(4, p.getCuatrimestre());
            ps.setInt(5, p.getPosicion());
            ps.setString(6, p.getClv_plan());
            ps.setInt(7, p.getHoras_x_semana());
            ps.setString(8, p.getTipo_materia());
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
    public void Modificar(Materia p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getClv_materia());
            ps.setString(2, p.getNombre_materia());
            ps.setInt(3, p.getCreditos());
            ps.setInt(4, p.getCuatrimestre());
            ps.setInt(5, p.getPosicion());
            ps.setString(6, p.getClv_plan());
            ps.setInt(7, p.getHoras_x_semana());
            ps.setString(8, p.getTipo_materia());
            ps.setString(9, p.getClv_materia());
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
    public void Eliminar(Materia p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DELETE);
            ps.setString(1, p.getClv_materia());
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

    private Materia CrearInstancia(ResultSet rs) throws SQLException{
        String clv_mat = rs.getString("clv_materia");
        String nombre = rs.getString("nombre_materia");
        int creditos  = rs.getInt("creditos");
        int cuatri = rs.getInt("cuatrimestre");
        int pos = rs.getInt("posicion");
        String clv_plan = rs.getString("clv_plan");
        int hxs = rs.getInt("horas_x_semana");
        String tipo_mat = rs.getString("tipo_materia");
        Materia m = new Materia(clv_mat, nombre, creditos, cuatri, pos, clv_plan, hxs, tipo_mat);
        return m;
    }

    @Override
    public List<Materia> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Materia> g = new ArrayList<>();
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
    public Materia Buscar(String id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Materia g = null;
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
