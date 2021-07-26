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
@Table(name = "MODALIDAD_EJECUCION")
@NamedQueries({
    @NamedQuery(name = "ModalidadEjecucion.findAll", query = "SELECT m FROM ModalidadEjecucion m")})
public class ModalidadEjecucion implements Serializable {

    @OneToMany(mappedBy = "idModalidad", fetch = FetchType.LAZY)
    private List<ProyectoCooperacion> proyectoCooperacionList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_MODALIDAD")
    private Long idModalidad;
    @Column(name = "DESCRIPCION_MODALIDAD")
    private String descripcionModalidad;

    public ModalidadEjecucion() {
    }

    public Long getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Long idModalidad) {
        this.idModalidad = idModalidad;
    }

    public String getDescripcionModalidad() {
        return descripcionModalidad;
    }

    public void setDescripcionModalidad(String descripcionModalidad) {
        this.descripcionModalidad = descripcionModalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModalidad != null ? idModalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModalidadEjecucion)) {
            return false;
        }
        ModalidadEjecucion other = (ModalidadEjecucion) object;
        if ((this.idModalidad == null && other.idModalidad != null) || (this.idModalidad != null && !this.idModalidad.equals(other.idModalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.ModalidadEjecucion[ idModalidad=" + idModalidad + " ]";
    }

    public List<ProyectoCooperacion> getProyectoCooperacionList() {
        return proyectoCooperacionList;
    }

    public void setProyectoCooperacionList(List<ProyectoCooperacion> proyectoCooperacionList) {
        this.proyectoCooperacionList = proyectoCooperacionList;
    }

}
