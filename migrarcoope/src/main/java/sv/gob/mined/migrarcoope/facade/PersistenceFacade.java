/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.migrarcoope.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.gob.mined.migrarcoope.model.ProyectoCooperacion;

/**
 *
 * @author misanchez
 */
@Stateless
public class PersistenceFacade {
    @PersistenceContext(unitName = "cooperacionCe")
    private EntityManager emCooperacion;
    
    
    public void guardar(ProyectoCooperacion proyectoCooperacion){
        emCooperacion.persist(proyectoCooperacion);
    }
}
