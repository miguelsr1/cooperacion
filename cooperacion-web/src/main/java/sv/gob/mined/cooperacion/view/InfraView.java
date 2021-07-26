/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import sv.gob.mined.cooperacion.facade.CatalogoFacade;
import sv.gob.mined.cooperacion.facade.EMailFacade;
import sv.gob.mined.cooperacion.facade.MantenimientoFacade;
import sv.gob.mined.cooperacion.facade.paquete.UbicacionFacade;
import sv.gob.mined.cooperacion.model.DatoInfraCe;
import sv.gob.mined.cooperacion.model.Director;
import sv.gob.mined.cooperacion.model.ProyectoCooperacion;
import sv.gob.mined.cooperacion.model.dto.FileInfoDto;
import sv.gob.mined.cooperacion.model.paquete.VwCatalogoEntidadEducativa;
import sv.gob.mined.cooperacion.util.RC4Crypter;
import sv.gob.mined.utils.StringUtils;
import sv.gob.mined.utils.jsf.JsfUtil;

@Named
@ViewScoped
public class InfraView implements Serializable {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("bundle");

    private int idTipoObservacion;
    private Boolean codigoBueno = true;
    private Boolean deshabilitarGuardar = false;
    private String observacion = "";
    private String nombreArchivo = "";

    private Director directorCe;
    private DatoInfraCe datoInfraCe;
    private ProyectoCooperacion proyecto;
    private VwCatalogoEntidadEducativa entidadEducativa = new VwCatalogoEntidadEducativa();
    private List<FileInfoDto> lstArchivos = new ArrayList();

    @Inject
    private CatalogoFacade catalogoFacade;
    @Inject
    private MantenimientoFacade mantenimientoFacade;
    @Inject
    private UbicacionFacade ubicacionFacade;
    @Inject
    private CredencialesView credencialesView;
    @Inject
    private EMailFacade eMailFacade;

    @PostConstruct
    public void init() {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.containsKey("id")) {
            RC4Crypter seguridad = new RC4Crypter();

            String[] cod = null;

            try {
                cod = seguridad.decrypt("ha", params.get("id")).split("::");
                //cod = params.get("id");
                proyecto = mantenimientoFacade.find(ProyectoCooperacion.class, Long.parseLong(cod[0]));
                datoInfraCe = catalogoFacade.findDatoInfraByCe(proyecto.getCodigoEntidad());

                deshabilitarGuardar = (proyecto.getIdEstado() == 2);
                cargarArchivos();
            } catch (NumberFormatException e) {
                codigoBueno = false;
            }
        } else {
            codigoBueno = false;
        }
    }

    public Boolean getDeshabilitarGuardar() {
        return deshabilitarGuardar;
    }

    public void setDeshabilitarGuardar(Boolean deshabilitarGuardar) {
        this.deshabilitarGuardar = deshabilitarGuardar;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int getIdTipoObservacion() {
        return idTipoObservacion;
    }

    public void setIdTipoObservacion(int idTipoObservacion) {
        this.idTipoObservacion = idTipoObservacion;
    }

    private void cargarArchivos() {
        entidadEducativa = ubicacionFacade.findEntidadEducativaByCodigo(proyecto.getCodigoEntidad());
        directorCe = mantenimientoFacade.getDirectorByCodigoEntidad(entidadEducativa.getCodigoEntidad());

        lstArchivos.clear();

        File folder = new File(JsfUtil.getPathReportes(RESOURCE_BUNDLE, "path_folder") + File.separator + proyecto.getIdProyecto());

        if (folder.exists()) {
            for (File archivo : folder.listFiles()) {
                try {
                    FileTime fTime = (FileTime) Files.getAttribute(archivo.toPath(), "creationTime");
                    FileInfoDto file = new FileInfoDto();

                    try (PDDocument pdc = PDDocument.load(archivo)) {
                        file.setFechaCreado(new Date(fTime.toMillis()));
                        file.setNombreArchivo(archivo.getName());
                        file.setNumeroPaginas(pdc.getNumberOfPages());

                        lstArchivos.add(file);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ListadoView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Boolean getCodigoBueno() {
        return codigoBueno;
    }

    public void setCodigoBueno(Boolean codigoBueno) {
        this.codigoBueno = codigoBueno;
    }

    public ProyectoCooperacion getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoCooperacion proyecto) {
        this.proyecto = proyecto;
    }

    public List<FileInfoDto> getLstArchivos() {
        return lstArchivos;
    }

    public void notificarRespuestaCe() {
        try {
            String titulo = RESOURCE_BUNDLE.getString("correo.respuesta.titulo");
            String mensajeNotificacionCabecera = "";

            switch (proyecto.getIdEstado()) {
                case 2://aprobado
                    mensajeNotificacionCabecera = RESOURCE_BUNDLE.getString("correo.respuestaAprobacionInfraestructura.mensaje");
                    deshabilitarGuardar = true;
                    break;
                case 3://observado
                    switch (idTipoObservacion) {
                        case 1:
                            mensajeNotificacionCabecera = RESOURCE_BUNDLE.getString("correo.respuestaObsCarpetaTecnicaInfraestructura.mensaje");
                            break;
                        case 2:
                            mensajeNotificacionCabecera = RESOURCE_BUNDLE.getString("correo.respuestaObsVisitaTecnicaInfraestructura.mensaje");
                            break;
                    }
                    break;
            }
            mantenimientoFacade.modificar(proyecto);

            StringBuilder sb = new StringBuilder();

            sb.append(MessageFormat.format(mensajeNotificacionCabecera,
                    StringUtils.getFecha(new Date()), directorCe.getNombreDirector(),
                    entidadEducativa.getNombre(), entidadEducativa.getCodigoEntidad(),
                    datoInfraCe.getDireccion(), datoInfraCe.getMatricula(),
                    (datoInfraCe.getPropiedadInmueble() == 1 ? "MINEDUCYT" : (datoInfraCe.getPropiedadInmueble() == 2 ? "ALCALDIA" : "COMODATO")),
                    proyecto.getNombreProyecto(), proyecto.getObjetivos()));

            enviarCorreo(directorCe.getCorreoElectronico(), titulo, sb.toString());
        } catch (AddressException ex) {
            Logger.getLogger(InfraView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarCorreo(String destinatario, String titulo, String mensaje) throws AddressException {
        InternetAddress[] to = new InternetAddress[1];
        to[0] = new InternetAddress(destinatario);

        Session sesion = credencialesView.getMailSessionRemitente();

        eMailFacade.enviarMail(to, null, credencialesView.getRemitenteOficial(), titulo, mensaje, sesion);
    }

    public StreamedContent getFile() throws FileNotFoundException {
        File filePdf = new File(JsfUtil.getPathReportes(RESOURCE_BUNDLE, "path_folder") + File.separator + proyecto.getIdProyecto() + File.separator + nombreArchivo);
        FileInputStream fis = new FileInputStream(filePdf);
        StreamedContent file = DefaultStreamedContent.builder()
                .name(nombreArchivo)
                .contentType("application/pdf")
                .stream(() -> fis)
                .build();

        return file;
    }
}
