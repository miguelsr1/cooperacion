package sv.gob.mined.cooperacion.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import sv.gob.mined.cooperacion.facade.CatalogoFacade;
import sv.gob.mined.cooperacion.facade.EMailFacade;
import sv.gob.mined.cooperacion.facade.MantenimientoFacade;
import sv.gob.mined.cooperacion.facade.paquete.UbicacionFacade;
import sv.gob.mined.cooperacion.model.Cooperante;
import sv.gob.mined.cooperacion.model.DatoInfraCe;
import sv.gob.mined.cooperacion.model.Director;
import sv.gob.mined.cooperacion.model.GeoEntidadEducativa;
import sv.gob.mined.cooperacion.model.HisCambioEstadoPro;
import sv.gob.mined.cooperacion.model.ModalidadEjecucion;
import sv.gob.mined.cooperacion.model.Notificacion;
import sv.gob.mined.cooperacion.model.ProyectoCooperacion;
import sv.gob.mined.cooperacion.model.TipoCooperacion;
import sv.gob.mined.cooperacion.model.TipoCooperante;
import sv.gob.mined.cooperacion.model.TipoInstrumento;
import sv.gob.mined.cooperacion.model.Usuario;
import sv.gob.mined.cooperacion.model.paquete.Departamento;
import sv.gob.mined.cooperacion.model.paquete.Municipio;
import sv.gob.mined.cooperacion.model.paquete.VwCatalogoEntidadEducativa;
import sv.gob.mined.cooperacion.util.RC4Crypter;
import sv.gob.mined.utils.StringUtils;
import sv.gob.mined.utils.jsf.JsfUtil;

/**
 *
 * @author misanchez
 */
@Named
@ViewScoped
public class RegistrarCooperacionView implements Serializable {

    private String posicionInicial = "13.749655, -88.822362";
    private String codigoEntidad;
    private String codigoDepartamento;

    private String[] nivelI;
    private String[] nivelII;
    private String[] nivelIII;
    private String[] nivelIV;

    private Long idTipoCooperacion;
    private Long idTipoInstrumento;
    private Long idModalidad;
    private Long idCooperante;

    private Director directorCe;
    private DatoInfraCe datoInfraCe;
    private ProyectoCooperacion proyectoCooperacion = new ProyectoCooperacion();
    private VwCatalogoEntidadEducativa entidadEducativa = new VwCatalogoEntidadEducativa();
    private GeoEntidadEducativa geoEntidadEducativa = new GeoEntidadEducativa();

    private List<Municipio> lstMunicipio = new ArrayList();

    private MapModel simpleModel;
    private List<UploadedFile> archivosDelProyecto;

    @Inject
    private UbicacionFacade ubicacionFacade;
    @Inject
    private MantenimientoFacade mantenimientoFacade;
    @Inject
    private CatalogoFacade catalogoFacade;
    @Inject
    private EMailFacade eMailFacade;

    @Inject
    private CredencialesView credencialesView;

    private DateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("bundle");

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (fc.getExternalContext().getSessionMap().containsKey("usuario")) {
            Usuario usu = (Usuario) fc.getExternalContext().getSessionMap().get("usuario");
            if (usu.getDirector() != null) {
                directorCe = usu.getDirector();
                codigoEntidad = directorCe.getCodigoEntidad();
                datoInfraCe = catalogoFacade.findDatoInfraByCe(codigoEntidad);
                buscarEntidadEducativa();
            }

            archivosDelProyecto = new ArrayList();
        }

