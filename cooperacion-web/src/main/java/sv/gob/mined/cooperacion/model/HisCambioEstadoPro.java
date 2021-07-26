/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MISanchez
 */
@Entity
@Table(name = "HIS_CAMBIO_ESTADO_PRO")
@NamedQueries({
    @NamedQuery(name = "HisCambioEstadoPro.findAll", query = "SELECT h FROM HisCambioEstadoPro h")})
public class HisCambioEstadoPro implements Serializable {

    @Column(name = "USUARIO_CAMBIO")
    private Long usuarioCambio;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "SEQ_HIS_CAMBIO_ESTADO_PRO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_HIS_CAMBIO_ESTADO_PRO", sequenceName = "SEQ_HIS_CAMBIO_ESTADO_PRO", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_HISTORICO")
    private Integer idHistorico;
    @Column(name = "ID_ESTADO_ANT")
    private Short idEstadoAnt;
    @Column(name = "ID_ESTADO_NEW")
    private Short idEstadoNew;
    @Column(name = "FECHA_CAMBIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @Column(name = "ID_PROYECTO")
    private Long idProyecto;
    @Column(name = "comentario")
    private String comentario;

    public HisCambioEstadoPro() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public HisCambioEstadoPro(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Short getIdEstadoAnt() {
        return idEstadoAnt;
    }

    public void setIdEstadoAnt(Short idEstadoAnt) {
        this.idEstadoAnt = idEstadoAnt;
    }

    public Short getIdEstadoNew() {
        return idEstadoNew;
    }

    public void setIdEstadoNew(Short idEstadoNew) {
        this.idEstadoNew = idEstadoNew;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorico != null ? idHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HisCambioEstadoPro)) {
            return false;
        }
        HisCambioEstadoPro other = (HisCambioEstadoPro) object;
        return !((this.idHistorico == null && other.idHistorico != null) || (this.idHistorico != null && !this.idHistorico.equals(other.idHistorico)));
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.model.HisCambioEstadoPro[ idHistorico=" + idHistorico + " ]";
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Long getUsuarioCambio() {
        return usuarioCambio;
    }

    public void setUsuarioCambio(Long usuarioCambio) {
        this.usuarioCambio = usuarioCambio;
    }
}
