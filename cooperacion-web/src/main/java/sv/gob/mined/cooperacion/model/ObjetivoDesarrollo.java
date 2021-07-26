/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "OBJETIVO_DESARROLLO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetivoDesarrollo.findAll", query = "SELECT o FROM ObjetivoDesarrollo o")
    , @NamedQuery(name = "ObjetivoDesarrollo.findByIdObjetivo", query = "SELECT o FROM ObjetivoDesarrollo o WHERE o.idObjetivo = :idObjetivo")
    , @NamedQuery(name = "ObjetivoDesarrollo.findByDescripcionObjetivo", query = "SELECT o FROM ObjetivoDesarrollo o WHERE o.descripcionObjetivo = :descripcionObjetivo")})
public class ObjetivoDesarrollo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OBJETIVO")
    private Integer idObjetivo;
    @Column(name = "DESCRIPCION_OBJETIVO")
    private String descripcionObjetivo;
    @OneToMany(mappedBy = "idObjetivo", fetch = FetchType.LAZY)
    private List<Meta> metaList;

    public ObjetivoDesarrollo() {
    }

    public ObjetivoDesarrollo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public String getDescripcionObjetivo() {
        return descripcionObjetivo;
    }

    public void setDescripcionObjetivo(String descripcionObjetivo) {
        this.descripcionObjetivo = descripcionObjetivo;
    }

    @XmlTransient
    public List<Meta> getMetaList() {
        return metaList;
    }

    public void setMetaList(List<Meta> metaList) {
        this.metaList = metaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjetivo != null ? idObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoDesarrollo)) {
            return false;
        }
        ObjetivoDesarrollo other = (ObjetivoDesarrollo) object;
        if ((this.idObjetivo == null && other.idObjetivo != null) || (this.idObjetivo != null && !this.idObjetivo.equals(other.idObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.model.ObjetivoDesarrollo[ idObjetivo=" + idObjetivo + " ]";
    }
    
}
