/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.report;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author manue
 */
public class ReportCasos {

    private static final long serialVersionUID = 1L;

    String nombreEstado;
    String cantidadEstado;
    String codigoCaso;
    String descripcion_caso;
    String fecha_inicio_afectacion;
    String pcl;
    String parte_afectada;
    String tiempo_incapacidad;
    String creado;
    String persona_cedula;
    String asignado;
    String tipo_caso;
    String nombre_tipo_caso;
    String nombre_estado;
    String nombrePersona;
    String fechaCreacion;
    String fechaActualizacion;
    private List<String> header;

    private List<ReportCasos> bodyTable;
    public static String sql = "select c.codigocaso as codigo, P.cedula, "
            + "p.nombre || ' ' || p.apellido_uno || ' ' || p.apellido_dos as nombre, c.descripcion_caso as descripcion, "
            + "c.fecha_inicio_afectacion, c.pcl, c.parte_afectada, c.tiempo_incapacidad, "
            + "c.creado, c.asignado, es.nombre_estado as estado, tc.tipo_caso as tipo, tc.nombre_tipo_caso, "
            + "fc.fecha_creacion, fc.fecha_actualizacion "
            + "from caso c "
            + "inner join estado_caso es on es.codigoestado = c.estado_caso_codigoestado "
            + "inner join tipo_caso tc on tc.codigo_tipo_caso = c.tipo_caso_codigo_tipo_caso "
            + "inner join persona p on p.cedula = c.persona_cedula "
            + "inner join flujocaso fc on fc.codigocaso = c.codigocaso "
            + "where fc.fecha_creacion BETWEEN ? AND ? ";

    public ReportCasos() {
        initHeader();
    }

    public ReportCasos(String nombreEstado, String cantidadEstado, String codigoCaso, String descripcion_caso, String fecha_inicio_afectacion, String pcl, String parte_afectada, String tiempo_incapacidad, String creado, String persona_cedula, String asignado, String tipo_caso, String nombre_tipo_caso, String nombre_estado, String nombrePersona, String fechaCreacion, String fechaActualizacion, List<String> header, List<ReportFormacion> bodyTable) {
        this.nombreEstado = nombreEstado;
        this.cantidadEstado = cantidadEstado;
        this.codigoCaso = codigoCaso;
        this.descripcion_caso = descripcion_caso;
        this.fecha_inicio_afectacion = fecha_inicio_afectacion;
        this.pcl = pcl;
        this.parte_afectada = parte_afectada;
        this.tiempo_incapacidad = tiempo_incapacidad;
        this.creado = creado;
        this.persona_cedula = persona_cedula;
        this.asignado = asignado;
        this.tipo_caso = tipo_caso;
        this.nombre_tipo_caso = nombre_tipo_caso;
        this.nombre_estado = nombre_estado;
        this.nombrePersona = nombrePersona;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        initHeader();
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<ReportCasos> getBodyTable() {
        return bodyTable;
    }

    public void setBodyTable(List<ReportCasos> bodyTable) {
        this.bodyTable = bodyTable;
    }

    public static String getSql() {
        return sql;
    }

    public static void setSql(String sql) {
        ReportCasos.sql = sql;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getDescripcion_caso() {
        return descripcion_caso;
    }

    public void setDescripcion_caso(String descripcion_caso) {
        this.descripcion_caso = descripcion_caso;
    }

    public String getFecha_inicio_afectacion() {
        return fecha_inicio_afectacion;
    }

    public void setFecha_inicio_afectacion(String fecha_inicio_afectacion) {
        this.fecha_inicio_afectacion = fecha_inicio_afectacion;
    }

    public String getPcl() {
        return pcl;
    }

    public void setPcl(String pcl) {
        this.pcl = pcl;
    }

    public String getParte_afectada() {
        return parte_afectada;
    }

    public void setParte_afectada(String parte_afectada) {
        this.parte_afectada = parte_afectada;
    }

    public String getTiempo_incapacidad() {
        return tiempo_incapacidad;
    }

    public void setTiempo_incapacidad(String tiempo_incapacidad) {
        this.tiempo_incapacidad = tiempo_incapacidad;
    }

    public String getCreado() {
        return creado;
    }

    public void setCreado(String creado) {
        this.creado = creado;
    }

    public String getPersona_cedula() {
        return persona_cedula;
    }

    public void setPersona_cedula(String persona_cedula) {
        this.persona_cedula = persona_cedula;
    }

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(String asignado) {
        this.asignado = asignado;
    }

    public String getTipo_caso() {
        return tipo_caso;
    }

    public void setTipo_caso(String tipo_caso) {
        this.tipo_caso = tipo_caso;
    }

    public String getNombre_tipo_caso() {
        return nombre_tipo_caso;
    }

    public void setNombre_tipo_caso(String nombre_tipo_caso) {
        this.nombre_tipo_caso = nombre_tipo_caso;
    }

    public String getNombre_estado() {
        return nombre_estado;
    }

    public void setNombre_estado(String nombre_estado) {
        this.nombre_estado = nombre_estado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getCantidadEstado() {
        return cantidadEstado;
    }

    public void setCantidadEstado(String cantidadEstado) {
        this.cantidadEstado = cantidadEstado;
    }

     public void initHeader() {
        header = new ArrayList<String>();
        header.add("Codigo");
        header.add("Cedula");
        header.add("Persona");
        header.add("Fecha Afectacion");
        header.add("Estado");
        header.add("Tipo");
        header.add("Caso");
        header.add("Fecha");

    }

    public String getTableHtml() {
        String uniqueID = UUID.randomUUID().toString();
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table id = '" + uniqueID + "' class=\"table table-bordered table-hover\">");
        htmlTable.append(getHeaders());
        htmlTable.append(getBody());
        htmlTable.append("</table>");
        return htmlTable.toString();

    }

    private String getHeaders() {
        StringBuilder Header = new StringBuilder();
        Header.append("<thead><tr>");
        for (String campo : header) {
            Header.append("<th>");
            Header.append(campo);
            Header.append("</th>");
        }
        Header.append("</tr></thead>");
        return Header.toString();
    }

    private String getBody() {
        StringBuilder body = new StringBuilder();
        body.append("<tbody>");

        for (ReportCasos casos : bodyTable) {
            body.append("<tr>");
            body.append("<td>" + casos.getCodigoCaso() + "</td>");
            body.append("<td>" + casos.getPersona_cedula() + "</td>");
            body.append("<td>" + casos.getNombrePersona()+ "</td>");
            body.append("<td>" + casos.getNombreEstado() + "</td>");
            body.append("<td>" + casos.getTipo_caso() + "</td>");
            body.append("<td>" + casos.getNombre_tipo_caso() + "</td>");
            body.append("<td>" + casos.getFechaCreacion() + "</td>");
            body.append("</tr>");
        }
        body.append("</tbody>");
        return body.toString();
    }

}
