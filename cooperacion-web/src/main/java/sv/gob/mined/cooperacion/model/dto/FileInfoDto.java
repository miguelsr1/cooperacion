package sv.gob.mined.cooperacion.model.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author misanchez
 */
public class FileInfoDto implements Serializable {

    private String nombreArchivo;
    private Integer numeroPaginas;
    private Date fechaCreado;

    public FileInfoDto() {
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Date getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }
}
