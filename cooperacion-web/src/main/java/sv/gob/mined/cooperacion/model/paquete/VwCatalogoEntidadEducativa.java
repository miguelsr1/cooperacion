/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.model.paquete;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "VW_CATALOGO_ENTIDAD_EDUCATIVA")
@NamedQueries({
    @NamedQuery(name = "VwCatalogoEntidadEducativa.findAll", query = "SELECT v FROM VwCatalogoEntidadEducativa v")})
public class VwCatalogoEntidadEducativa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_ENTIDAD")
    private String codigoEntidad;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO1")
    private String telefono1;
    @Column(name = "TELEFONO2")
    private String telefono2;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "INICIALES_MODALIDAD")
    private String inicialesModalidad;
    @Basic(optional = false)
    @Column(name = "CODIGO_DEPARTAMENTO")
    private String codigoDepartamento;
    @Basic(optional = false)
    @Column(name = "CODIGO_MUNICIPIO")
    private String codigoMunicipio;
    @Column(name = "CODIGO_CANTON")
    private String codigoCanton;
    @Column(name = "NOMBRE_CANTON")
    private String nombreCanton;
    @Basic(optional = false)
    @Column(name = "ID_MUNICIPIO")
    private BigInteger idMunicipio;

    public VwCatalogoEntidadEducativa() {
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getInicialesModalidad() {
        return inicialesModalidad;
    }

    public void setInicialesModalidad(String inicialesModalidad) {
        this.inicialesModalidad = inicialesModalidad;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(String codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public String getNombreCanton() {
        return nombreCanton;
    }

    public void setNombreCanton(String nombreCanton) {
        this.nombreCanton = nombreCanton;
    }

    public BigInteger getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(BigInteger idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
    
}
