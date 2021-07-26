/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.facade.paquete;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.gob.mined.cooperacion.model.paquete.Departamento;
import sv.gob.mined.cooperacion.model.paquete.Municipio;
import sv.gob.mined.cooperacion.model.paquete.VwCatalogoEntidadEducativa;

/**
 *
 * @author misanchez
 */
@Stateless
public class UbicacionFacade {

    @PersistenceContext(unitName = "paquetePU")
    private EntityManager emPaquete;

    public List<Departamento> getLstDepartamentos() {
        Query q = emPaquete.createQuery("SELECT d FROM Departamento d ORDER BY d.codigoDepartamento", Departamento.class);
        return q.getResultList();
    }

    public List<Municipio> getLstMunicipio(String codigoDepa) {
        Query q = emPaquete.createQuery("SELECT m FROM Municipio m WHERE m.codigoDepartamento.codigoDepartamento=:codDepa ORDER BY m.codigoMunicipio", Departamento.class);
        q.setParameter("codDepa", codigoDepa);
        return q.getResultList();
    }

    public VwCatalogoEntidadEducativa findEntidadEducativaByCodigo(String codigoEntidad) {
        Query q = emPaquete.createQuery("SELECT v FROM VwCatalogoEntidadEducativa v WHERE v.codigoEntidad = :codigoEntidad", VwCatalogoEntidadEducativa.class);
        q.setParameter("codigoEntidad", codigoEntidad);
        return q.getResultList().isEmpty() ? null : (VwCatalogoEntidadEducativa) q.getSingleResult();
    }

}
