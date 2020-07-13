package com.mycompany.app.ODATA;

import com.mycompany.app.Excepciones.Excepciones;
import com.mycompany.app.Modelo.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelBueno {
    private ArrayList<ArrayList<String>> filas = new ArrayList<>();
    private ArrayList<Aula> aulas = new ArrayList<>();
    private ArrayList<AulaEquipo> aulae = new ArrayList<>();
    private ArrayList<Carrera> carrera = new ArrayList<>();
    private ArrayList<CategoriasEquipo> categoriasEquipos = new ArrayList<>();
    private ArrayList<Disponibilidad> disponibilidad = new ArrayList<>();
    private ArrayList<Equipo> equipo = new ArrayList<>();
    private ArrayList<Grupo> grupo = new ArrayList<>();
    private ArrayList<GrupoMateriaProfesor> agmp = new ArrayList<>();
    private ArrayList<Login> logins = new ArrayList<>();
    private ArrayList<Materia> materias = new ArrayList<>();
    private ArrayList<MateriaUsuario> materiasU = new ArrayList<>();
    private ArrayList<PlanEstudios> planE = new ArrayList<>();
    private ArrayList<Prestamos> prestamos = new ArrayList<>();
    private ArrayList<Profesor> profesors = new ArrayList<>();
    private ArrayList<UsoAulaGrupo> auag = new ArrayList<>();

    private static int numhoja;

    public ExcelBueno(File excel) throws Excepciones {
        try{
            FileInputStream file = new FileInputStream(excel);
            XSSFWorkbook libro = new XSSFWorkbook(file);
            for(int i=0; i<libro.getNumberOfSheets(); i++){
                System.out.println(i+" Constructor");
                EscogerHoja(libro, i);
                XSSFSheet hoja = libro.getSheetAt(numhoja);

                for(Row row : hoja){
                    ArrayList<String> fila = new ArrayList<>();
                    for(Cell cell : row){
                        fila.add(cell.getStringCellValue());
                    }
                    filas.add(fila);
                    ObtenerData(filas, libro.getSheetName(numhoja));
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void EscogerHoja(XSSFWorkbook wb, int hoja){
        System.out.println(wb.getNumberOfSheets());
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {

            System.out.println(i+" Vuelta escoger de hoja");
            if (wb.getSheetName(i).equals("Aulas")) {
                this.numhoja = i;
                break;
            }else if(wb.getSheetName(i).equals("AulaEquipo")){
                this.numhoja = i;

                break;
            }else if (wb.getSheetName(i).equals("Carrera")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("CategoriasEquipo")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Disponibilidad")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Equipo")){
                this.numhoja = i;
                break;
            }else if(wb.getSheetName(i).equals("Grupo")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("GrupoMateriaProfesor")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Login")){
                this.numhoja = i;
                break;
            }else if(wb.getSheetName(i).equals("Materia")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("MateriaUsuario")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("PlanDeEstudios")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Prestamos")){
                this.numhoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Usuarios")){
                this.numhoja = i;
                break;
            }else if(wb.getSheetName(i).equals("UsoAulaGrupo")){
                this.numhoja = i;
                break;
            }
            break;
        }
    }

    private void ObtenerData(ArrayList<ArrayList<String>> filas, String nombre) throws Excepciones{
        int cont = 0;

        for(ArrayList<String> fila : filas){
            cont++;
            System.out.println(cont+" Vueltas obtener data");
            try{
                switch (nombre){
                    case "Aulas":
                        aulas.add(new Aula(fila.get(0), fila.get(1), fila.get(2), Integer.parseInt(fila.get(3)), fila.get(4), fila.get(5)));
                        break;
                    case "Aulas Equipo":
                        AulaEquipo ae = new AulaEquipo();
                        AulaEquipo.PKAulaE pk = new AulaEquipo.PKAulaE(Integer.parseInt(fila.get(0)), fila.get(1));
                        ae.setPkAulaE(pk);
                        ae.setCantidad(Integer.parseInt(fila.get(2)));
                        aulae.add(ae);
                        break;
                    case "Carrera":
                        carrera.add(new Carrera(Integer.parseInt(fila.get(0)), fila.get(1)));
                        break;
                    case "Categorias":
                        categoriasEquipos.add(new CategoriasEquipo(Integer.parseInt(fila.get(0)) ,fila.get(1), fila.get(2)));
                        break;
                    case "Disponibilidad":
                        Disponibilidad d = new Disponibilidad();
                        Disponibilidad.PKDis pk1 = new Disponibilidad.PKDis(Integer.parseInt(fila.get(0)), Integer.parseInt(fila.get(1)), fila.get(2));
                        d.setId(pk1);
                        disponibilidad.add(d);
                        break;
                    case "Equipo":
                        equipo.add(new Equipo(Integer.parseInt(fila.get(0)), fila.get(1), fila.get(2), Integer.parseInt(fila.get(3))));
                        break;
                    case "Grupo":
                        grupo.add(new Grupo(fila.get(0), Boolean.parseBoolean(fila.get(1))));
                        break;
                    case "Login":
                        Login l = new Login(fila.get(0), fila.get(1), fila.get(2));
                        logins.add(l);
                        break;
                    case "Materia":
                        Materia m = new Materia(fila.get(0), fila.get(1), Integer.parseInt(fila.get(2)),
                                Integer.parseInt(fila.get(3)), Integer.parseInt(fila.get(4)), fila.get(5),
                                Integer.parseInt(fila.get(6)), fila.get(7));
                        materias.add(m);
                        break;
                    case "GrupoMateriasProfesor":
                        GrupoMateriaProfesor gmp = new GrupoMateriaProfesor();
                        GrupoMateriaProfesor.PKGMP pk2 = new GrupoMateriaProfesor.PKGMP(fila.get(0), fila.get(1), fila.get(2));
                        gmp.setPKgmp(pk2);
                        agmp.add(gmp);
                        break;
                    case "MateriaUsuario":
                        MateriaUsuario mu = new MateriaUsuario();
                        MateriaUsuario.PKMatU pk3 = new MateriaUsuario.PKMatU(fila.get(0), fila.get(1), fila.get(2));
                        mu.setPkMatU(pk3);
                        int pc = Integer.parseInt(fila.get(3));
                        int pd = Integer.parseInt(fila.get(4));
                        mu.setPuntos_confianza(pc);
                        mu.setPuntos_director(pd);
                        materiasU.add(mu);
                        break;
                    case "PlanEstudios":
                        PlanEstudios pl = new PlanEstudios(fila.get(0), fila.get(1), fila.get(2), Integer.parseInt(fila.get(3)));
                        planE.add(pl);
                        break;
                    case "Prestamos":
                        Prestamos pres = new Prestamos();
                        Prestamos.PKPres pk4 = new Prestamos.PKPres(fila.get(0), Integer.parseInt(fila.get(1)));
                        pres.setPkPres(pk4);
                        prestamos.add(pres);
                        break;
                    case "Profesor":
                        Profesor pf = new Profesor(fila.get(0), Integer.parseInt(fila.get(1)), fila.get(2),
                                fila.get(3), fila.get(4));
                        profesors.add(pf);
                        break;
                    case "UsoAulaGrupo":
                        UsoAulaGrupo uag = new UsoAulaGrupo();
                        UsoAulaGrupo.PKUag pk5 = new UsoAulaGrupo.PKUag( Integer.parseInt(fila.get(0)), Integer.parseInt(fila.get(1)),
                                fila.get(2), fila.get(3), fila.get(4) );
                        uag.setPkUag(pk5);
                        auag.add(uag);
                        break;
                }
            }catch (Exception e){
                throw new Excepciones("Error agregando la linea: "+ cont+" en la hoja de: "+nombre, e);
            }

            //System.out.println(aulas.size()+" Tamano aulas");
            //System.out.println(profesors.size()+" Tamano profesores");
            for (int i=0; i<aulas.size(); i++){

                //System.out.println(aulas.get(i).getNombre());
            }
        }

    }
}
