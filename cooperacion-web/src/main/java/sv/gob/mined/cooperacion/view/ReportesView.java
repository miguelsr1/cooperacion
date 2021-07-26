/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.DateFormatConverter;
import sv.gob.mined.cooperacion.facade.CatalogoFacade;
import sv.gob.mined.cooperacion.model.dto.MatrizProyectoDto;
import sv.gob.mined.utils.StringUtils;

@Named
@ViewScoped
public class ReportesView implements Serializable {

    private String anho = "";

    private HSSFWorkbook wb;
    private DataFormat FORMATO_DATA;
    private final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
    private final String EXTENSION_XLS = "xls";
    private final String CONTENIDO_XLS = "application/ms-excel";
    private final DateFormat FORMAT_DATE_RPT = new SimpleDateFormat("ddMMMyy_HHmmss");

    @Inject
    private CatalogoFacade catalogoFacade;

    public void generarMatrizProyecto() {
        anho = "2020";
        int row = 8;
        Date date = new Date();

        List<MatrizProyectoDto> lstDatos = catalogoFacade.getMatrizProyectosByAnho(anho);
        HSSFCellStyle style;
        try (InputStream ins = ReportesView.class.getClassLoader().getResourceAsStream("sv/gob/mined/rpt/excel/matrizProyecto.xls")) {
            wb = (HSSFWorkbook) WorkbookFactory.create(ins);
            FORMATO_DATA = wb.createDataFormat();
            style = wb.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setWrapText(true);
            style.setVerticalAlignment(VerticalAlignment.TOP);
            style.setAlignment(HorizontalAlignment.JUSTIFY);

            HSSFCellStyle styleFecha = wb.createCellStyle();
            styleFecha.setBorderBottom(BorderStyle.THIN);
            styleFecha.setBorderTop(BorderStyle.THIN);
            styleFecha.setBorderRight(BorderStyle.THIN);
            styleFecha.setBorderLeft(BorderStyle.THIN);
            styleFecha.setVerticalAlignment(VerticalAlignment.TOP);
            styleFecha.setAlignment(HorizontalAlignment.JUSTIFY);
            String excelFormatPattern = DateFormatConverter.convert(Locale.ENGLISH, "dd/MM/yyyy");
            styleFecha.setDataFormat(wb.createDataFormat().getFormat(excelFormatPattern));

            HSSFCellStyle styleEntero = wb.createCellStyle();
            styleEntero.setBorderBottom(BorderStyle.THIN);
            styleEntero.setBorderTop(BorderStyle.THIN);
            styleEntero.setBorderRight(BorderStyle.THIN);
            styleEntero.setBorderLeft(BorderStyle.THIN);
            styleEntero.setVerticalAlignment(VerticalAlignment.TOP);
            styleEntero.setAlignment(HorizontalAlignment.JUSTIFY);
            styleEntero.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

            HSSFCellStyle styleDecimal = wb.createCellStyle();
            styleDecimal.setBorderBottom(BorderStyle.THIN);
            styleDecimal.setBorderTop(BorderStyle.THIN);
            styleDecimal.setBorderRight(BorderStyle.THIN);
            styleDecimal.setBorderLeft(BorderStyle.THIN);
            styleDecimal.setVerticalAlignment(VerticalAlignment.TOP);
            styleDecimal.setAlignment(HorizontalAlignment.JUSTIFY);
            styleDecimal.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));

            HSSFSheet s1 = wb.getSheetAt(0);   //sheet by index

            HSSFRow hrow = s1.getRow(3);
            HSSFCell cell = hrow.getCell(2);
            cell.setCellValue("Matriz de Proyectos en Ejecución - " + anho);

            

            hrow = s1.getRow(4);
            cell = hrow.getCell(2);
            cell.setCellValue("Resumen por Unidad Técnica al  " + StringUtils.getNumDia(date) + " de " + StringUtils.getNomMes(date) + " de " + anho);

