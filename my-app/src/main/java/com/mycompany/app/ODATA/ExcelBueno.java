package com.mycompany.app.ODATA;

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
    private ArrayList<UsoAulaGrupo> uag = new ArrayList<>();

    private int numhoja;

    public ExcelBueno(File excel) throws ExcepcionesData {
        try{
            FileInputStream file = new FileInputStream(excel);
            XSSFWorkbook libro = new XSSFWorkbook(file);
            for(int i=0; i<libro.getNumberOfSheets(); i++){
                EscogerHoja(libro, numhoja);
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

    private int EscogerHoja(XSSFWorkbook wb, int hoja){
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            if (wb.getSheetName(i).equals("Aulas")) {
                System.out.println(wb.getSheetName(i));
                hoja = i;
                break;
            }else if(wb.getSheetName(i).equals("AulaEquipo")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Carrera")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("CategoriasEquipo")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Disponibilidad")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Equipo")){
                hoja = i;
                break;
            }else if(wb.getSheetName(i).equals("Grupo")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("GrupoMateriaProfesor")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Login")){
                hoja = i;
                break;
            }else if(wb.getSheetName(i).equals("Materia")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("MateriaUsuario")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("PlanDeEstudios")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Prestamos")){
                hoja = i;
                break;
            }else if (wb.getSheetName(i).equals("Usuarios")){
                hoja = i;
                break;
            }else if(wb.getSheetName(i).equals("UsoAulaGrupo")){
                hoja = i;break;
            }
        }
        return hoja;
    }

    private void ObtenerData(ArrayList<ArrayList<String>> filas, String nombre) throws ExcepcionesData{
        int cont = 0;
        for(ArrayList<String> fila : filas){
            cont++;
            try{
                switch (nombre){
                    case "Aulas":
                        aulas.add(new Aula(fila.get(1), fila.get(2), fila.get(3), Integer.parseInt(fila.get(4)), fila.get(5), fila.get(6)));
                        break;
                    case "Aulas Equipo":
                        AulaEquipo ae = new AulaEquipo();
                        AulaEquipo.PKAulaE pk = new AulaEquipo.PKAulaE(Integer.parseInt(fila.get(1)), fila.get(2));
                        ae.setPkAulaE(pk);
                        ae.setCantidad(Integer.parseInt(fila.get(3)));
                        aulae.add(ae);
                        break;
                    case "Carrera":
                        carrera.add(new Carrera(Integer.parseInt(fila.get(1)), fila.get(2)));
                        break;
                    case "Categorias":
                        categoriasEquipos.add(new CategoriasEquipo(Integer.parseInt(fila.get(1)) ,fila.get(2), fila.get(3)));
                        break;
                    case "Disponibilidad":
                        Disponibilidad d = new Disponibilidad();
                        Disponibilidad.PKDis pk1 = new Disponibilidad.PKDis(Integer.parseInt(fila.get(1)), Integer.parseInt(fila.get(2)), fila.get(3));
                        d.setId(pk1);
                        disponibilidad.add(d);
                        break;
                    case "Equipo":
                        equipo.add(new Equipo(Integer.parseInt(fila.get(1)), fila.get(2), fila.get(3), Integer.parseInt(fila.get(4))));
                        break;
                    case "Grupo":
                        grupo.add(new Grupo(fila.get(1), Boolean.parseBoolean(fila.get(2))));
                        break;
                    case "GrupoMateriasProfesor":
                        GrupoMateriaProfesor gmp = new GrupoMateriaProfesor();
                        GrupoMateriaProfesor.PKGMP pk2 = new GrupoMateriaProfesor.PKGMP(fila.get(1), fila.get(2), fila.get(3));
                        gmp.setPKgmp(pk2);
                        agmp.add(gmp);
                }
            }catch (Exception e){
                throw new ExcepcionesData("Error agregando la linea: "+cont);
            }
        }

    }
}
