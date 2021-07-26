/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.view;

import javax.inject.Named;
import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author MISanchez
 */
@Named
@ViewScoped
public class UserBean implements Serializable {
    

    public Boolean getCentroEscolar() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return "CE".equals(fc.getExternalContext().getSessionMap().get("role"));
    }

    public String getPerfilUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext().getSessionMap().get("role").toString();
    }

    public void validarUsuario(String role) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc.getExternalContext().getSessionMap().get("role") == null) {
            redirigirAccessDenied(fc);
        } else if (role == null || role.isEmpty()) {
            //sigue su curso normal
        } else if (!role.equals(fc.getExternalContext().getSessionMap().get("role"))) {
            redirigirAccessDenied(fc);
        }
    }

    private void redirigirAccessDenied(FacesContext fc) {
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        nav.performNavigation("access-denied");
    }
    
    public void validar(){
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc.getExternalContext().getSessionMap().get("role") == null) {
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
        nav.performNavigation("/index.mined");
        }
    }
}
