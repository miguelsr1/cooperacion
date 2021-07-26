package sv.gob.mined.cooperacion.facade;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import sv.gob.mined.cooperacion.model.Cooperante;
import sv.gob.mined.cooperacion.model.DatoInfraCe;
import sv.gob.mined.cooperacion.model.Director;
import sv.gob.mined.cooperacion.model.EeGeoDepartamento;
import sv.gob.mined.cooperacion.model.EeGeoMunicipio;
import sv.gob.mined.cooperacion.model.GeoEntidadEducativa;
import sv.gob.mined.cooperacion.model.Meta;
import sv.gob.mined.cooperacion.model.ModalidadEjecucion;
import sv.gob.mined.cooperacion.model.Notificacion;
import sv.gob.mined.cooperacion.model.ObjetivoDesarrollo;
import sv.gob.mined.cooperacion.model.ProyectoCooperacion;
import sv.gob.mined.cooperacion.model.TipoCooperacion;
import sv.gob.mined.cooperacion.model.TipoCooperante;
import sv.gob.mined.cooperacion.model.TipoInstrumento;
import sv.gob.mined.cooperacion.model.Usuario;
import sv.gob.mined.cooperacion.model.dto.MatrizProyectoDto;

@Stateless
public class CatalogoFacade {

    @PersistenceContext(unitName = "cooperacionPU")
    private EntityManager emCooperacion;

    public List<Cooperante> findAllCooperante() {
        Query q = emCooperacion.createQuery("SELECT c FROM Cooperante c ORDER BY c.idCooperante", Cooperante.class);
        return q.getResultList();
    }

    public List<TipoCooperante> findTipoCooperante() {
        Query q = emCooperacion.createQuery("SELECT t FROM TipoCooperante t", TipoCooperante.class);
        return q.getResultList();
    }

    public List<TipoCooperacion> findTipoCooperacion() {
        Query q = emCooperacion.createQuery("SELECT t FROM TipoCooperacion t", TipoCooperacion.class);
        return q.getResultList();
    }

    public List<ModalidadEjecucion> findModalidadEjecucion() {
        Query q = emCooperacion.createQuery("SELECT m FROM ModalidadEjecucion m", ModalidadEjecucion.class);
        return q.getResultList();
    }

    public List<TipoInstrumento> findTipoInstrumento() {
        Query q = emCooperacion.createQuery("SELECT t FROM TipoInstrumento t", TipoInstrumento.class);
        return q.getResultList();
    }

    public GeoEntidadEducativa findGeoEntidadEducativaByCodigo(String codigoEntidad) {
        Query q = emCooperacion.createQuery("SELECT g FROM GeoEntidadEducativa g WHERE g.codigoEntidad = :codigoEntidad", GeoEntidadEducativa.class);
        q.setParameter("codigoEntidad", codigoEntidad);
        return q.getResultList().isEmpty() ? null : (GeoEntidadEducativa) q.getSingleResult();
    }

    public EeGeoDepartamento getGeoDepartamentoByCodDepa(String codigoDepartamento) {
        Query q = emCooperacion.createQuery("SELECT e FROM EeGeoDepartamento e WHERE e.codigoDepartamento=:codDepa", EeGeoDepartamento.class);
        q.setParameter("codDepa", codigoDepartamento);
        return q.getResultList().isEmpty() ? null : (EeGeoDepartamento) q.getSingleResult();
    }

    public EeGeoMunicipio getGeoMunicipioByCodMuniAndDepa(String codigoDepartamento, String codigoMunicipio) {
        Query q = emCooperacion.createQuery("SELECT e FROM EeGeoMunicipio e WHERE e.codigoDepartamento=:codDepa and e.codigoMunicipio=:codMuni", EeGeoMunicipio.class);
        q.setParameter("codDepa", codigoDepartamento);
        q.setParameter("codMuni", codigoMunicipio);
        return q.getResultList().isEmpty() ? null : (EeGeoMunicipio) q.getSingleResult();
    }

    public Director findDirectorByCodigoEntidad(String codigoEntidad) {
        Query q = emCooperacion.createQuery("SELECT d FROM Director d WHERE d.codigoEntidad=:codEnt AND d.activo=1", Director.class);
        q.setParameter("codEnt", codigoEntidad);
        return q.getResultList().isEmpty() ? null : (Director) q.getSingleResult();
    }

    public Director findDirectorByEmail(String email) {
        Query q = emCooperacion.createQuery("SELECT d FROM Director d WHERE d.correoElectronico=:email AND d.activo=1", Director.class);
        q.setParameter("email", email);
        return q.getResultList().isEmpty() ? null : (Director) q.getResultList().get(0);
    }

    public Usuario findUsuarioByEmail(String email) {
        Query q = emCooperacion.createQuery("SELECT u FROM Usuario u WHERE u.usuario=:usu and u.activo = 1", Usuario.class);
        q.setParameter("usu", email);
        return q.getResultList().isEmpty() ? null : (Usuario) q.getResultList().get(0);
    }

    public List<Notificacion> findNotificacionByTipoCooperacion(Long idTipoCooperacion) {
        Query q = emCooperacion.createQuery("SELECT n FROM Notificacion n WHERE n.idTipoCooperacion=:idTipo and n.estadoEliminacion=0 ORDER BY n.idNotificacion", Notificacion.class);
        q.setParameter("idTipo", idTipoCooperacion);
        return q.getResultList();
    }

    public DatoInfraCe findDatoInfraByCe(String codigoEntidad) {
        Query q = emCooperacion.createQuery("SELECT d FROM DatoInfraCe d WHERE d.codigoEntidad=:codEnt", DatoInfraCe.class);
        q.setParameter("codEnt", codigoEntidad);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (DatoInfraCe) q.getResultList().get(0);
        }
    }

    public List<MatrizProyectoDto> getMatrizProyectosByAnho(String anho) {
        Query q = emCooperacion.createNamedQuery("Cooperacion.MatrizProyecto", MatrizProyectoDto.class);
        q.setParameter(1, anho);
        return q.getResultList();
    }

    public JasperPrint getRpt(HashMap map, InputStream input) {
        try {
            JasperPrint jp;
            Connection conn = emCooperacion.unwrap(java.sql.Connection.class);
            jp = JasperFillManager.fillReport(input, map, conn);

            input.close();
            return jp;
        } catch (JRException | IOException ex) {
            Logger.getLogger(CatalogoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<ObjetivoDesarrollo> findAllObjetivos() {
        Query q = emCooperacion.createQuery("SELECT o FROM ObjetivoDesarrollo o ORDER BY o.idObjetivo", ObjetivoDesarrollo.class);
        return q.getResultList();
    }

    public List<Meta> findMetaByObjetivo(Integer idObjetivo) {
        Query q = emCooperacion.createQuery("SELECT m FROM Meta m WHERE m.idObjetivo.idObjetivo = :pIdObjetivo ORDER BY m.idMeta", Meta.class);
        q.setParameter("pIdObjetivo", idObjetivo);
        return q.getResultList();
    }

    public List<ProyectoCooperacion> findAllProyectos() {
        Query q = emCooperacion.createQuery("SELECT p FROM ProyectoCooperacion p ORDER BY p.idProyecto", ProyectoCooperacion.class);
        return q.getResultList();
    }

    public ObjetivoDesarrollo getObjetivoByIdMeta(Integer idMeta) {
        return emCooperacion.find(Meta.class, idMeta).getIdObjetivo();
    }
}
