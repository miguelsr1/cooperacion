/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MISanchez
 */
@Entity
@XmlRootElement
@SqlResultSetMapping(name = "defaultListadoProyecto",
        entities = @EntityResult(entityClass = ListadoProyectoDto.class))
public class ListadoProyectoDto implements Serializable {

    @Id
    private Long idProyecto;
    private String nombreCe;
    private String codigoEntidad;
    private String nombreDepartamento;
    private String codigoDepartamento;
    private String nombreMunicipio;
    private String codigoMunicipio;
    private String nombreDirector;
    private String correoElectronico;
    private String nombreProyecto;
    private String anho;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    private BigDecimal geoPx;
    private BigDecimal geoPy;
    private Long idCooperante;
    private Short idEstado;
    private BigDecimal idRow;

    public ListadoProyectoDto() {
    }

    public BigDecimal getIdRow() {
        return idRow;
    }

    public void setIdRow(BigDecimal idRow) {
        this.idRow = idRow;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreCe() {
        return nombreCe;
    }

    public void setNombreCe(String nombreCe) {
        this.nombreCe = nombreCe;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidadl) {
        this.codigoEntidad = codigoEntidadl;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Short getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Short estado) {
        this.idEstado = estado;
    }

    public BigDecimal getGeoPx() {
        return geoPx;
    }

    public void setGeoPx(BigDecimal geoPx) {
        this.geoPx = geoPx;
    }

    public BigDecimal getGeoPy() {
        return geoPy;
    }

    public void setGeoPy(BigDecimal geoPy) {
        this.geoPy = geoPy;
    }

    public Long getIdCooperante() {
        return idCooperante;
    }

    public void setIdCooperante(Long idCooperante) {
        this.idCooperante = idCooperante;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }
}
