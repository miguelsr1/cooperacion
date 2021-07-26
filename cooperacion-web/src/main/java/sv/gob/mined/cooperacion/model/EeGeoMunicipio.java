/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "EE_GEO_MUNICIPIO")
@NamedQueries({
    @NamedQuery(name = "EeGeoMunicipio.findAll", query = "SELECT e FROM EeGeoMunicipio e")})
public class EeGeoMunicipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MUNICIPIO")
    private Integer idMunicipio;
    @Size(max = 2)
    @Column(name = "CODIGO_MUNICIPIO")
    private String codigoMunicipio;
    @Size(max = 2)
    @Column(name = "CODIGO_DEPARTAMENTO")
    private String codigoDepartamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GEOREFERENCIA_X")
    private BigDecimal georeferenciaX;
    @Column(name = "GEOREFERENCIA_Y")
    private BigDecimal georeferenciaY;
    @Size(max = 20)
    @Column(name = "NOMBRE_MUNICIPIO")
    private String nombreMunicipio;

    public EeGeoMunicipio() {
    }

    public EeGeoMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public BigDecimal getGeoreferenciaX() {
        return georeferenciaX;
    }

    public void setGeoreferenciaX(BigDecimal georeferenciaX) {
        this.georeferenciaX = georeferenciaX;
    }

    public BigDecimal getGeoreferenciaY() {
        return georeferenciaY;
    }

    public void setGeoreferenciaY(BigDecimal georeferenciaY) {
        this.georeferenciaY = georeferenciaY;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMunicipio != null ? idMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EeGeoMunicipio)) {
            return false;
        }
        EeGeoMunicipio other = (EeGeoMunicipio) object;
        if ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.EeGeoMunicipio[ idMunicipio=" + idMunicipio + " ]";
    }
    
}