        credencialesView.setDominio("2");
    }

    public Long getIdTipoCooperacion() {
        return idTipoCooperacion;
    }

    public void setIdTipoCooperacion(Long idTipoCooperacion) {
        this.idTipoCooperacion = idTipoCooperacion;
    }

    public Long getIdTipoInstrumento() {
        return idTipoInstrumento;
    }

    public void setIdTipoInstrumento(Long idTipoInstrumento) {
        this.idTipoInstrumento = idTipoInstrumento;
    }

    public Long getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Long idModalidad) {
        this.idModalidad = idModalidad;
    }

    public Long getIdCooperante() {
        return idCooperante;
    }

    public void setIdCooperante(Long idCooperante) {
        this.idCooperante = idCooperante;
    }

    public List<UploadedFile> getArchivosDelProyecto() {
        return archivosDelProyecto;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public VwCatalogoEntidadEducativa getEntidadEducativa() {
        return entidadEducativa;
    }

    public GeoEntidadEducativa getGeoEntidadEducativa() {
        return geoEntidadEducativa;
    }

    public List<Departamento> getLstDepartamentos() {
        return ubicacionFacade.getLstDepartamentos();
    }

    public List<Municipio> getLstMunicipio() {
        return lstMunicipio;
    }

    public List<Cooperante> getLstCooperantes() {
        return catalogoFacade.findAllCooperante();
    }

    public List<TipoCooperante> getLstTipoCooperante() {
        return catalogoFacade.findTipoCooperante();
    }

    public List<TipoCooperacion> getLstTipoCooperacion() {
        return catalogoFacade.findTipoCooperacion();
    }

    public List<ModalidadEjecucion> getLstModalidadEjecucion() {
        return catalogoFacade.findModalidadEjecucion();
    }

    public List<TipoInstrumento> getLstTipoInstrumento() {
        return catalogoFacade.findTipoInstrumento();
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getPosicionInicial() {
        return posicionInicial;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public Director getDirectorCe() {
        return directorCe;
    }

    public ProyectoCooperacion getProyectoCooperacion() {
        return proyectoCooperacion;
    }

    public void setProyectoCooperacion(ProyectoCooperacion proyectoCooperacion) {
        this.proyectoCooperacion = proyectoCooperacion;
    }

    public void recuperarMunicipios() {
        lstMunicipio = ubicacionFacade.getLstMunicipio(codigoDepartamento);
    }

    public String[] getNivelI() {
        return nivelI;
    }

    public void setNivelI(String[] nivelI) {
        this.nivelI = nivelI;
    }

    public String[] getNivelII() {
        return nivelII;
    }

    public void setNivelII(String[] nivelII) {
        this.nivelII = nivelII;
    }

    public String[] getNivelIII() {
        return nivelIII;
    }

    public void setNivelIII(String[] nivelIII) {
        this.nivelIII = nivelIII;
    }

    public String[] getNivelIV() {
        return nivelIV;
    }

    public void setNivelIV(String[] nivelIV) {
        this.nivelIV = nivelIV;
    }

    public DatoInfraCe getDatoInfraCe() {
        if (datoInfraCe == null) {
            datoInfraCe = new DatoInfraCe();
        }
        return datoInfraCe;
    }

    public void setDatoInfraCe(DatoInfraCe datoInfraCe) {
        this.datoInfraCe = datoInfraCe;
    }

    public String getFechaInicio() {
        if (proyectoCooperacion != null && proyectoCooperacion.getFechaEstimadaInicio() == null) {
            return FORMAT_DATE.format(new Date());
        } else {
            return FORMAT_DATE.format(proyectoCooperacion.getFechaEstimadaInicio());
        }
    }

    public void buscarEntidadEducativa() {
        entidadEducativa = ubicacionFacade.findEntidadEducativaByCodigo(codigoEntidad);
        if (entidadEducativa != null) {
            directorCe = catalogoFacade.findDirectorByCodigoEntidad(codigoEntidad);
            if (directorCe != null) {
                geoEntidadEducativa = catalogoFacade.findGeoEntidadEducativaByCodigo(codigoEntidad);
                codigoDepartamento = entidadEducativa.getCodigoDepartamento();
                if (geoEntidadEducativa != null) {
                    posicionInicial = geoEntidadEducativa.getGeoreferenciaY().toString() + "," + geoEntidadEducativa.getGeoreferenciaX().toString();
                    agregarGpsCe();
                }
                recuperarMunicipios();
            } else {
                JsfUtil.mensajeAlerta("No se cuenta con el director del Centro Educativo: " + codigoEntidad + " - " + entidadEducativa.getNombre());
            }
        } else {
            JsfUtil.mensajeAlerta("El código ingresado no existe.");
        }
    }

    public void agregarGpsCe() {
        simpleModel = new DefaultMapModel();

        if (geoEntidadEducativa.getGeoreferenciaX() != null && geoEntidadEducativa.getGeoreferenciaX().intValue() != 0
                && geoEntidadEducativa.getGeoreferenciaY() != null && geoEntidadEducativa.getGeoreferenciaY().intValue() != 0) {
            LatLng coor = new LatLng(geoEntidadEducativa.getGeoreferenciaY().doubleValue(), geoEntidadEducativa.getGeoreferenciaX().doubleValue());
            simpleModel.addOverlay(new Marker(coor, "CE: " + geoEntidadEducativa.getCodigoEntidad()));
        }
    }

    public void preValidacion() {
        Boolean continuar = false;

        proyectoCooperacion.setIdTipoCooperacion(mantenimientoFacade.find(TipoCooperacion.class, idTipoCooperacion));

        switch (proyectoCooperacion.getIdTipoCooperacion().getIdTipoCooperacion().intValue()) {
            case 1:
            case 4:
            case 6:
                datoInfraCe = catalogoFacade.findDatoInfraByCe(codigoEntidad);
                if (datoInfraCe == null) {
                    PrimeFaces.current().executeScript("PF('dlgDatoInfra').show();");
                    continuar = false;
                }else{
                    continuar = true;
                }
                break;
            default:
                continuar = true;
        }

        if (continuar) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(proyectoCooperacion.getFechaEstimadaInicio());
            proyectoCooperacion.setAnho(String.valueOf(calendar.get(java.util.Calendar.YEAR)));
            proyectoCooperacion.setIdEtapaEjecucion((short) 1);
            guardar();
        }
    }

    public void guardar() {
        Date tmpFecha;
        String titulo = RESOURCE_BUNDLE.getString("correo.respuesta.titulo");
        String mensajeParaCe = "";
        String mensajeParaUt = "";
        Boolean necesitaAprobacion = false;

        for (String nivel : nivelI) {
            switch (nivel) {
                case "1":
                    proyectoCooperacion.setInicial((short) 1);
                    break;
                case "2":
                    proyectoCooperacion.setParvularia((short) 2);
                    break;
            }
        }
        for (String nivel : nivelII) {
            switch (nivel) {
                case "3":
                    proyectoCooperacion.setBasicaCi((short) 1);
                    break;
                case "4":
                    proyectoCooperacion.setBasicaCii((short) 1);
                    break;
                case "5":
                    proyectoCooperacion.setBasicaCiii((short) 1);
                    break;
            }
        }
        for (String nivel : nivelIII) {
            switch (nivel) {
                case "6":
                    proyectoCooperacion.setMedia((short) 1);
                    break;
                case "7":
                    proyectoCooperacion.setDocente((short) 1);
                    break;
                case "8":
                    proyectoCooperacion.setBasicaNocturna((short) 1);
                    break;
            }
        }
        for (String nivel : nivelIV) {
            switch (nivel) {
                case "9":
                    proyectoCooperacion.setModFelxible((short) 1);
                    break;
                case "10":
                    proyectoCooperacion.setEspecial((short) 1);
                    break;
                case "11":
                    proyectoCooperacion.setOtros((short) 1);
                    break;
            }
        }

        if (proyectoCooperacion.getIdProyecto() == null) {
            InternetAddress[] to;
            InternetAddress[] cc;

            tmpFecha = new Date();
            proyectoCooperacion.setFechaInsercion(tmpFecha);
            proyectoCooperacion.setUsuarioInsercion(directorCe.getIdDirector());
            proyectoCooperacion.setCodigoEntidad(codigoEntidad);
            proyectoCooperacion.setEstadoEliminacion((short) 0);
            proyectoCooperacion.setIdCooperante(mantenimientoFacade.find(Cooperante.class, idCooperante));
            proyectoCooperacion.setIdModalidad(mantenimientoFacade.find(ModalidadEjecucion.class, idModalidad));
            proyectoCooperacion.setIdTipoInstrumento(mantenimientoFacade.find(TipoInstrumento.class, idTipoInstrumento));

            switch (idTipoCooperacion.intValue()) {
                //RESPUESTA DE APROBACIÓN AUTOMATICA
                case 2:
                case 7:
                case 8:
                case 10:
                case 11:
                case 14:
                    necesitaAprobacion = false;
                    break;

                //RESPUESTA DEPENDERA DEL MONTO DEL PROYECTO
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                    necesitaAprobacion = true;
                    break;
                default:
            }

            proyectoCooperacion.setIdEstado((short) (necesitaAprobacion ? 1 : 2));

            if (mantenimientoFacade.guardar(proyectoCooperacion)) {
                switch (idTipoCooperacion.intValue()) {
                    //RESPUESTA DE APROBACIÓN AUTOMATICA
                    case 2:
                    case 7:
                    case 8:
                    case 10:
                    case 11:
                    case 14:
                        mensajeParaCe = MessageFormat.format(RESOURCE_BUNDLE.getString("correo.respuestaAprobacionAutomatica.mensaje"),
                                StringUtils.getFecha(new Date()), directorCe.getNombreDirector(),
                                entidadEducativa.getNombre(), entidadEducativa.getCodigoEntidad(),
                                proyectoCooperacion.getNombreProyecto(), proyectoCooperacion.getObjetivos());
                        break;

                    //RESPUESTA DEPENDERA DEL MONTO DEL PROYECTO
                    case 1:
                    case 4:
                    case 5:
                    case 6:
                        if (idTipoCooperacion.intValue() != 5) {
                            mensajeParaCe = MessageFormat.format(RESOURCE_BUNDLE.getString("correo.respuestaAprobacionVoBo.mensaje"),
                                    StringUtils.getFecha(new Date()), directorCe.getNombreDirector(),
                                    entidadEducativa.getNombre(), entidadEducativa.getCodigoEntidad(),
                                    proyectoCooperacion.getNombreProyecto(), proyectoCooperacion.getObjetivos());

                        }
                        break;

                    case 3:
                        mensajeParaCe = MessageFormat.format(RESOURCE_BUNDLE.getString("correo.respuestaAprobacionVoBo.mensaje"),
                                StringUtils.getFecha(new Date()),
                                directorCe.getNombreDirector(), entidadEducativa.getNombre(), entidadEducativa.getCodigoEntidad(),
                                proyectoCooperacion.getNombreProyecto(), proyectoCooperacion.getObjetivos());

                        break;
                    default:
                }

                File folderProyecto = new File(JsfUtil.getPathReportes(RESOURCE_BUNDLE, "path_folder") + File.separator + proyectoCooperacion.getIdProyecto() + File.separator);
                if (!folderProyecto.exists()) {
                    folderProyecto.mkdir();
                }

                try {
                    for (UploadedFile updFile : archivosDelProyecto) {
                        Path folder = Paths.get(JsfUtil.getPathReportes(RESOURCE_BUNDLE, "path_folder") + File.separator + proyectoCooperacion.getIdProyecto() + File.separator + updFile.getFileName());
                        Path arc;
                        if (folder.toFile().exists()) {
                            arc = folder;
                        } else {
                            arc = Files.createFile(folder);
                        }

                        try (InputStream input = updFile.getInputStream()) {
                            Files.copy(input, arc, StandardCopyOption.REPLACE_EXISTING);
                        }
                    }

                } catch (IOException ex) {
                    Logger.getLogger(RegistrarCooperacionView.class.getName()).log(Level.SEVERE, null, ex);
                }

                PrimeFaces.current().executeScript("PF('dlgAceptar').show()");
                
                guardarHistoricoCambioEstado(tmpFecha, null, (short) 1, null);
                List<Notificacion> lstNotificacion = catalogoFacade.findNotificacionByTipoCooperacion(proyectoCooperacion.getIdTipoCooperacion().getIdTipoCooperacion());

                String emailsTo = "";
                String emailsCc = "";

                if (!necesitaAprobacion) {
                    emailsTo = directorCe.getCorreoElectronico();
                }

                for (Notificacion notificacion : lstNotificacion) {
                    if (notificacion.getTipoDestinatario() == 1) {
                        if (necesitaAprobacion) {
                            emailsTo = notificacion.getCorreo();
                        } else {
                            emailsTo = emailsTo.concat(",").concat(notificacion.getCorreo());
                        }
                    } else {
                        if (emailsCc.isEmpty()) {
                            emailsCc = notificacion.getCorreo();
                        } else {
                            emailsCc = emailsCc.concat(",").concat(notificacion.getCorreo());
                        }
                    }
                }

                to = new InternetAddress[emailsTo.split(",").length];
                cc = new InternetAddress[emailsCc.split(",").length];

                try {
                    for (int i = 0; i < emailsTo.split(",").length; i++) {
                        to[i] = new InternetAddress(emailsTo.split(",")[i]);
                    }
                    for (int i = 0; i < emailsCc.split(",").length; i++) {
                        cc[i] = new InternetAddress(emailsCc.split(",")[i]);
                    }

                    //Session session = credencialesView.getMailSession();
                    if (necesitaAprobacion) {
                        RC4Crypter seguridad = new RC4Crypter();
                        mensajeParaUt = MessageFormat.format(RESOURCE_BUNDLE.getString("correo.notificacionDeCooperacion.mensaje"),
                                StringUtils.getFecha(new Date()),
                                entidadEducativa.getNombre(), entidadEducativa.getCodigoEntidad(),
                                seguridad.encrypt("ha", "".concat(proyectoCooperacion.getIdProyecto().toString()).concat("::").concat(proyectoCooperacion.getIdCooperante().getIdCooperante().toString()).concat("::").concat(proyectoCooperacion.getCodigoEntidad()))
                        );                    
                        
                        
                        //CORREO PARA UNIDAD TÉNICA
                        eMailFacade.enviarMail(to, cc, RESOURCE_BUNDLE.getString("remitente_correo"), titulo, mensajeParaUt, credencialesView.getMailSessionRemitente());

                        to = new InternetAddress[emailsTo.split(",").length];
                        to[0] = new InternetAddress(directorCe.getCorreoElectronico());

                        //CORREO PARA CENTRO ESCOLAR
                        eMailFacade.enviarMail(to, cc, RESOURCE_BUNDLE.getString("remitente_correo"), titulo, mensajeParaCe, credencialesView.getMailSessionRemitente());
                    } else {
                        //CORREO PARA CENTRO ESCOLAR
                        eMailFacade.enviarMail(to, cc, RESOURCE_BUNDLE.getString("remitente_correo"), titulo, mensajeParaCe, credencialesView.getMailSessionRemitente());
                    }

                } catch (AddressException ex) {
                    Logger.getLogger(RegistrarCooperacionView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JsfUtil.mensajeError("Ah ocurrido un error en la operación de guardar.");
            }
        } else {
            mantenimientoFacade.modificar(proyectoCooperacion);
        }
    }

    private void guardarHistoricoCambioEstado(Date tmpFecha, Short cambioAnt, Short cambioNew, String comentario) {
        HisCambioEstadoPro historico = new HisCambioEstadoPro();
        historico.setFechaCambio(tmpFecha);
        historico.setIdEstadoAnt(cambioAnt);
        historico.setIdEstadoNew(cambioNew);
        historico.setComentario(comentario);
        historico.setIdProyecto(proyectoCooperacion.getIdProyecto());
        historico.setUsuarioCambio(directorCe.getIdDirector());

        mantenimientoFacade.guardar(historico);
    }

    public void handleFileUpload(FileUploadEvent event) {
        archivosDelProyecto.add(event.getFile());
        PrimeFaces.current().ajax().update("lstArchivosDelProyecto");
    }

    public void guardarDatoInfraCe() {
        if (datoInfraCe.getIdDatoInfra() == null) {
            switch (proyectoCooperacion.getIdTipoCooperacion().getIdTipoCooperacion().intValue()) {
                case 1:
                case 4:
                case 6:
                    datoInfraCe.setCodigoEntidad(codigoEntidad);
                    mantenimientoFacade.guardar(datoInfraCe);
                    break;
            }
        }
        preValidacion();
    }

    public void prueba() {
    }
}
