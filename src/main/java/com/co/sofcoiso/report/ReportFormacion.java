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
public class ReportFormacion {

    private static final long serialVersionUID = 1L;

    String id_formacion;
    String tipo_formacion;
    String fecha_formacion;
    String temas;
    String numeroAsistentes;
    private List<String> header;

  
    
    private List<ReportFormacion> bodyTable;
    public static String sql = "select * from formacion "
            + "where fecha_formacion BETWEEN ? AND ? ";

    public ReportFormacion() {
        initHeader();
    }

    public ReportFormacion(String id_formacion, String tipo_formacion, String fecha_formacion, String temas, String numeroAsistentes) {

        this.id_formacion = id_formacion;
        this.tipo_formacion = tipo_formacion;
        this.fecha_formacion = fecha_formacion;
        this.temas = temas;
        this.numeroAsistentes = numeroAsistentes;
        initHeader();
    }
    
      public String getId_formacion() {
        return id_formacion;
    }

    public void setId_formacion(String id_formacion) {
        this.id_formacion = id_formacion;
    }

    public String getTipo_formacion() {
        return tipo_formacion;
    }

    public void setTipo_formacion(String tipo_formacion) {
        this.tipo_formacion = tipo_formacion;
    }

    public String getFecha_formacion() {
        return fecha_formacion;
    }

    public void setFecha_formacion(String fecha_formacion) {
        this.fecha_formacion = fecha_formacion;
    }

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
    }

    public String getNumeroAsistentes() {
        return numeroAsistentes;
    }

    public void setNumeroAsistentes(String numeroAsistentes) {
        this.numeroAsistentes = numeroAsistentes;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<ReportFormacion> getBodyTable() {
        return bodyTable;
    }

    public void setBodyTable(List<ReportFormacion> bodyTable) {
        this.bodyTable = bodyTable;
    }

    public static String getSql() {
        return sql;
    }

    public static void setSql(String sql) {
        ReportFormacion.sql = sql;
    }
    
    public void initHeader() {
        header = new ArrayList<String>();
        header.add("id_formacion");
        header.add("tipo_formacion");
        header.add("fecha_formacion");
        header.add("temas");
        header.add("numeroAsistentes");

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

        for (ReportFormacion campo : bodyTable) {
            body.append("<tr>");
            body.append("<td>" + campo.getId_formacion() + "</td>");
            body.append("<td>" + campo.getTipo_formacion() + "</td>");
            body.append("<td>" + campo.getFecha_formacion() + "</td>");
            body.append("<td>" + campo.getTemas() + "</td>");
            body.append("<td>" + campo.getNumeroAsistentes() + "</td>");
            body.append("</tr>");
        }
        body.append("</tbody>");
        return body.toString();
    }
    

}
