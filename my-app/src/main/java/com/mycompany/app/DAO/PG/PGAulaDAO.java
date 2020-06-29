package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.AulaDAO;
import com.mycompany.app.DAO.DAOException;
import com.mycompany.app.Modelo.Aula;
import com.mycompany.app.Modelo.Profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGAulaDAO implements AulaDAO {

    static final String INSERT = "INSERT INTO aulas VALUES (?, ?, ?, ?, ?, ?)";
    static final String UPDATE = "UPDATE aulas SET id_aula = ?, nombre = ?, tipo = ?, capacidad = ?, descripcion = ?, ubicacion= ? WHERE id_aula = ?";
    static final String DELETE = "DELETE FROM usuarios WHERE id_aula = ?";
    static final String ALL = "SELECT * FROM aulas";
    static final String BUSCAR = "SELECT * FROM usuarios WHERE id_aula = ?";

    Connection con;

    public PGAulaDAO(Connection con){
        this.con = con;
    }

    @Override
    public void Insertar(Aula p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(INSERT);
            ps.setString(1, p.getId_aula());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTipo());
            ps.setInt(4, p.getCapacidad());
            ps.setString(5, p.getDescripcion());
            ps.setString(6, p.getDescripcion());
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
    public void Modificar(Aula p) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
            ps.setString(1, p.getId_aula());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTipo());
               ps.setInt(4, p.getCapacidad());
            ps.setString(5, p.getId_aula());
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
    public void Eliminar(Aula p) throws DAOException {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(DELETE);
            ps.setString(1, p.getId_aula());
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

    private Aula CrearInstancia(ResultSet rs) throws SQLException{
        String id = rs.getString("id_aula");
        String nombre = rs.getString("nombre");
        String tipo = rs.getString("tipo");
        int capacidad = rs.getInt("capacidad");
        String descripcion = rs.getString("descripcion");
        String ubicacion = rs.getString("ubicacion");
        Aula a = new Aula(id, nombre, tipo, capacidad, descripcion, ubicacion);
        return a;
    }

    @Override
    public List<Aula> All() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Aula> a = new ArrayList<>();
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
    public Aula Buscar(String id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Aula a = null;
        try {
            ps = con.prepareStatement(BUSCAR);
            ps.setString(1, id);
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
