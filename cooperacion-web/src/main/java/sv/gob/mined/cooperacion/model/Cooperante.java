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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "COOPERANTE")
@NamedQueries({
    @NamedQuery(name = "Cooperante.findAll", query = "SELECT c FROM Cooperante c")})
public class Cooperante implements Serializable {

    @OneToMany(mappedBy = "idCooperante", fetch = FetchType.LAZY)
    private List<ProyectoCooperacion> proyectoCooperacionList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_COOPERANTE")
    @GeneratedValue(generator = "seqCooperante", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "SEQ_COOPERANTE", name = "seqCooperante", initialValue = 1, allocationSize = 1)
    private Long idCooperante;
    @Column(name = "NOMBRE_COOPERANTE")
    private String nombreCooperante;
    @Column(name = "NOMBRE_CONTACTO")
    private String nombreContacto;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "CELULAR")
    private String celular;
    @Column(name = "CORREO")
    private String correo;
    @JoinColumn(name = "ID_TIPO_COOPERANTE", referencedColumnName = "ID_TIPO_COOPERANTE")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoCooperante idTipoCooperante;
    
    @Column(name = "NOMBRE_ASISTENTE")
    private String nombreAsistente;
    @Column(name = "DIRECCION")
    private String direccion;

    public Cooperante() {
    }

    public Cooperante(Long idCooperante) {
        this.idCooperante = idCooperante;
    }

    public Long getIdCooperante() {
        return idCooperante;
    }

    public void setIdCooperante(Long idCooperante) {
        this.idCooperante = idCooperante;
    }

    public String getNombreCooperante() {
        return nombreCooperante;
    }

    public void setNombreCooperante(String nombreCooperante) {
        this.nombreCooperante = nombreCooperante;
    }

    public TipoCooperante getIdTipoCooperante() {
        return idTipoCooperante;
    }

    public void setIdTipoCooperante(TipoCooperante idTipoCooperante) {
        this.idTipoCooperante = idTipoCooperante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCooperante != null ? idCooperante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cooperante)) {
            return false;
        }
        Cooperante other = (Cooperante) object;
        if ((this.idCooperante == null && other.idCooperante != null) || (this.idCooperante != null && !this.idCooperante.equals(other.idCooperante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.Cooperante[ idCooperante=" + idCooperante + " ]";
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContanto) {
        this.nombreContacto = nombreContanto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<ProyectoCooperacion> getProyectoCooperacionList() {
        return proyectoCooperacionList;
    }

    public void setProyectoCooperacionList(List<ProyectoCooperacion> proyectoCooperacionList) {
        this.proyectoCooperacionList = proyectoCooperacionList;
    }

    public String getNombreAsistente() {
        return nombreAsistente;
    }

    public void setNombreAsistente(String nombreAsistente) {
        this.nombreAsistente = nombreAsistente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
