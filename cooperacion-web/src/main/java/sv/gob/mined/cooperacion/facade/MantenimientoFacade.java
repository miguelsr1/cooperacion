package sv.gob.mined.cooperacion.facade;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.gob.mined.cooperacion.model.Cooperante;
import sv.gob.mined.cooperacion.model.Director;
import sv.gob.mined.cooperacion.model.FechaCapacitacion;
import sv.gob.mined.cooperacion.model.ProyectoCooperacion;
import sv.gob.mined.cooperacion.model.TipoCooperacion;
import sv.gob.mined.cooperacion.model.dto.AtributoValorDto;
import sv.gob.mined.cooperacion.model.dto.ListadoProyectoDto;
import sv.gob.mined.cooperacion.util.QueryNativas;

@Stateless
public class MantenimientoFacade {

    @PersistenceContext(unitName = "cooperacionPU")
    private EntityManager emCooperacion;

    public Boolean guardar(Object obj) {
        try {
            emCooperacion.persist(obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public <T extends Object> T find(Class<T> entityClass, Object primaryKey) {
        return emCooperacion.find(entityClass, primaryKey);
    }

    public <T extends Object> T modificar(T entity) {
        emCooperacion.merge(entity);
        return entity;
    }

    public List<ListadoProyectoDto> findAllProyectos() {
        Query q = emCooperacion.createNativeQuery(QueryNativas.SQL_LISTADO_PROYECTOS + " ORDER BY pro.FECHA_INSERCION, mun.codigo_departamento, mun.codigo_municipio, vw.codigo_entidad", ListadoProyectoDto.class);
        return q.getResultList();
    }

    public List<ListadoProyectoDto> findProyectosByWhereCustom(String where) {
        Query q = emCooperacion.createNativeQuery(QueryNativas.SQL_LISTADO_PROYECTOS + where + " ORDER BY pro.FECHA_INSERCION, mun.codigo_departamento, mun.codigo_municipio, vw.codigo_entidad", ListadoProyectoDto.class);
        return q.getResultList();
    }
    
    public List<AtributoValorDto> findAnhosDeProyecto(){
        Query q = emCooperacion.createNamedQuery("Cooperacion.AnhoProyecto", AtributoValorDto.class);
        return q.getResultList();
    }

    public List<ListadoProyectoDto> findProyectosByEstado(Short idEstado, String codigoDepartamento, BigDecimal idMunicipio) {
        String where = "";
        if (codigoDepartamento != null) {
            where = " mun.codigo_departamento='" + codigoDepartamento + "'";
        }
        if (idMunicipio != null) {
            if (where.isEmpty()) {
                where = " mun.id_municipio=" + idMunicipio;
            } else {
                where = where + " and mun.id_municipio=" + idMunicipio;
            }
        }

        if (!where.isEmpty()) {
            where = where + " and pro.id_estado =" + idEstado;
        }

        Query q = emCooperacion.createNativeQuery(QueryNativas.SQL_LISTADO_PROYECTOS + where + " ORDER BY mun.codigo_departamento, mun.codigo_municipio, vw.codigo_entidad", ListadoProyectoDto.class);
        return q.getResultList();
    }

    public List<ProyectoCooperacion> findProyectosByCodEntAndAnho(String codigoEntidad, String anho) {
        Query q = emCooperacion.createQuery("SELECT p FROM ProyectoCooperacion p WHERE p.codigoEntidad=:codEnt and p.anho=:anho", ProyectoCooperacion.class);
        q.setParameter("codEnt", codigoEntidad);
        q.setParameter("anho", anho);
        return q.getResultList();
    }

    public List<ProyectoCooperacion> findProyectosByCodEnt(String codigoEntidad) {
        Query q = emCooperacion.createQuery("SELECT p FROM ProyectoCooperacion p WHERE p.codigoEntidad=:codEnt", ProyectoCooperacion.class);
        q.setParameter("codEnt", codigoEntidad);
        return q.getResultList();
    }

    public List<Cooperante> findAllCooperantes() {
        Query q = emCooperacion.createQuery("SELECT c FROM Cooperante c ORDER BY c.nombreCooperante", Cooperante.class);
        return q.getResultList();
    }

    public List<TipoCooperacion> findAllTipoCoopeciones() {
        Query q = emCooperacion.createQuery("SELECT t FROM TipoCooperacion t ORDER BY t.descripcionCooperacion", TipoCooperacion.class);
        return q.getResultList();
    }

    public List<FechaCapacitacion> findAllCapacitaciones() {
        Query q = emCooperacion.createQuery("SELECT f FROM FechaCapacitacion f ORDER BY f.fechaInicio", FechaCapacitacion.class);
        return q.getResultList();
    }

    public List<FechaCapacitacion> findCapacitacionesByProyecto(Long idProyecto) {
        Query q = emCooperacion.createQuery("SELECT f FROM FechaCapacitacion f WHERE f.idProyecto.idProyecto=:id ORDER BY f.fechaInicio", FechaCapacitacion.class);
        q.setParameter("id", idProyecto);
        return q.getResultList();
    }

    public ProyectoCooperacion getProyectoByIdAndCooperanteAndCodEnt(Long idProyecto, Long idCooperante, String codigoEntidad) {
        Query q = emCooperacion.createQuery("SELECT p FROM ProyectoCooperacion p WHERE p.idProyecto=:id and p.idCooperante.idCooperante=:idCo and p.codigoEntidad=:cod", ProyectoCooperacion.class);
        q.setParameter("id", idProyecto);
        q.setParameter("idCo", idCooperante);
        q.setParameter("cod", codigoEntidad);
        return q.getResultList().isEmpty() ? null : (ProyectoCooperacion) q.getResultList().get(0);
    }

    public Director getDirectorByCodigoEntidad(String codigoEntidad) {
        Query q = emCooperacion.createQuery("select d from Director d where d.codigoEntidad=:cod", Director.class);
        q.setParameter("cod", codigoEntidad);
        return (Director) q.getResultList().get(0);
    }
    
    public List<Cooperante> findCooperantesDeProyecto(){
        Query q = emCooperacion.createQuery("SELECT distinct p.idCooperante FROM ProyectoCooperacion p ORDER BY p.idCooperante.nombreCooperante", Cooperante.class);
        return q.getResultList();
    }
}
