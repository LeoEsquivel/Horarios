package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.LoginDAO;
import com.mycompany.app.Modelo.Grupo;
import com.mycompany.app.Modelo.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PgLoginDAO implements LoginDAO {

    static final String INSERT = "INSERT INTO login VALUES (?, ?, ?)";
    static final String UPDATE = "UPDATE login SET clv_usuario = ?, pass_usuario = ?, tipo_usuario = ? WHERE clv_usuario = ?";
    static final String DELETE = "DELETE FROM login WHERE clv_usuario = ?";
    static final String ALL = "SELECT * FROM login";
    static final String BUSCAR = "SELECT * FROM login WHERE clv_usuario = ?";

    private Connection con;

    public PgLoginDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void Insertar(Login p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getClv_usuario());
            ps.setString(2, p.getPass_usuario());
            ps.setString(3, p.getTipo_usuario());
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
    public void Modificar(Login p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getClv_usuario());
            ps.setString(2, p.getPass_usuario());
            ps.setString(3, p.getTipo_usuario());
            ps.setString(4, p.getTipo_usuario());
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
    public void Eliminar(Login p) throws DAOException {
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

    private Login CrearInstancia(ResultSet rs) throws SQLException{
        String clv = rs.getString("clv_usuario");
        String pass = rs.getString("pass_usuario");
        String tipouser = rs.getString("tipo_usuario");
        Login l = new Login(clv, pass, tipouser);
        return l;
    }

    @Override
    public List<Login> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Login> l = new ArrayList<>();
        try{
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
        return l;
    }

    @Override
    public Login Buscar(String id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Login l = null;
        try {
            ps = con.prepareStatement(BUSCAR);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                l = CrearInstancia(rs);
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
        return l;
    }
}