            for (MatrizProyectoDto dato : lstDatos) {
                hrow = s1.createRow(row);

                escribirNumeroEntero(dato.getIdProyecto().toString(), hrow, 0, styleEntero);
                escribirTexto(dato.getNombreProyecto(), hrow, 1, style);
                escribirTexto(dato.getDescripcion(), hrow, 2, style);
                escribirTexto(dato.getInstitucion(), hrow, 3, style);
                escribirTexto(dato.getSectorIntervencion(), hrow, 4, style);
                escribirTexto(dato.getDescripcionCooperacion(), hrow, 5, style);
                escribirTexto(dato.getDescripcionModalidad(), hrow, 6, style);
                escribirTexto(dato.getDescripcionObjetivo(), hrow, 7, style);
                escribirTexto(dato.getDescripcionMeta(), hrow, 8, style);
                escribirFecha(dato.getFechaEstimadaInicio(), hrow, 9, styleFecha);
                escribirFecha(dato.getFechaEstimadaFin(), hrow, 10, styleFecha);
                escribirTexto(dato.getNombreCooperante(), hrow, 11, style);

                escribirNumeroDecimal((dato.getMontoInversion() == null ? "" : dato.getMontoInversion().toString()), hrow, 12, styleDecimal);
                escribirTexto("", hrow, 13, style);
                escribirTexto("", hrow, 14, style);
                escribirTexto("", hrow, 15, style);
                escribirTexto("", hrow, 16, style);
                row++;

                hrow.setHeight((short) -1);
            }

            generarArchivo(wb, "rptMatrizProyecto");
        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(ReportesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void escribirTexto(String text, HSSFRow hrow, Integer col, CellStyle style) {
        HSSFRichTextString texto = new HSSFRichTextString(text);
        HSSFCell cell = hrow.createCell(col);
        //style.setWrapText(true);
        cell.setCellStyle(style);
        cell.setCellValue(texto);
        cell.getRow().setHeight((short) -1);

    }

    private void escribirNumeroEntero(String text, HSSFRow hrow, Integer col, CellStyle style) {
        text = text.replace(",", "");
        HSSFCell cell = hrow.createCell(col);
        cell.setCellValue(Integer.parseInt(text));
        cell.setCellStyle(style);
    }

    private void escribirNumeroDecimal(String text, HSSFRow hrow, Integer col, CellStyle style) {
        if (text == null || text.trim().isEmpty()) {
            text = "0";
        }
        text = text.replace(",", "");
        //System.out.println(text);
        HSSFCell cell = hrow.createCell(col);
        cell.setCellValue(Double.parseDouble(text));
        cell.setCellStyle(style);
    }

    private void escribirFecha(Date fecha, HSSFRow hrow, Integer col, CellStyle style) {
        //Calendar calendar = new GregorianCalendar();
        HSSFCell cell = hrow.createCell(col);
        //calendar.setTime(fecha);
        cell.setCellValue(fecha);
        cell.setCellStyle(style);
    }

    private void generarArchivo(Workbook wb, String nombreFile) {
        try (ByteArrayOutputStream outByteStream = new ByteArrayOutputStream()) {
            wb.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            downloadFileBytes(outArray, nombreFile, CONTENIDO_XLS, EXTENSION_XLS);
        } catch (IOException ex) {
            Logger.getLogger(ReportesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void downloadFileBytes(byte[] outArray, String nombreFile, String tipoDeContenido, String extension) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        response.setContentType(tipoDeContenido);
        response.setContentLength(outArray.length);
        response.setHeader("Content-disposition", "attachment; filename=" + nombreFile + "-" + getFechaGeneracionReporte() + "." + extension);
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        outStream.close();
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    public String getFechaGeneracionReporte() {
        return FORMAT_DATE_RPT.format(new Date());
    }

    public String formatearNumero(int posisiones, String valor, Boolean numInt) {
        valor = valor.replaceAll("[.]", "");
        Formatter fmt = new Formatter();
        if (numInt) {
            fmt.format("%0" + posisiones + "d", Integer.parseInt(valor));
        } else {
            fmt.format("%-" + posisiones + "s", valor);
        }
        return fmt.toString();
    }
}
