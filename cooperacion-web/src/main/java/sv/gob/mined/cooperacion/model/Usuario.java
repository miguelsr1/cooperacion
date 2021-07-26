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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MISanchez
 */
@Entity
@Table(name = "USUARIO")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {

    @OneToMany(mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<UsuarioOrg> usuarioOrgList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "ID_PERFIL")
    private Short idPerfil;
    @Column(name = "ACTIVO")
    private Short activo;
    @OneToOne(mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private Director director;

    public Usuario() {
    }

    public Usuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Short getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Short idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.model.Usuario[ idUsuario=" + idUsuario + " ]";
    }

    public List<UsuarioOrg> getUsuarioOrgList() {
        return usuarioOrgList;
    }

    public void setUsuarioOrgList(List<UsuarioOrg> usuarioOrgList) {
        this.usuarioOrgList = usuarioOrgList;
    }
    
}
