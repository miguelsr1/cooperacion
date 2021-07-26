/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "FECHA_CAPACITACION")
@NamedQueries({
    @NamedQuery(name = "FechaCapacitacion.findAll", query = "SELECT f FROM FechaCapacitacion f")})
public class FechaCapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_FECHA")
    @GeneratedValue(generator = "SEQ_FECHA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_FECHA", sequenceName = "SEQ_FECHA", allocationSize = 1, initialValue = 1)
    private Integer idFecha;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "HORA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaInicio;
    @Column(name = "HORA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaFin;
    @Column(name = "ESTADO_ELIMINACION")
    private Short estadoEliminacion;
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProyectoCooperacion idProyecto;

    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "LUGAR")
    private String lugar;

    public FechaCapacitacion() {
    }

    public FechaCapacitacion(Integer idFecha) {
        this.idFecha = idFecha;
    }

    public Integer getIdFecha() {
        return idFecha;
    }

    public void setIdFecha(Integer idFecha) {
        this.idFecha = idFecha;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Short getEstadoEliminacion() {
        return estadoEliminacion;
    }

    public void setEstadoEliminacion(Short estadoEliminacion) {
        this.estadoEliminacion = estadoEliminacion;
    }

    public ProyectoCooperacion getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(ProyectoCooperacion idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFecha != null ? idFecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FechaCapacitacion)) {
            return false;
        }
        FechaCapacitacion other = (FechaCapacitacion) object;
        if ((this.idFecha == null && other.idFecha != null) || (this.idFecha != null && !this.idFecha.equals(other.idFecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.model.FechaCapacitacion[ idFecha=" + idFecha + " ]";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
