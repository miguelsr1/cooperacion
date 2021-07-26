/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "META")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meta.findAll", query = "SELECT m FROM Meta m")
    , @NamedQuery(name = "Meta.findByIdMeta", query = "SELECT m FROM Meta m WHERE m.idMeta = :idMeta")
    , @NamedQuery(name = "Meta.findByDescripcionMeta", query = "SELECT m FROM Meta m WHERE m.descripcionMeta = :descripcionMeta")
    , @NamedQuery(name = "Meta.findByCodigoMeta", query = "SELECT m FROM Meta m WHERE m.codigoMeta = :codigoMeta")})
public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_META")
    private Integer idMeta;
    @Column(name = "DESCRIPCION_META")
    private String descripcionMeta;
    @Column(name = "CODIGO_META")
    private String codigoMeta;
    @JoinColumn(name = "ID_OBJETIVO", referencedColumnName = "ID_OBJETIVO")
    @ManyToOne(fetch = FetchType.EAGER)
    private ObjetivoDesarrollo idObjetivo;

    public Meta() {
    }

    public Meta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public String getDescripcionMeta() {
        return descripcionMeta;
    }

    public void setDescripcionMeta(String descripcionMeta) {
        this.descripcionMeta = descripcionMeta;
    }

    public String getCodigoMeta() {
        return codigoMeta;
    }

    public void setCodigoMeta(String codigoMeta) {
        this.codigoMeta = codigoMeta;
    }

    public ObjetivoDesarrollo getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(ObjetivoDesarrollo idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMeta != null ? idMeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meta)) {
            return false;
        }
        Meta other = (Meta) object;
        if ((this.idMeta == null && other.idMeta != null) || (this.idMeta != null && !this.idMeta.equals(other.idMeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.model.Meta[ idMeta=" + idMeta + " ]";
    }
    
}
