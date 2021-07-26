/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.migrarcoope;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author DesarrolloPc
 */
public class Principal {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String filePath = "C:\\Users\\DesarrolloPc\\Documents\\MINED\\Cooperacion\\BaseMapaCooperacion.xlsx";

        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) { // iterate over all rows in the sheet
                if (row.getCell(20).getStringCellValue().trim().toUpperCase().equals("SI")) {
                    Proyecto proyecto = new Proyecto();
                    Boolean existeCooperante = true;

                    for (int i = 21; i <= 54; i++) {
                        if (row.getCell(i).getStringCellValue() == null) {
                            existeCooperante = false;
                            break;
                        }
                    }

                    if (existeCooperante) {

                        proyecto.setCodigoEntidad(row.getCell(1).getStringCellValue().trim());
                        proyecto.setAnho("2019");
                        proyecto.setCantidadBeneficiarios(new Double(row.getCell(10).getNumericCellValue()).longValue());

                        for (Cell cell : row) {
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
                            
                            
                            for (int i = 21; i <= 54; i++) {
                                
                            }
                            
                            switch (cell.getColumnIndex()) {
                                case 21:
                                    proyecto.setIdCooperante(new Long("3"));
                                    break;
                                case 22:
                                    proyecto.setIdCooperante(new Long("4"));
                                    break;
                                case 23:
                                    proyecto.setIdCooperante(new Long("4"));
                                    break;
                                case 24:
                                    proyecto.setIdCooperante(new Long("4"));
                                    break;
                                case 25:
                                    proyecto.setIdCooperante(new Long("4"));
                                    break;
                                case 26:
                                    proyecto.setIdCooperante(new Long("18"));
                                    break;
                                case 27:
                                    proyecto.setIdCooperante(new Long("18"));
                                    proyecto.setNombreProyecto("Salto Generacional");
                                case 28:
                                    proyecto.setIdCooperante(new Long("18"));
                                    proyecto.setNombreProyecto("Salto Generacional Fase I y II");
                                    break;
                                case 29:
                                    proyecto.setIdCooperante(new Long("18"));
                                    proyecto.setNombreProyecto("Prevención de Violencia");
                                case 30:
                                    proyecto.setIdCooperante(new Long("109"));
                                    break;
                                case 31:
                                    proyecto.setIdCooperante(new Long("110"));
                                    break;
                                case 32:
                                    proyecto.setIdCooperante(new Long("111"));
                                    proyecto.setNombreProyecto("Discapacidad Visual");
                                    break;
                                case 33:
                                    proyecto.setIdCooperante(new Long("112"));
                                    break;
                                case 34:
                                    proyecto.setIdCooperante(new Long("63"));
                                    break;
                                case 35:
                                    proyecto.setIdCooperante(new Long("108"));
                                    break;
                                case 36:
                                    proyecto.setIdCooperante(new Long("113"));
                                    break;
                                case 37:
                                    proyecto.setIdCooperante(new Long("70"));
                                    break;
                                case 38:
                                    proyecto.setIdCooperante(new Long("36"));
                                    break;
                                case 39:
                                    proyecto.setIdCooperante(new Long("30"));
                                    break;
                                case 40:
                                    proyecto.setIdCooperante(new Long("76"));
                                    break;
                                case 41:
                                    proyecto.setIdCooperante(new Long("82"));
                                    break;
                                case 42:
                                    proyecto.setIdCooperante(new Long("85"));
                                    break;
                                case 43:
                                    proyecto.setIdCooperante(new Long("114"));
                                    break;
                                case 44:
                                    proyecto.setIdCooperante(new Long("62"));
                                    break;
                                case 45:
                                    proyecto.setIdCooperante(new Long("62"));
                                    proyecto.setNombreProyecto("Primera Infancia");
                                    break;
                                case 46:
                                    proyecto.setIdCooperante(new Long("62"));
                                    proyecto.setNombreProyecto("Modalidad Flexible y Desfavorecidos");
                                    break;
                                case 47:
                                    proyecto.setIdCooperante(new Long("27"));
                                    break;
                                case 48:
                                    proyecto.setIdCooperante(new Long("27"));
                                    proyecto.setNombreProyecto("Bachilleratos Técnicos");
                                    break;
                                case 49:
                                    proyecto.setIdCooperante(new Long("27"));
                                    proyecto.setNombreProyecto("Tercer Ciclo");
                                    break;
                                case 50:
                                    proyecto.setIdCooperante(new Long("27"));
                                    proyecto.setNombreProyecto("Igualdad de Género");
                                    break;
                                case 51:
                                    proyecto.setIdCooperante(new Long("27"));
                                    proyecto.setNombreProyecto("Plan El Salvador Seguro");
                                    break;
                                case 52:
                                    proyecto.setIdCooperante(new Long("2"));
                                    proyecto.setNombreProyecto("Construcción");
                                    break;
                                case 53:
                                    proyecto.setIdCooperante(new Long("2"));
                                    proyecto.setNombreProyecto("Construcción");
                                    break;
                                case 54:
                                    proyecto.setIdCooperante(new Long("2"));
                                    proyecto.setNombreProyecto("Puentes de Empleo");
                                    break;
                            }

                        }
                        System.out.println("\n");
                    } else {
                        System.out.println("La fila :" + (row.getRowNum() + 1) + " no tiene cooperante");
                    }
                }
            }
        }
    }
}
