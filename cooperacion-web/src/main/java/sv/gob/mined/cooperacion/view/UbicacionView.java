/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sv.gob.mined.cooperacion.facade.paquete.UbicacionFacade;
import sv.gob.mined.cooperacion.model.paquete.Departamento;
import sv.gob.mined.cooperacion.model.paquete.Municipio;

@Named
@ViewScoped
public class UbicacionView implements Serializable {

    private String codigoDepartamento;
    private List<Municipio> lstMunicipio = new ArrayList();

    @Inject
    private UbicacionFacade ubicacionFacade;

    public List<Departamento> getLstDepartamentos() {
        return ubicacionFacade.getLstDepartamentos();
    }

    public List<Municipio> getLstMunicipio() {
        return lstMunicipio;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public void recuperarMunicipios() {
        lstMunicipio = ubicacionFacade.getLstMunicipio(codigoDepartamento);
    }
}
