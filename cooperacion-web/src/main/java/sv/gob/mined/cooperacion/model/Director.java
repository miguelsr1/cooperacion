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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MISanchez
 */
@Entity
@Table(name = "DIRECTOR")
@NamedQueries({
    @NamedQuery(name = "Director.findAll", query = "SELECT d FROM Director d")})
public class Director implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DIRECTOR")
    private Long idDirector;
    @Size(max = 5)
    @Column(name = "CODIGO_ENTIDAD")
    private String codigoEntidad;
    @Size(max = 300)
    @Column(name = "NOMBRE_DIRECTOR")
    private String nombreDirector;
    @Size(max = 150)
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    @Column(name = "ACTIVO")
    private Short activo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario idUsuario;

    public Director() {
    }

    public Director(Long idDirector) {
        this.idDirector = idDirector;
    }

    public Long getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Long idDirector) {
        this.idDirector = idDirector;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirector != null ? idDirector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Director)) {
            return false;
        }
        Director other = (Director) object;
        if ((this.idDirector == null && other.idDirector != null) || (this.idDirector != null && !this.idDirector.equals(other.idDirector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.model.Director[ idDirector=" + idDirector + " ]";
    }
    
}
