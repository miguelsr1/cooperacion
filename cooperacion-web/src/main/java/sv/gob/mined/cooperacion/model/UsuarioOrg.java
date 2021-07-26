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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "USUARIO_ORG")
@NamedQueries({
    @NamedQuery(name = "UsuarioOrg.findAll", query = "SELECT u FROM UsuarioOrg u")})
public class UsuarioOrg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_USU_OG")
    private Integer idUsuOg;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario idUsuario;

    public UsuarioOrg() {
    }

    public UsuarioOrg(Integer idUsuOg) {
        this.idUsuOg = idUsuOg;
    }

    public Integer getIdUsuOg() {
        return idUsuOg;
    }

    public void setIdUsuOg(Integer idUsuOg) {
        this.idUsuOg = idUsuOg;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idUsuOg != null ? idUsuOg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioOrg)) {
            return false;
        }
        UsuarioOrg other = (UsuarioOrg) object;
        if ((this.idUsuOg == null && other.idUsuOg != null) || (this.idUsuOg != null && !this.idUsuOg.equals(other.idUsuOg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.model.UsuarioOrg[ idUsuOg=" + idUsuOg + " ]";
    }
    
}
