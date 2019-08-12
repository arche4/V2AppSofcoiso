/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.report;

import com.co.sofcoiso.modelo.Persona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author manue
 */
public class ReportPersona {

    private static final long serialVersionUID = 1L;

    private Integer cedula;
    private String nombre;
    private String apellidoUno;
    private String apellidoDos;
    private String genero;
    private String fechaNacimiento;
    private String edad;
    private String empresa;
    private String antiguedadEmpresa;
    private String cargo;
    private String fechaClinica;
    private String recomendado;
    private String telefono;
    private List<String> header;
    private String eps;
    private String arl;
    private String afp;
    String direccion;
    String comuna;

   

    private List<ReportPersona> bodyTable;
    public static String sql = "SELECT  P.cedula, p.nombre || ' ' || p.apellido_uno || ' ' || p.apellido_dos as nombre,  "
            + "p.genero, p.fecha_nacimiento, p.edad, p.empresa,  p.antiguedad_empresa,  "
            + "p.cargo, p.fecha_clinica, p.telefono, eps.nombre as Eps, afp.nombre as Afp, arl.nombre as Arl, "
            + "pd.dirreccion, pd.comuna,  p.recomendado  "
            + "FROM persona p INNER JOIN eps on p.codigoeps = eps.codigoeps "
            + "INNER JOIN afp on afp.codigoafp = p.codigoafp   "
            + "INNER JOIN arl  on arl.codigoarl = p.codigoarl "
            + "INNER JOIN persona_dirreccion pd on p.cedula = pd.cedula_persona "
            + "where p.fecha_clinica BETWEEN ? AND ? ";

    public ReportPersona() {
        initHeader();
    }

    public ReportPersona(String cedula, String nombre, String apellidoUno, String apellidoDos, String genero,
            String fechaCumpleaños, String edad, String empresa, String eps, String arl, String codigoafp, String fechaClinica,
            String antiguedad, String telefono, String cargo, String recomendado, String comuna, String direccion) {

        this.cedula = Integer.parseInt(cedula);
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.genero = genero;
        this.fechaNacimiento = fechaCumpleaños;
        this.edad = edad;
        this.empresa = empresa;
        this.antiguedadEmpresa = antiguedad;
        this.telefono = telefono;
        this.eps = eps;
        this.arl = arl;
        this.afp = codigoafp;
        this.cargo = cargo;
        this.fechaClinica = fechaClinica;
        this.recomendado = recomendado;
        this.comuna = comuna;
        this.direccion = direccion;
        initHeader();
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoUno() {
        return apellidoUno;
    }

    public void setApellidoUno(String apellidoUno) {
        this.apellidoUno = apellidoUno;
    }

    public String getApellidoDos() {
        return apellidoDos;
    }

    public void setApellidoDos(String apellidoDos) {
        this.apellidoDos = apellidoDos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getAntiguedadEmpresa() {
        return antiguedadEmpresa;
    }

    public void setAntiguedadEmpresa(String antiguedadEmpresa) {
        this.antiguedadEmpresa = antiguedadEmpresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechaClinica() {
        return fechaClinica;
    }

    public void setFechaClinica(String fechaClinica) {
        this.fechaClinica = fechaClinica;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getArl() {
        return arl;
    }

    public void setArl(String arl) {
        this.arl = arl;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }
    
     public String getRecomendado() {
        return recomendado;
    }

    public void setRecomendado(String recomendado) {
        this.recomendado = recomendado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public List<ReportPersona> getBodyTable() {
        return bodyTable;
    }

    public void setBodyTable(List<ReportPersona> bodyTable) {
        this.bodyTable = bodyTable;
    }
    

    public void initHeader() {
        header = new ArrayList<String>();
        header.add("cedula");
        header.add("nombre");
        header.add("genero");
        header.add("fecha_nacimiento");
        header.add("edad");
        header.add("empresa");
        header.add("antiguedad_empresa");
        header.add("cargo");
        header.add("fecha_clinica");
        header.add("telefono");
        header.add("Eps");
        header.add("Afp");
        header.add("Arl");
        header.add("Recomendado");
        header.add("Sector");
        header.add("direccion");

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

        for (ReportPersona campo : bodyTable) {
            body.append("<tr>");
            body.append("<td>" + campo.getCedula() + "</td>");
            body.append("<td>" + campo.getNombre() + "</td>");
            body.append("<td>" + campo.getGenero() + "</td>");
            body.append("<td>" + campo.getFechaNacimiento() + "</td>");
            body.append("<td>" + campo.getEdad() + "</td>");
            body.append("<td>" + campo.getEmpresa() + "</td>");
            body.append("<td>" + campo.getAntiguedadEmpresa() + "</td>");
            body.append("<td>" + campo.getFechaClinica() + "</td>");
            body.append("<td>" + campo.getTelefono() + "</td>");
            body.append("<td>" + campo.getEps() + "</td>");
            body.append("<td>" + campo.getAfp() + "</td>");
            body.append("<td>" + campo.getArl() + "</td>");
            body.append("<td>" + campo.getRecomendado()+ "</td>");
            body.append("<td>" + campo.getComuna()+ "</td>");
            body.append("<td>" + campo.getDireccion()+ "</td>");
            body.append("</tr>");
        }
        body.append("</tbody>");
        return body.toString();
    }
}
