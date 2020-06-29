package com.mycompany.app.ODATA;

import com.mycompany.app.Conexion.Conexion;
import com.mycompany.app.DAO.DAO;
import com.mycompany.app.DAO.PG.*;
import com.mycompany.app.Modelo.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class JSONData extends Conexion {
    public void LeerJson(File JsonArch){

        DAO daoAula = new PGAulaDAO(getCon());
        DAO daoAE = new PGAulaEquipoDAO(getCon());
        DAO daoCarr = new PGCarreraDAO(getCon());
        DAO daoCatE = new PGCategoriasEquipoDAO(getCon());
        DAO daoDis = new PGDisponibilidadDAO(getCon());
        DAO daoEquipo = new PGEquipoDAO(getCon());
        DAO daoGrup = new PGGrupoDAO(getCon());
        DAO daoGMP = new PGGmpDAO(getCon());
        DAO daoLogin = new PgLoginDAO(getCon());
        DAO daoMat = new PGMateriaDAO(getCon());
        DAO daoMatUser = new PGMateriaUserDAO(getCon());
        DAO daoPE = new PGPlanEstudiosDAO(getCon());
        DAO daoPres = new PGPrestamosDAO(getCon());
        DAO daoprof = new PGProfesorDAO(getCon());
        DAO daoUag = new PGUsoAulaGrupoDAO(getCon());

        ArrayList<JSONObject> json = new ArrayList<JSONObject>();
        JSONObject obj;
        String linea = null;

        try {
            FileReader fileReader = new FileReader(JsonArch);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((linea = bufferedReader.readLine()) != null) {
                obj = (JSONObject) new JSONParser().parse(linea);

                if (obj.get("Tipo").toString().equals("Profesor")) {
                    String clv_usuario = obj.get("clvusuario").toString();
                    int idcar = Integer.parseInt(obj.get("idCarrera").toString());
                    String nom = obj.get("Nombre").toString();
                    String lvl = obj.get("nivelAds").toString();
                    String contrato = obj.get("Contrato").toString();

                    Profesor prof = new Profesor(clv_usuario, idcar, nom, lvl, contrato);
                    daoprof.Insertar(prof);

                }else if(obj.get("Tipo").toString().equals("Aula")){
                    Aula a = new Aula(
                            obj.get("id_aula").toString(),
                            obj.get("nombre").toString(),
                            obj.get("tipo").toString(),
                            Integer.parseInt(obj.get("capacidad").toString()),
                            obj.get("descripcion").toString(),
                            obj.get("ubicacion").toString()
                    );
                    daoAula.Insertar(a);

                }else if(obj.get("Tipo").toString().equals("AulaEquipo")){
                    AulaEquipo ae = new AulaEquipo();
                    AulaEquipo.PKAulaE pk = new AulaEquipo.PKAulaE(
                            Integer.parseInt(obj.get("id_equipo").toString()),
                            obj.get("id_aula").toString()
                    );
                    int cap = Integer.parseInt(obj.get("capacidad").toString());
                    ae.setPkAulaE(pk);
                    ae.setCantidad(cap);
                    daoAE.Insertar(ae);

                }else if (obj.get("Tipo").equals("Carrera")){
                    Carrera c = new Carrera(
                            Integer.parseInt(obj.get("idcarrera").toString()),
                            obj.get("nombre_carrera").toString()
                    );
                    daoCarr.Insertar(c);

                }else if (obj.get("Tipo").toString().equals("Categoria_equipo")){
                    CategoriasEquipo ce = new CategoriasEquipo(
                            Integer.parseInt(obj.get("id").toString()),
                            obj.get("nombre").toString(),
                            obj.get("descripcion").toString()
                    );
                    daoCatE.Insertar(ce);

                }else if(obj.get("Tipo").toString().equals("Disponibilidad")){
                        Disponibilidad d = new Disponibilidad();
                        Disponibilidad.PKDis pk = new Disponibilidad.PKDis(
                                Integer.parseInt(obj.get("dia").toString()),
                                Integer.parseInt(obj.get("espacio_tiempo").toString()),
                                obj.get("clv_usuario").toString()
                        );
                        d.setId(pk);
                        daoDis.Insertar(d);

                }else if(obj.get("Tipo").toString().equals("Equipo")){
                    Equipo e = new Equipo(
                            Integer.parseInt(obj.get("id").toString()),
                            obj.get("nombre").toString(),
                            obj.get("descripcion").toString(),
                            Integer.parseInt(obj.get("id_categoria").toString())
                    );
                    daoEquipo.Insertar(e);

                }else if(obj.get("Tipo").toString().equals("Grupo")){
                    Grupo g = new Grupo(
                            obj.get("clv_grupo").toString(),
                            Boolean.valueOf(obj.get("turno").toString())
                    );
                    daoGrup.Insertar(g);

                }else if(obj.get("Tipo").toString().equals("GrupoMateriaProfesor")){
                    GrupoMateriaProfesor gpm = new GrupoMateriaProfesor();
                    GrupoMateriaProfesor.PKGMP pk = new GrupoMateriaProfesor.PKGMP(
                            obj.get("clv_grupo").toString(),
                            obj.get("clv_materia").toString(),
                            obj.get("clv_usuario").toString()
                    );
                    gpm.setPKgmp(pk);
                    daoGMP.Insertar(gpm);

                }else if(obj.get("Tipo").toString().equals("Login")){
                    Login l = new Login(
                            obj.get("clv_usuario").toString(),
                            obj.get("password").toString(),
                            obj.get("tipo_user").toString()
                    );
                    daoLogin.Insertar(l);

                }else if(obj.get("Tipo").toString().equals("Materia")){
                    Materia m = new Materia(
                            obj.get("nombre_materia").toString(),
                            obj.get("clv_materia").toString(),
                            Integer.parseInt(obj.get("creditos").toString()),
                            Integer.parseInt(obj.get("cuatrimestre").toString()),
                            Integer.parseInt(obj.get("posicion").toString()),
                            obj.get("clv_plan").toString(),
                            Integer.parseInt(obj.get("horasxsemana").toString()),
                            obj.get("tipo_materia").toString()
                    );
                    daoMat.Insertar(m);

                }else if(obj.get("Tipo").toString().equals("MateriaUsuario")){
                    MateriaUsuario mu = new MateriaUsuario();
                    MateriaUsuario.PKMatU pk = new MateriaUsuario.PKMatU(
                            obj.get("clv_materia").toString(),
                            obj.get("clv_plan").toString(),
                            obj.get("clv_usuario").toString()
                    );
                    mu.setPkMatU(pk);
                    int pc = Integer.parseInt(obj.get("puntos_confianza").toString());
                    int pp = Integer.parseInt(obj.get("puntos_director").toString());
                    mu.setPuntos_confianza(pc);
                    mu.setPuntos_director(pp);
                    daoMatUser.Insertar(mu);

                }else if(obj.get("Tipo").toString().equals("PlanDeEstudios")){
                    PlanEstudios pe = new PlanEstudios(
                            obj.get("clv_plan").toString(),
                            obj.get("nombre_plan").toString(),
                            obj.get("nivel").toString(),
                            Integer.parseInt(obj.get("id_carrera").toString())
                    );
                    daoPE.Insertar(pe);
                }else if(obj.get("Tipo").toString().equals("Prestamos")){
                    Prestamos p = new Prestamos();
                    Prestamos.PKPres pk = new Prestamos.PKPres(
                            obj.get("clv_usuario").toString(),
                            Integer.parseInt(obj.get("idcarrera").toString())
                    );
                    p.setPkPres(pk);
                    daoPres.Insertar(p);

                }else if (obj.get("Tipo").toString().equals("UsoAulaGrupo")){
                    UsoAulaGrupo uag = new UsoAulaGrupo();
                    UsoAulaGrupo.PKUag pk = new UsoAulaGrupo.PKUag(
                            Integer.parseInt(obj.get("dia").toString()),
                            Integer.parseInt(obj.get("espacio_tiempo").toString()),
                            obj.get("id_aula").toString(),
                            obj.get("clv_grupo").toString(),
                            obj.get("clv_materia").toString()
                    );

                    uag.setPkUag(pk);
                    daoUag.Insertar(uag);

                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}