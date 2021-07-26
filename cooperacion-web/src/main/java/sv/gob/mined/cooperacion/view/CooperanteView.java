package sv.gob.mined.cooperacion.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import sv.gob.mined.cooperacion.facade.CatalogoFacade;
import sv.gob.mined.cooperacion.facade.MantenimientoFacade;
import sv.gob.mined.cooperacion.model.Cooperante;
import sv.gob.mined.cooperacion.model.TipoCooperante;
import sv.gob.mined.utils.jsf.JsfUtil;

@Named
@ViewScoped
public class CooperanteView implements Serializable {

    private Long idTipoCooperante;
    private Cooperante cooperante = new Cooperante();
    
    private List<Cooperante> lstCooperantes = new ArrayList();
   
    @Inject
    private CatalogoFacade catalogoFacade;
    @Inject
    private MantenimientoFacade manttoFacade;
    
    @PostConstruct
    public void init(){
        lstCooperantes = catalogoFacade.findAllCooperante();
    }

    public List<TipoCooperante> getLstTipoCooperante() {
        return catalogoFacade.findTipoCooperante();
    }

    public Cooperante getCooperante() {
        return cooperante;
    }

    public void setCooperante(Cooperante cooperante) {
        this.cooperante = cooperante;
    }

    public Long getIdTipoCooperante() {
        return idTipoCooperante;
    }

    public void setIdTipoCooperante(Long idTipoCooperante) {
        this.idTipoCooperante = idTipoCooperante;
    }

    public void guardarCooperante() {
        cooperante.setIdTipoCooperante(new TipoCooperante(idTipoCooperante));

        if (cooperante.getIdCooperante() == null) {
            manttoFacade.guardar(cooperante);
        } else {
            manttoFacade.modificar(cooperante);
        }

        JsfUtil.mensajeInformacion("Datos almacenados satisfactoriamente");
    }

    public List<Cooperante> getLstCooperantes() {
        return lstCooperantes;
    }
    
    public void onRowSelect(SelectEvent<Cooperante> event) {
        idTipoCooperante = cooperante.getIdTipoCooperante().getIdTipoCooperante();
    }
}
