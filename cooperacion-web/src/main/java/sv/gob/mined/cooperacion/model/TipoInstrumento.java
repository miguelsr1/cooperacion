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
@Table(name = "TIPO_INSTRUMENTO")
@NamedQueries({
    @NamedQuery(name = "TipoInstrumento.findAll", query = "SELECT t FROM TipoInstrumento t")})
public class TipoInstrumento implements Serializable {

    @OneToMany(mappedBy = "idTipoInstrumento", fetch = FetchType.LAZY)
    private List<ProyectoCooperacion> proyectoCooperacionList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_INSTRUMENTO")
    private Long idTipoInstrumento;
    @Column(name = "DESCRIPCION_INSTRUMENTO")
    private String descripcionInstrumento;

    public TipoInstrumento() {
    }

    public TipoInstrumento(Long idTipoInstrumento) {
        this.idTipoInstrumento = idTipoInstrumento;
    }

    public Long getIdTipoInstrumento() {
        return idTipoInstrumento;
    }

    public void setIdTipoInstrumento(Long idTipoInstrumento) {
        this.idTipoInstrumento = idTipoInstrumento;
    }

    public String getDescripcionInstrumento() {
        return descripcionInstrumento;
    }

    public void setDescripcionInstrumento(String descripcionInstrumento) {
        this.descripcionInstrumento = descripcionInstrumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoInstrumento != null ? idTipoInstrumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoInstrumento)) {
            return false;
        }
        TipoInstrumento other = (TipoInstrumento) object;
        if ((this.idTipoInstrumento == null && other.idTipoInstrumento != null) || (this.idTipoInstrumento != null && !this.idTipoInstrumento.equals(other.idTipoInstrumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.TipoInstrumento[ idTipoInstrumento=" + idTipoInstrumento + " ]";
    }

    public List<ProyectoCooperacion> getProyectoCooperacionList() {
        return proyectoCooperacionList;
    }

    public void setProyectoCooperacionList(List<ProyectoCooperacion> proyectoCooperacionList) {
        this.proyectoCooperacionList = proyectoCooperacionList;
    }
    
}
