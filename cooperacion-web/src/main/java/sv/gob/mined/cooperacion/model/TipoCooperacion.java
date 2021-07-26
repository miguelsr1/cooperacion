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
@Table(name = "TIPO_COOPERACION")
@NamedQueries({
    @NamedQuery(name = "TipoCooperacion.findAll", query = "SELECT t FROM TipoCooperacion t")})
public class TipoCooperacion implements Serializable {

    @OneToMany(mappedBy = "idTipoCooperacion", fetch = FetchType.LAZY)
    private List<ProyectoCooperacion> proyectoCooperacionList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_COOPERACION")
    private Long idTipoCooperacion;
    @Column(name = "DESCRIPCION_COOPERACION")
    private String descripcionCooperacion;

    public TipoCooperacion() {
    }

    public TipoCooperacion(Long idTipoCooperacion) {
        this.idTipoCooperacion = idTipoCooperacion;
    }

    public Long getIdTipoCooperacion() {
        return idTipoCooperacion;
    }

    public void setIdTipoCooperacion(Long idTipoCooperacion) {
        this.idTipoCooperacion = idTipoCooperacion;
    }

    public String getDescripcionCooperacion() {
        return descripcionCooperacion;
    }

    public void setDescripcionCooperacion(String descripcionCooperacion) {
        this.descripcionCooperacion = descripcionCooperacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCooperacion != null ? idTipoCooperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCooperacion)) {
            return false;
        }
        TipoCooperacion other = (TipoCooperacion) object;
        if ((this.idTipoCooperacion == null && other.idTipoCooperacion != null) || (this.idTipoCooperacion != null && !this.idTipoCooperacion.equals(other.idTipoCooperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.TipoCooperacion[ idTipoCooperacion=" + idTipoCooperacion + " ]";
    }

    public List<ProyectoCooperacion> getProyectoCooperacionList() {
        return proyectoCooperacionList;
    }

    public void setProyectoCooperacionList(List<ProyectoCooperacion> proyectoCooperacionList) {
        this.proyectoCooperacionList = proyectoCooperacionList;
    }
    
}
