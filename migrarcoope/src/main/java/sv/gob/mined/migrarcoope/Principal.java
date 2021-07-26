/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.migrarcoope;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import sv.gob.mined.migrarcoope.model.ProyectoCooperacion;

/**
 *
 * @author DesarrolloPc
 */
public class Principal {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    private EntityManagerFactory emf = null;
    private EntityManager em;

    public static void main(String[] args) {
        Principal p = new Principal();
        p.iniciar();
    }

    public void iniciar() {
        emf = Persistence.createEntityManagerFactory("cooperacionCe");
        em = emf.createEntityManager();

        String filePath = "/home/misanchez/Escritorio/BaseMapaCooperacion.xlsx";

        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) { // iterate over all rows in the sheet
                if (row.getCell(20).getStringCellValue().trim().toUpperCase().equals("SI")) {
                    ProyectoCooperacion proyecto = new ProyectoCooperacion();
                    Boolean existeCooperante = true;

                    for (int i = 21; i <= 54; i++) {
                        if (row.getCell(i).getStringCellValue() == null) {
                            existeCooperante = false;
                            break;
                        }
                    }

                    if (existeCooperante) {

                        for (Cell cell : row) {
                            if (cell.getColumnIndex() == 0) {
                                proyecto = new ProyectoCooperacion();
                                proyecto.setCodigoEntidad(row.getCell(1).getStringCellValue().trim());
                                proyecto.setAnho("2019");
                                proyecto.setCantidadBeneficiarios(new Double(row.getCell(10).getNumericCellValue()).longValue());

                                System.out.format("inicio de fila %d\n", row.getRowNum());
                            }

                            switch (cell.getCellType()) {
                                case NUMERIC:
                                    if (cell.getNumericCellValue() > 0) {
                                        switch (cell.getColumnIndex()) {
                                            case 11:
                                                proyecto.setInicial((short) 1);
                                                break;
                                            case 12:
                                                proyecto.setParvularia((short) 1);
                                                break;
                                            case 13:
                                                proyecto.setBasicaCi((short) 1);
                                                proyecto.setBasicaCii((short) 1);
                                                proyecto.setBasicaCiii((short) 1);
                                                break;
                                            case 14:
                                                proyecto.setMedia((short) 1);
                                                break;
                                            case 15:
                                                proyecto.setBasicaNocturna((short) 1);
                                                break;
                                        }
                                    }
                                    break;
                                case STRING:
                                    switch (cell.getColumnIndex()) {
                                        case 18:
                                            if (cell.getStringCellValue().equals("SI")) {
                                                proyecto.setEspecial((short) 1);
                                                break;
                                            }
                                    }
                                    break;
                            }

                            //for (int i = 21; i <= 54; i++) {
                            //    System.out.format("Fila %d y columna %d \n", row.getRowNum(), i);
                            boolean esProyecto = false;

                            if (cell.getColumnIndex() > 20 && cell.getColumnIndex() < 52) {
                                if (!cell.getStringCellValue().isEmpty()) {
                                    switch (cell.getColumnIndex()) {
                                        case 21:
                                            proyecto.setIdCooperante(new Long("3"));
                                            proyecto.setNombreProyecto("KOICA");
                                            esProyecto = true;
                                            break;
                                        case 22:
                                            proyecto.setIdCooperante(new Long("4"));
                                            proyecto.setNombreProyecto("ITALIA");
                                            esProyecto = true;
                                            break;
                                        case 23:
                                            proyecto.setIdCooperante(new Long("4"));
                                            proyecto.setNombreProyecto("Italia Paper 11300");
                                            esProyecto = true;
                                            break;
                                        case 24:
                                            proyecto.setIdCooperante(new Long("4"));
                                            proyecto.setNombreProyecto("Italia EITP");
                                            esProyecto = true;
                                            break;
                                        case 25:
                                            proyecto.setIdCooperante(new Long("4"));
                                            proyecto.setNombreProyecto("Italia Ampliación Media");
                                            esProyecto = true;
                                            break;
                                        case 26:
                                            proyecto.setIdCooperante(new Long("18"));
                                            proyecto.setNombreProyecto("Cooperación BID");
                                            esProyecto = true;
                                            break;
                                        case 27:
                                            proyecto.setIdCooperante(new Long("18"));
                                            proyecto.setNombreProyecto("Salto Generacional");
                                            esProyecto = true;
                                        case 28:
                                            proyecto.setIdCooperante(new Long("18"));
                                            proyecto.setNombreProyecto("Salto Generacional Fase I y II");
                                            esProyecto = true;
                                            break;
                                        case 29:
                                            proyecto.setIdCooperante(new Long("18"));
                                            proyecto.setNombreProyecto("Prevención de Violencia");
                                            esProyecto = true;
                                        case 30:
                                            proyecto.setIdCooperante(new Long("109"));
                                            proyecto.setNombreProyecto("Cooperación FANTEL");
                                            esProyecto = true;
                                            break;
                                        case 31:
                                            proyecto.setIdCooperante(new Long("110"));
                                            proyecto.setNombreProyecto("FISDL - ANDALUCIA (Mobiliario)");
                                            esProyecto = true;
                                            break;
                                        case 32:
                                            proyecto.setIdCooperante(new Long("111"));
                                            proyecto.setNombreProyecto("Discapacidad Visual");
                                            esProyecto = true;
                                            break;
                                        case 33:
                                            proyecto.setIdCooperante(new Long("112"));
                                            proyecto.setNombreProyecto("FOMILENIO 2 Componente 4.1");
                                            esProyecto = true;
                                            break;
                                        case 34:
                                            proyecto.setIdCooperante(new Long("63"));
                                            proyecto.setNombreProyecto("Fondos Canadá - UNFPA (Sexualidad)");
                                            esProyecto = true;
                                            break;
                                        case 35:
                                            proyecto.setIdCooperante(new Long("108"));
                                            proyecto.setNombreProyecto("Fondos de Apoyo al PESS (Soy Música)");
                                            esProyecto = true;
                                            break;
                                        case 36:
                                            proyecto.setIdCooperante(new Long("113"));
                                            proyecto.setNombreProyecto("Fundación GAIA (Biosfera Trifinio)");
                                            esProyecto = true;
                                            break;
                                        case 37:
                                            proyecto.setIdCooperante(new Long("70"));
                                            proyecto.setNombreProyecto("FUNDEMAS (Limpiemos)");
                                            esProyecto = true;
                                            break;
                                        case 38:
                                            proyecto.setIdCooperante(new Long("36"));
                                            proyecto.setNombreProyecto("Japón - Alcaldía (Infraestructura)");
                                            esProyecto = true;
                                            break;
                                        case 39:
                                            proyecto.setIdCooperante(new Long("30"));
                                            proyecto.setNombreProyecto("Luxemburgo FOCAP");
                                            esProyecto = true;
                                            break;
                                        case 40:
                                            proyecto.setIdCooperante(new Long("76"));
                                            proyecto.setNombreProyecto("OEA (Educación Inclusiva)");
                                            esProyecto = true;
                                            break;
                                        case 41:
                                            proyecto.setIdCooperante(new Long("82"));
                                            proyecto.setNombreProyecto("OXFAM (Saneamiento Ambiental)");
                                            esProyecto = true;
                                            break;
                                        case 42:
                                            proyecto.setIdCooperante(new Long("85"));
                                            proyecto.setNombreProyecto("PLAN El Salvador (Sexualidad)");
                                            esProyecto = true;
                                            break;
                                        case 43:
                                            proyecto.setIdCooperante(new Long("114"));
                                            proyecto.setNombreProyecto("República China (Taiwán) - Computadoras");
                                            esProyecto = true;
                                            break;
                                        case 44:
                                            proyecto.setIdCooperante(new Long("62"));
                                            proyecto.setNombreProyecto("Cooperación de UNICEF");
                                            esProyecto = true;
                                            break;
                                        case 45:
                                            proyecto.setIdCooperante(new Long("62"));
                                            proyecto.setNombreProyecto("Primera Infancia");
                                            esProyecto = true;
                                            break;
                                        case 46:
                                            proyecto.setIdCooperante(new Long("62"));
                                            proyecto.setNombreProyecto("Modalidad Flexible y Desfavorecidos");
                                            esProyecto = true;
                                            break;
                                        case 47:
                                            proyecto.setIdCooperante(new Long("27"));
                                            proyecto.setNombreProyecto("Cooperación de Unión Europea");
                                            esProyecto = true;
                                            break;
                                        case 48:
                                            proyecto.setIdCooperante(new Long("27"));
                                            proyecto.setNombreProyecto("Bachilleratos Técnicos");
                                            esProyecto = true;
                                            break;
                                        case 49:
                                            proyecto.setIdCooperante(new Long("27"));
                                            proyecto.setNombreProyecto("Tercer Ciclo");
                                            esProyecto = true;
                                            break;
                                        case 50:
                                            proyecto.setIdCooperante(new Long("27"));
                                            proyecto.setNombreProyecto("Igualdad de Género");
                                            esProyecto = true;
                                            break;
                                        case 51:
                                            proyecto.setIdCooperante(new Long("27"));
                                            proyecto.setNombreProyecto("Plan El Salvador Seguro");
                                            esProyecto = true;
                                            break;
                                        case 52:
                                            proyecto.setIdCooperante(new Long("2"));
                                            proyecto.setNombreProyecto("Construcción");
                                            esProyecto = true;
                                            break;
                                        case 53:
                                            proyecto.setIdCooperante(new Long("2"));
                                            proyecto.setNombreProyecto("Construcción");
                                            esProyecto = true;
                                            break;
                                        case 54:
                                            proyecto.setIdCooperante(new Long("2"));
                                            proyecto.setNombreProyecto("Puentes de Empleo");
                                            esProyecto = true;
                                            break;
                                        default:
                                            esProyecto = false;
                                            break;
                                    }
                                }
                            }
                            if (esProyecto) {
                                proyecto.setFechaInsercion(new Date());
                                proyecto.setUsuarioInsercion(5320l);

                                em.getTransaction().begin();
                                em.persist(proyecto);
                                em.getTransaction().commit();

                                ProyectoCooperacion temp = new ProyectoCooperacion();
                                BeanUtils.copyProperties(temp, proyecto);
                                
                                proyecto = new ProyectoCooperacion();

                                temp.setIdProyecto(null);
                                proyecto = temp;
                            }
                            //}
                            System.out.format("Proyecto %d\n", row.getRowNum());

                            if (cell.getColumnIndex() == 54) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("La fila :" + (row.getRowNum() + 1) + " no tiene cooperante");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | EncryptedDocumentException | IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
