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
import org.primefaces.event.SelectEvent;
import sv.gob.mined.cooperacion.facade.CatalogoFacade;
import sv.gob.mined.cooperacion.facade.MantenimientoFacade;
import sv.gob.mined.cooperacion.model.Meta;
import sv.gob.mined.cooperacion.model.ObjetivoDesarrollo;
import sv.gob.mined.cooperacion.model.ProyectoCooperacion;
import sv.gob.mined.utils.jsf.JsfUtil;

/**
 *
 * @author misanchez
 */
@Named
@ViewScoped
public class AsociarProyectoMetaView implements Serializable {

    private Integer idObjetivo;
    private Integer idMeta;

    private ProyectoCooperacion proyectoCooperacion = new ProyectoCooperacion();
    private List<ProyectoCooperacion> lstProyecto = new ArrayList();

    @Inject
    private CatalogoFacade catalogoFacade;
    @Inject
    private MantenimientoFacade mantenimientoFacade;

    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public ProyectoCooperacion getProyectoCooperacion() {
        return proyectoCooperacion;
    }

    public void setProyectoCooperacion(ProyectoCooperacion proyectoCooperacion) {
        this.proyectoCooperacion = proyectoCooperacion;
    }

    public List<ProyectoCooperacion> getLstProyectos() {
        return lstProyecto;
    }

    public void buscarProyecto() {
        lstProyecto = catalogoFacade.findAllProyectos();
    }

    public List<ObjetivoDesarrollo> getObjetivosDesarrollo() {
        return catalogoFacade.findAllObjetivos();
    }

    public List<Meta> getMeta() {
        return catalogoFacade.findMetaByObjetivo(idObjetivo);
    }

    public void proyectoSelect(SelectEvent<ProyectoCooperacion> event) {
        proyectoCooperacion = event.getObject();
        idMeta = proyectoCooperacion.getIdMeta();
        idObjetivo = catalogoFacade.getObjetivoByIdMeta(idMeta).getIdObjetivo();
    }
    
    public void guardar(){
        proyectoCooperacion.setIdMeta(idMeta);
        mantenimientoFacade.modificar(proyectoCooperacion);
        JsfUtil.mensajeUpdate();
    }
}
