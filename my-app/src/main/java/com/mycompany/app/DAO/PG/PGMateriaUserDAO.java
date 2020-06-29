package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.DAO.MateriaUsuarioDAO;
import com.mycompany.app.Modelo.Aula;
import com.mycompany.app.Modelo.MateriaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGMateriaUserDAO implements MateriaUsuarioDAO {

    static final String INSERT = "INSERT INTO materia_usuario VALUES (?, ?, ?, ?, ?)";
    static final String UPDATE = "UPDATE materia_usuario SET clv_materia = ?, clv_plan = ?, clv_usuario = ?, puntos_confianza = ?, puntos_director = ? WHERE clv_materia = ? AND clv_plan = ? AND clv_usuario = ?";
    static final String DELETE = "DELETE FROM materia_usuario WHERE clv_materia = ? AND clv_plan = ? AND clv_usuario = ?";
    static final String ALL = "SELECT * FROM materia_usuario";
    static final String BUSCAR = "SELECT * FROM materia_usuario WHERE clv_materia = ? AND clv_plan = ? AND clv_usuario = ?";

    private Connection con;

    public PGMateriaUserDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void Insertar(MateriaUsuario p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getPkMatU().getClv_materia());
            ps.setString(2, p.getPkMatU().getClv_plan());
            ps.setString(3, p.getPkMatU().getClv_usuario());
            ps.setInt(4, p.getPuntos_confianza());
            ps.setInt(5, p.getPuntos_director());
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
    public void Modificar(MateriaUsuario p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getPkMatU().getClv_materia());
            ps.setString(2, p.getPkMatU().getClv_plan());
            ps.setString(3, p.getPkMatU().getClv_usuario());
            ps.setInt(4, p.getPuntos_confianza());
            ps.setInt(5, p.getPuntos_director());
            ps.setString(6, p.getPkMatU().getClv_materia());
            ps.setString(7, p.getPkMatU().getClv_plan());
            ps.setString(8, p.getPkMatU().getClv_usuario());
            if(ps.executeUpdate() == 0){
                throw new DAOException("Es posible que los datos no se hayan guardado correctamente");
            }
        }catch (Exception e){
            throw new DAOException("Error en la sentencia SQL", e);
        }finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (Exception e) {
                    throw new DAOException("Error al cerrar");
                }
            }
        }
    }

    @Override
    public void Eliminar(MateriaUsuario p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(DELETE);
            ps.setString(1, p.getPkMatU().getClv_materia());
            ps.setString(2, p.getPkMatU().getClv_plan());
            ps.setString(3, p.getPkMatU().getClv_usuario());
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

    private MateriaUsuario CrearInstancia (ResultSet rs) throws SQLException {
        String clvmat = rs.getString("clv_materia");
        String clvplan = rs.getString("clv_plan");
        String clvuser = rs.getString("clv_usuario");
        int pc = rs.getInt("puntos_confianza");
        int pd = rs.getInt("puntos_director");

        MateriaUsuario ma= new MateriaUsuario();
        MateriaUsuario.PKMatU pk = new MateriaUsuario.PKMatU(clvmat, clvplan, clvuser);
        ma.setPkMatU(pk);
        ma.setPuntos_confianza(pc);
        ma.setPuntos_director(pd);
        return ma;
    }

    @Override
    public List<MateriaUsuario> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MateriaUsuario> a = new ArrayList<>();
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
    public MateriaUsuario Buscar(MateriaUsuario.PKMatU id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        MateriaUsuario a = null;
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
