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

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "TIPO_COOPERANTE")
@NamedQueries({
    @NamedQuery(name = "TipoCooperante.findAll", query = "SELECT t FROM TipoCooperante t")})
public class TipoCooperante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_COOPERANTE")
    private Long idTipoCooperante;
    @Column(name = "DESCRIPCION_COOPERANTE")
    private String descripcionCooperante;
    @OneToMany(mappedBy = "idTipoCooperante", fetch = FetchType.LAZY)
    private List<Cooperante> cooperanteList;

    public TipoCooperante() {
    }

    public TipoCooperante(Long idTipoCooperante) {
        this.idTipoCooperante = idTipoCooperante;
    }

    public Long getIdTipoCooperante() {
        return idTipoCooperante;
    }

    public void setIdTipoCooperante(Long idTipoCooperante) {
        this.idTipoCooperante = idTipoCooperante;
    }

    public String getDescripcionCooperante() {
        return descripcionCooperante;
    }

    public void setDescripcionCooperante(String descripcionCooperante) {
        this.descripcionCooperante = descripcionCooperante;
    }

    public List<Cooperante> getCooperanteList() {
        return cooperanteList;
    }

    public void setCooperanteList(List<Cooperante> cooperanteList) {
        this.cooperanteList = cooperanteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCooperante != null ? idTipoCooperante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCooperante)) {
            return false;
        }
        TipoCooperante other = (TipoCooperante) object;
        if ((this.idTipoCooperante == null && other.idTipoCooperante != null) || (this.idTipoCooperante != null && !this.idTipoCooperante.equals(other.idTipoCooperante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.TipoCooperante[ idTipoCooperante=" + idTipoCooperante + " ]";
    }
    
}
