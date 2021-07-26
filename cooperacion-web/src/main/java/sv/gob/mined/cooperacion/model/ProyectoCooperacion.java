/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "PROYECTO_COOPERACION")
@NamedQueries({
    @NamedQuery(name = "ProyectoCooperacion.findAll", query = "SELECT p FROM ProyectoCooperacion p")})
public class ProyectoCooperacion implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIO_INSERCION")
    private long usuarioInsercion;
    @JoinColumn(name = "ID_COOPERANTE", referencedColumnName = "ID_COOPERANTE")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cooperante idCooperante;
    @JoinColumn(name = "ID_MODALIDAD", referencedColumnName = "ID_MODALIDAD")
    @ManyToOne(fetch = FetchType.LAZY)
    private ModalidadEjecucion idModalidad;
    @JoinColumn(name = "ID_TIPO_COOPERACION", referencedColumnName = "ID_TIPO_COOPERACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoCooperacion idTipoCooperacion;
    @JoinColumn(name = "ID_TIPO_INSTRUMENTO", referencedColumnName = "ID_TIPO_INSTRUMENTO")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoInstrumento idTipoInstrumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto", fetch = FetchType.LAZY)
    private List<FechaCapacitacion> fechaCapacitacionList;

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_PROYECTO")
    @GeneratedValue(generator = "SEQ_PROYECTO",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_PROYECTO", sequenceName = "SEQ_PROYECTO", allocationSize = 1, initialValue = 1)
    private Long idProyecto;
    @Column(name = "NOMBRE_PROYECTO")
    private String nombreProyecto;
    @Column(name = "OBJETIVOS")
    private String objetivos;
    @Column(name = "FECHA_ESTIMADA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstimadaInicio;
    @Column(name = "FECHA_ESTIMADA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEstimadaFin;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CANTIDAD")
    private Long cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_INVERSION")
    private BigDecimal montoInversion;
    @Column(name = "CANTIDAD_BENEFICIARIOS")
    private Long cantidadBeneficiarios;
    @Column(name = "INICIAL")
    private Short inicial;
    @Column(name = "PARVULARIA")
    private Short parvularia;
    @Column(name = "BASICA_CI")
    private Short basicaCi;
    @Column(name = "BASICA_CII")
    private Short basicaCii;
    @Column(name = "BASICA_CIII")
    private Short basicaCiii;
    @Column(name = "MEDIA")
    private Short media;
    @Column(name = "DOCENTE")
    private Short docente;
    @Column(name = "OTROS")
    private Short otros;
    @Column(name = "BASICA_NOCTURNA")
    private Short basicaNocturna;
    @Column(name = "MOD_FLEXIBLE")
    private Short modFelxible;
    @Column(name = "ESPECIAL")
    private Short especial;
    @Column(name = "ID_ESTADO")
    private Short idEstado;
    @Column(name = "ID_ETAPA_EJECUCION")
    private Short idEtapaEjecucion;
    @Basic(optional = false)
    @Column(name = "FECHA_INSERCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsercion;
    @Column(name = "ESTADO_ELIMINACION")
    private Short estadoEliminacion;
    @Column(name = "CODIGO_ENTIDAD")
    private String codigoEntidad;
    @Column(name = "ANHO")
    private String anho;
    @Column(name = "SECTOR_INTERVENCION")
    private String sectorIntervencion;
    @Column(name = "ID_META")
    private Integer idMeta;

    public ProyectoCooperacion() {
    }

    public ProyectoCooperacion(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public ProyectoCooperacion(Long idProyecto, Date fechaInsercion, Long usuarioInsercion) {
        this.idProyecto = idProyecto;
        this.fechaInsercion = fechaInsercion;
        this.usuarioInsercion = usuarioInsercion;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public Date getFechaEstimadaInicio() {
        return fechaEstimadaInicio;
    }

    public void setFechaEstimadaInicio(Date fechaEstimadaInicio) {
        this.fechaEstimadaInicio = fechaEstimadaInicio;
    }

    public Date getFechaEstimadaFin() {
        return fechaEstimadaFin;
    }

    public void setFechaEstimadaFin(Date fechaEstimadaFin) {
        this.fechaEstimadaFin = fechaEstimadaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getMontoInversion() {
        return montoInversion;
    }

    public void setMontoInversion(BigDecimal montoInversion) {
        this.montoInversion = montoInversion;
    }

    public Long getCantidadBeneficiarios() {
        return cantidadBeneficiarios;
    }

    public void setCantidadBeneficiarios(Long cantidadBeneficiarios) {
        this.cantidadBeneficiarios = cantidadBeneficiarios;
    }

    public Short getInicial() {
        return inicial;
    }

    public void setInicial(Short inicial) {
        this.inicial = inicial;
    }

    public Short getParvularia() {
        return parvularia;
    }

    public void setParvularia(Short parvularia) {
        this.parvularia = parvularia;
    }

    public Short getBasicaCi() {
        return basicaCi;
    }

    public void setBasicaCi(Short basicaCi) {
        this.basicaCi = basicaCi;
    }

    public Short getBasicaCii() {
        return basicaCii;
    }

    public void setBasicaCii(Short basicaCii) {
        this.basicaCii = basicaCii;
    }

    public Short getBasicaCiii() {
        return basicaCiii;
    }

    public void setBasicaCiii(Short basicaCiii) {
        this.basicaCiii = basicaCiii;
    }

    public Short getMedia() {
        return media;
    }

    public void setMedia(Short media) {
        this.media = media;
    }

    public Short getDocente() {
        return docente;
    }

    public void setDocente(Short docente) {
        this.docente = docente;
    }

    public Short getOtros() {
        return otros;
    }

    public void setOtros(Short otros) {
        this.otros = otros;
    }

    public Short getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Short idEstado) {
        this.idEstado = idEstado;
    }

    public Short getIdEtapaEjecucion() {
        return idEtapaEjecucion;
    }

    public void setIdEtapaEjecucion(Short idEtapaEjecucion) {
        this.idEtapaEjecucion = idEtapaEjecucion;
    }

    public Date getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(Date fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public Long getUsuarioInsercion() {
        return usuarioInsercion;
    }

    public void setUsuarioInsercion(Long usuarioInsercion) {
        this.usuarioInsercion = usuarioInsercion;
    }

    public Short getEstadoEliminacion() {
        return estadoEliminacion;
    }

    public void setEstadoEliminacion(Short estadoEliminacion) {
        this.estadoEliminacion = estadoEliminacion;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoCooperacion)) {
            return false;
        }
        ProyectoCooperacion other = (ProyectoCooperacion) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.cooperacion.web.model.ProyectoCooperacion[ idProyecto=" + idProyecto + " ]";
    }

    public List<FechaCapacitacion> getFechaCapacitacionList() {
        return fechaCapacitacionList;
    }

    public void setFechaCapacitacionList(List<FechaCapacitacion> fechaCapacitacionList) {
        this.fechaCapacitacionList = fechaCapacitacionList;
    }

    public Cooperante getIdCooperante() {
        return idCooperante;
    }

    public void setIdCooperante(Cooperante idCooperante) {
        this.idCooperante = idCooperante;
    }

    public ModalidadEjecucion getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(ModalidadEjecucion idModalidad) {
        this.idModalidad = idModalidad;
    }

    public TipoCooperacion getIdTipoCooperacion() {
        return idTipoCooperacion;
    }

    public void setIdTipoCooperacion(TipoCooperacion idTipoCooperacion) {
        this.idTipoCooperacion = idTipoCooperacion;
    }

    public TipoInstrumento getIdTipoInstrumento() {
        return idTipoInstrumento;
    }

    public void setIdTipoInstrumento(TipoInstrumento idTipoInstrumento) {
        this.idTipoInstrumento = idTipoInstrumento;
    }

    public Short getBasicaNocturna() {
        return basicaNocturna;
    }

    public void setBasicaNocturna(Short basicaNocturna) {
        this.basicaNocturna = basicaNocturna;
    }

    public Short getModFelxible() {
        return modFelxible;
    }

    public void setModFelxible(Short modFelxible) {
        this.modFelxible = modFelxible;
    }

    public Short getEspecial() {
        return especial;
    }

    public void setEspecial(Short especial) {
        this.especial = especial;
    }

    public String getSectorIntervencion() {
        return sectorIntervencion;
    }

    public void setSectorIntervencion(String sectorIntervencion) {
        this.sectorIntervencion = sectorIntervencion;
    }
    
}
