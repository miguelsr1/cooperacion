/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.view;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sv.gob.mined.cooperacion.facade.CatalogoFacade;
import sv.gob.mined.cooperacion.facade.MantenimientoFacade;
import sv.gob.mined.cooperacion.model.ProyectoCooperacion;
import sv.gob.mined.cooperacion.model.Usuario;
import sv.gob.mined.cooperacion.util.RC4Crypter;
import sv.gob.mined.utils.jsf.JsfUtil;

/**
 *
 * @author MISanchez
 */
@Named
@ViewScoped
public class AprobarCapacitacionView implements Serializable {

    private String correoRemitente;
    private String password;
    private String codigoEncritado;

    private Boolean codigoBueno = true;
    private ProyectoCooperacion pro;

    @Inject
    private MantenimientoFacade mantenimientoFacade;

    @Inject
    private CredencialesView credencialesView;

    @Inject
    private CatalogoFacade catalogoFacade;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.containsKey("codigo")) {
            String codigo = params.get("codigo");
            RC4Crypter seguridad = new RC4Crypter();
            String[] valores = null;

            try {
                valores = seguridad.decrypt("ha", codigo).split("::");
                pro = mantenimientoFacade.getProyectoByIdAndCooperanteAndCodEnt(Long.parseLong(valores[0]), Long.parseLong(valores[1]), valores[2]);

                codigoEncritado = seguridad.encrypt("ha", valores[0].concat("::").concat(valores[1]));
                
                String[] cod = seguridad.decrypt("ha", codigoEncritado).split("::");
                pro = mantenimientoFacade.find(ProyectoCooperacion.class, Long.parseLong(cod[0]));
            } catch (Exception e) {
                codigoBueno = false;
            }
        }
    }

    public Boolean getCodigoBueno() {
        return codigoBueno;
    }

    public void setCodigoBueno(Boolean codigoBueno) {
        this.codigoBueno = codigoBueno;
    }

    public String getCorreoRemitente() {
        return correoRemitente;
    }

    public void setCorreoRemitente(String correoRemitente) {
        this.correoRemitente = correoRemitente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validarCrendecialesDelCorreo() {
        String url = "";
        if (correoRemitente != null && password != null) {

            credencialesView.setDominio("2");
            credencialesView.setCorreoRemitente(correoRemitente);
            credencialesView.setPassword(password);
            credencialesView.validarCredencial();

            if (credencialesView.isCorreoValido()) {
                FacesContext context = FacesContext.getCurrentInstance();

                Usuario usuario = catalogoFacade.findUsuarioByEmail(credencialesView.getRemitente());
                //validar que este usuario es de la UT que debe de aprobar este proyecto
                context.getExternalContext().getSessionMap().put("usuario", usuario);

                switch (pro.getIdTipoCooperacion().getIdTipoCooperacion().intValue()) {
                    case 1:
                    case 4:
                    case 6://infraestructura
                        url = "/app/ut/infra?faces-redirect=true&id=" + codigoEncritado;
                        break;
                    case 3:
                    case 15:
                        url = "/app/ut/infod?faces-redirect=true&id=" + codigoEncritado;
                        break;
                }

            } else {
                JsfUtil.mensajeError("Error en el usuario o  clave de acceso.");
            }
        }

        return url;
    }
}
