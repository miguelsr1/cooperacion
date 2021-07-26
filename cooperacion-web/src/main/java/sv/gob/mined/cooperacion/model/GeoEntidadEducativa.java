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

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "GEO_ENTIDAD_EDUCATIVA")
@NamedQueries({
    @NamedQuery(name = "GeoEntidadEducativa.findAll", query = "SELECT g FROM GeoEntidadEducativa g")})
public class GeoEntidadEducativa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_ENTIDAD")
    private String codigoEntidad;
    @Column(name = "GEOREFERENCIA_X")
    private BigDecimal georeferenciaX;
    @Column(name = "GEOREFERENCIA_Y")
    private BigDecimal georeferenciaY;

    public GeoEntidadEducativa() {
    }

    public GeoEntidadEducativa(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEntidad != null ? codigoEntidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeoEntidadEducativa)) {
            return false;
        }
        GeoEntidadEducativa other = (GeoEntidadEducativa) object;
        if ((this.codigoEntidad == null && other.codigoEntidad != null) || (this.codigoEntidad != null && !this.codigoEntidad.equals(other.codigoEntidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.GeoEntidadEducativa[ codigoEntidad=" + codigoEntidad + " ]";
    }
    
}
