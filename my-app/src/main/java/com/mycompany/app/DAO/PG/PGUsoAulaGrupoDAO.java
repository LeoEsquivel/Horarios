package com.mycompany.app.DAO.PG;

import com.mycompany.app.Excepciones.Excepciones;
import com.mycompany.app.DAO.UsoAulaGrupoDAO;
import com.mycompany.app.Modelo.UsoAulaGrupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGUsoAulaGrupoDAO implements UsoAulaGrupoDAO {

    static final String INSERT = "INSERT INTO uso_aula_grupo VALUES (?, ?, ?, ?, ?)";
    static final String UPDATE = "UPDATE uso_aula_grupo SET dia= ?, espacio_tiempo = ?, id_aula = ?, clv_grupo = ?, clv_materia = ? WHERE dia= ? AND espacio_tiempo = ? ANd id_aula = ? AND clv_grupo = ? AND clv_materia = ?";
    static final String DELETE = "DELETE FROM uso_aula_grupo WHERE dia= ? AND espacio_tiempo = ? ANd id_aula = ? AND clv_grupo = ? AND clv_materia = ?";
    static final String ALL = "SELECT * FROM uso_aula_grupo";
    static final String BUSCAR = "SELECT * FROM uso_aula_grupo WHERE dia= ? AND espacio_tiempo = ? ANd id_aula = ? AND clv_grupo = ? AND clv_materia = ?";

    private Connection con;
    public PGUsoAulaGrupoDAO(Connection con) {
        this.con = con;
    }

    @Override
    public void Insertar(UsoAulaGrupo p) throws Excepciones {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(INSERT);
            ps.setInt(1, p.getPkUag().getDia());
            ps.setInt(2, p.getPkUag().getEspacio_tiempo());
            ps.setString(3, p.getPkUag().getId_aula());
            ps.setString(4, p.getPkUag().getClv_grupo());
            ps.setString(5, p.getPkUag().getClv_materia());
            if(ps.executeUpdate()== 0){
                throw new Excepciones("Es posible que los datos no hayan sido guardaos.");
            }
        }catch (java.lang.Exception e){
            throw new Excepciones("Error en la sentencia SQL", e);
        }finally {
            if (ps != null){
                try {
                    ps.close();
                } catch (java.lang.Exception e) {
                    throw new Excepciones("Error al cerrar.", e);
                }
            }
        }
    }

    @Override
    public void Modificar(UsoAulaGrupo p) throws Excepciones {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(UPDATE);
            ps.setInt(1, p.getPkUag().getDia());
            ps.setInt(2, p.getPkUag().getEspacio_tiempo());
            ps.setString(3, p.getPkUag().getId_aula());
            ps.setString(4, p.getPkUag().getClv_grupo());
            ps.setString(5, p.getPkUag().getClv_materia());

            ps.setInt(6, p.getPkUag().getDia());
            ps.setInt(7, p.getPkUag().getEspacio_tiempo());
            ps.setString(8, p.getPkUag().getId_aula());
            ps.setString(9, p.getPkUag().getClv_grupo());
            ps.setString(10, p.getPkUag().getClv_materia());
            if(ps.executeUpdate()== 0){
                throw new Excepciones("Es posible que los datos no hayan sido guardaos.");
            }
        }catch (java.lang.Exception e){
            throw new Excepciones("Error en la sentencia SQL", e);
        }finally {
            if (ps != null){
                try {
                    ps.close();
                } catch (java.lang.Exception e) {
                    throw new Excepciones("Error al cerrar.", e);
                }
            }
        }
    }

    @Override
    public void Eliminar(UsoAulaGrupo p) throws Excepciones {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(DELETE);
            ps.setInt(1, p.getPkUag().getDia());
            ps.setInt(2, p.getPkUag().getEspacio_tiempo());
            ps.setString(3, p.getPkUag().getId_aula());
            ps.setString(4, p.getPkUag().getClv_grupo());
            ps.setString(5, p.getPkUag().getClv_materia());
            if(ps.executeUpdate()== 0){
                throw new Excepciones("Es posible que los datos no hayan sido guardaos.");
            }
        }catch (java.lang.Exception e){
            throw new Excepciones("Error en la sentencia SQL", e);
        }finally {
            if (ps != null){
                try {
                    ps.close();
                } catch (java.lang.Exception e) {
                    throw new Excepciones("Error al cerrar.", e);
                }
            }
        }
    }

    private UsoAulaGrupo CrearInstancia(ResultSet rs)throws SQLException {
        int dia = rs.getInt("dia");
        int esptie = rs.getInt("espacio_tiempo");
        String idaula = rs.getString("id_aula");
        String clvgrup = rs.getString("clv_grupo");
        String clv_materia = rs.getString("clv_materia");

        UsoAulaGrupo uag = new UsoAulaGrupo();
        UsoAulaGrupo.PKUag pk = new UsoAulaGrupo.PKUag(dia, esptie, idaula, clvgrup, clv_materia);
        uag.setPkUag(pk);
        return uag;
    }

    @Override
    public List<UsoAulaGrupo> All() throws Excepciones {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UsoAulaGrupo> a = new ArrayList<>();
        try {
            ps = con.prepareStatement(ALL);
            rs = ps.executeQuery();
            while (rs.next()){
                a.add(CrearInstancia(rs));
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
        return a;
    }

    @Override
    public UsoAulaGrupo Buscar(UsoAulaGrupo.PKUag id) throws Excepciones {
        PreparedStatement ps = null;
        ResultSet rs = null;
        UsoAulaGrupo a = null;
        try {
            ps = con.prepareStatement(BUSCAR);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                a = CrearInstancia(rs);
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
        return a;
    }
}
