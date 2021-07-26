/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.cooperacion.util;

/**
 *
 * @author MISanchez
 */
public class QueryNativas {

    public final static String SQL_LISTADO_PROYECTOS = "select \n"
            + "    pro.id_proyecto         as idProyecto,\n"
            + "    nombre                  as nombreCe,\n"
            + "    vw.codigo_entidad       as codigoEntidad,\n"
            + "    dep.nombre_departamento as nombreDepartamento,\n"
            + "    mun.nombre_municipio    as nombreMunicipio,\n"
            + "    dir.nombre_director     as nombreDirector,\n"
            + "    dir.correo_electronico  as correoElectronico,\n"
            + "    pro.nombre_proyecto     as nombreProyecto,\n"
            + "    pro.fecha_estimada_inicio   as fechaInicio,\n"
            + "    pro.fecha_estimada_fin      as fechaFin,\n"
            + "    mun.codigo_departamento as codigoDepartamento,\n"
            + "    mun.codigo_municipio    as codigoMunicipio,\n"
            + "    mun.id_municipio        as idMunicipio,\n"
            + "    pro.id_estado           as idEstado,\n"
            + "    geo.georeferencia_x     as geoPx,\n"
            + "    geo.georeferencia_y     as geoPy,\n"
            + "    pro.id_cooperante        as idCooperante,\n"
            + "    pro.id_tipo_cooperacion  as idTipoCooperacion,\n"
            + "    rownum                   as idRow,\n"
            + "    pro.anho                 as anho\n"
            + "from proyecto_cooperacion pro\n"
            + "    inner join vw_Catalogo_entidad_educativa vw on pro.codigo_entidad = vw.codigo_entidad\n"
            + "    inner join paquetes.departamento dep on vw.codigo_departamento = dep.codigo_departamento\n"
            + "    inner join paquetes.municipio mun on mun.codigo_municipio = vw.codigo_municipio and mun.codigo_departamento = dep.codigo_departamento\n"
            + "    inner join director dir on pro.codigo_entidad = dir.codigo_entidad\n"
            + "    left outer join geo_entidad_educativa geo on geo.codigo_entidad = vw.codigo_entidad\n"
            + "where pro.estado_eliminacion=0 ";
}
