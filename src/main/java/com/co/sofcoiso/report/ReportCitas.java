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
public class ReportCitas {

    String idCita;
    String Persona;
    String fecha;
    String estado;
    Integer cedula;
    private List<String> header;

    private List<ReportCitas> bodyTable;
    public static String sql = "select c.cedula, p.nombre || ' ' || p.apellido_uno || ' ' || p.apellido_dos as nombre, "
            + "c.fecha_cita, c.estado "
            + "from cita c "
            + "inner join persona p on c.cedula = p.cedula "
            + "where c.fecha_cita BETWEEN ? AND ?";

    public ReportCitas(String idCita, String Persona, String fecha, String estado, Integer cedula) {
        this.idCita = idCita;
        this.Persona = Persona;
        this.fecha = fecha;
        this.estado = estado;
        this.cedula = cedula;
        initHeader();
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<ReportCitas> getBodyTable() {
        return bodyTable;
    }

    public void setBodyTable(List<ReportCitas> bodyTable) {
        this.bodyTable = bodyTable;
    }

    public static String getSql() {
        return sql;
    }

    public static void setSql(String sql) {
        ReportCitas.sql = sql;
    }

    public ReportCitas() {
        initHeader();
    }
    
    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getPersona() {
        return Persona;
    }

    public void setPersona(String Persona) {
        this.Persona = Persona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void initHeader() {
        header = new ArrayList<String>();
        header.add("Cedula");
        header.add("Nombre");
        header.add("fecha");
        header.add("Estado");

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

        for (ReportCitas campo : bodyTable) {
            body.append("<tr>");
            body.append("<td>" + campo.getCedula() + "</td>");
            body.append("<td>" + campo.getPersona() + "</td>");
            body.append("<td>" + campo.getFecha() + "</td>");
            body.append("<td>" + campo.getEstado() + "</td>");
            body.append("</tr>");
        }
        body.append("</tbody>");
        return body.toString();
    }

}
