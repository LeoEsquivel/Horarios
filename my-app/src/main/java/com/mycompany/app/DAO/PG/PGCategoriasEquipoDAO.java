package com.mycompany.app.DAO.PG;

import com.mycompany.app.DAO.CategoriasEquipoDAO;
import com.mycompany.app.Excepciones.Excepciones;
import com.mycompany.app.Modelo.CategoriasEquipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PGCategoriasEquipoDAO implements CategoriasEquipoDAO {

    static final String INSERT = "INSERT INTO categorias_equipo VALUES (?, ?, ?)";
    static final String UPDATE = "UPDATE categorias_equipo SET id_categoria = ?, nombre = ?, descripcion = ? WHERE id_categoria = ?";
    static final String DELETE = "DELETE FROM categorias_equipo WHERE id_categoria = ?";
    static final String ALL = "SELECT * FROM categorias_equipo";
    static final String BUSCAR = "SELECT * FROM categorias_equipo WHERE id_categoria = ?";

    Connection con;

    public PGCategoriasEquipoDAO(Connection con){
        this.con = con;
    }

    @Override
    public void Insertar(CategoriasEquipo p) throws Excepciones {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(INSERT);
               ps.setInt(1, p.getId_categoria());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            if (ps.executeUpdate() == 0) {
                throw new Excepciones("La informacion es posible que no se haya guardado");
            }
        } catch (SQLException e) {
            throw new Excepciones("Error en la sentencia SQL", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new Excepciones("Error al cerrar", e);
                }
            }

        }
    }

    @Override
    public void Modificar(CategoriasEquipo p) throws Excepciones {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(UPDATE);
               ps.setInt(1, p.getId_categoria());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
               ps.setInt(4, p.getId_categoria());
            if (ps.executeUpdate() == 0) {
                throw new Excepciones("La informacion es posible que no se haya guardado");
            }
        } catch (SQLException e) {
            throw new Excepciones("Error en la sentencia SQL", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new Excepciones("Error al cerrar", e);
                }
            }

        }
    }

    @Override
    public void Eliminar(CategoriasEquipo p) throws Excepciones {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, p.getId_categoria());
            if (ps.executeUpdate() == 0) {
                throw new Excepciones("La informacion es posible que no se haya guardado");
            }
        } catch (SQLException e) {
            throw new Excepciones("Error en la sentencia SQL", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new Excepciones("Error al cerrar", e);
                }
            }
        }
    }

    private CategoriasEquipo CrearInstancia(ResultSet rs) throws SQLException{
        int id = rs.getInt("id_categoria");
        String nombre = rs.getString("nombre");
        String desc = rs.getString("descripcion");
        CategoriasEquipo c = new CategoriasEquipo(id, nombre, desc);
        return c;
    }

    @Override
    public List<CategoriasEquipo> All() throws Excepciones {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CategoriasEquipo> c = new ArrayList<>();
        try {
            ps = con.prepareStatement(ALL);
            while (rs.next()){
                c.add(CrearInstancia(rs));
            }
        } catch (java.lang.Exception e) {
            throw new Excepciones("Error es la sentencia SQL", e);
        } finally {
            if (rs != null || ps != null) {
                try {
                    rs.close();
                    ps.close();
                }catch (java.lang.Exception e) {
                    throw new Excepciones("No ha podido cerrar correctamente");
                }
            }
        }
        return c;
    }

    @Override
    public CategoriasEquipo Buscar(Integer id) throws Excepciones {
        PreparedStatement ps = null;
        ResultSet rs = null;
        CategoriasEquipo p = null;
        try {
            ps = con.prepareStatement(BUSCAR);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p = CrearInstancia(rs);
            } else {
                throw new Excepciones("No se han encontrado el registro");
            }
        } catch (java.lang.Exception e) {
            throw new Excepciones("Error es la sentencia SQL", e);
        } finally {
            if (rs != null || ps != null) {
                try {
                    rs.close();
                    ps.close();
                }catch (java.lang.Exception e) {
                    throw new Excepciones("No ha podido cerrar correctamente");
                }
            }
        }
        return p;
    }

    public static void main2(String[] args){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProyectoHorarios", "postgres","1234");
            CategoriasEquipoDAO dao = new PGCategoriasEquipoDAO(con);
            CategoriasEquipo ce = new CategoriasEquipo(2,"Equipo de computo", "ASDEFQWE");
            dao.Insertar(ce);
            System.out.println("Jalo");
        }catch (java.lang.Exception e){
            e.printStackTrace();
        }
    }
}
