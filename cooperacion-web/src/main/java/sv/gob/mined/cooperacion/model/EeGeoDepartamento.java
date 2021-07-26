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
@Table(name = "EE_GEO_DEPARTAMENTO")
@NamedQueries({
    @NamedQuery(name = "EeGeoDepartamento.findAll", query = "SELECT e FROM EeGeoDepartamento e")})
public class EeGeoDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CODIGO_DEPARTAMENTO")
    private String codigoDepartamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GEOREFERENCIA_X")
    private BigDecimal georeferenciaX;
    @Column(name = "GEOREFERENCIA_Y")
    private BigDecimal georeferenciaY;
    @Size(max = 100)
    @Column(name = "NOMBRE_DEPARTAMENTO")
    private String nombreDepartamento;

    public EeGeoDepartamento() {
    }

    public EeGeoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
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

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDepartamento != null ? codigoDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EeGeoDepartamento)) {
            return false;
        }
        EeGeoDepartamento other = (EeGeoDepartamento) object;
        if ((this.codigoDepartamento == null && other.codigoDepartamento != null) || (this.codigoDepartamento != null && !this.codigoDepartamento.equals(other.codigoDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.EeGeoDepartamento[ codigoDepartamento=" + codigoDepartamento + " ]";
    }
    
}
