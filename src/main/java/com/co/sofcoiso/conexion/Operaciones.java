/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.conexion;

import com.co.sofcoiso.ExcelReport.ExcelCreateReport;
import com.co.sofcoiso.report.ReportCitas;
import com.co.sofcoiso.report.ReportFormacion;
import com.co.sofcoiso.report.ReportPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Operaciones {

    public String FechaDia = "";

    public String getConsultas(String Reporte, String fecha_ini, String fecha_fin, String path) throws SQLException {
        String data = "";

        if (Reporte.equals("Personas")) {
            data = reporte_personas(fecha_ini, fecha_fin, Reporte, path);
        } else if (Reporte.equals("Citas")) {
            data = reporte_formacion(fecha_ini, fecha_fin, Reporte, path);
        } else if (Reporte.equals("Formaciones")) {
            data = reporte_personas(fecha_ini, fecha_fin, Reporte, path);
        }

        return data;
    }

    public String reporte_personas(String fecha_ini, String fecha_fin, String tCons, String path) throws SQLException {

        //Conexion Base de Datos
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Conexion con = new Conexion();
        connection = con.conPostgreSQL;
        String data = "";

        try {

            //Variables Locales
            ReportPersona mihtmlTabla = new ReportPersona();
            List<ReportPersona> datosReport = new ArrayList<>(); //Variable donde se guarda la consulta

            //Preparación del query
            stmt = connection.prepareStatement(mihtmlTabla.sql);
            stmt.setString(1, fecha_ini.trim());
            stmt.setString(2, fecha_fin.trim());
            //Ejecución del query
            rs = stmt.executeQuery();

            //Recorrer la consulta y generar el reporte
            while (rs.next()) {

                ReportPersona thisObjRepo = new ReportPersona();
                thisObjRepo.setCedula(rs.getInt(1));
                thisObjRepo.setNombre(rs.getString(2));
                thisObjRepo.setGenero(rs.getString(3));
                thisObjRepo.setFechaNacimiento(rs.getString(4));
                thisObjRepo.setEdad(rs.getString(5));
                thisObjRepo.setEmpresa(rs.getString(6));
                thisObjRepo.setAntiguedadEmpresa(rs.getString(7));
                thisObjRepo.setCargo(rs.getString(8));
                thisObjRepo.setFechaClinica(rs.getString(9));
                thisObjRepo.setTelefono(rs.getString(10));
                thisObjRepo.setEps(rs.getString(11));
                thisObjRepo.setArl(rs.getString(12));
                thisObjRepo.setAfp(rs.getString(13));
                thisObjRepo.setDireccion(rs.getString(14));
                thisObjRepo.setComuna(rs.getString(15));
                thisObjRepo.setRecomendado(rs.getString(16));
                datosReport.add(thisObjRepo);
            }

            //Generar Reporte Excel
            ExcelCreateReport excelReport = new ExcelCreateReport("Reporte", path + tCons + ".xls");
            excelReport.createPersona(datosReport);

            //Generar vista HTML 
            mihtmlTabla.setBodyTable(datosReport);
            data = mihtmlTabla.getTableHtml();

        } catch (Exception e) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return data;
    }

    public String reporte_formacion(String fecha_ini, String fecha_fin, String tCons, String path) throws SQLException {

        //Conexion Base de Datos
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Conexion con = new Conexion();
        connection = con.conPostgreSQL;
        String data = "";

        try {

            //Variables Locales
            ReportFormacion mihtmlTabla = new ReportFormacion();
            List<ReportFormacion> datosReport = new ArrayList<>(); //Variable donde se guarda la consulta

            //Preparación del query
            stmt = connection.prepareStatement(mihtmlTabla.sql);
            stmt.setString(1, fecha_ini.trim());
            stmt.setString(2, fecha_fin.trim());
            //Ejecución del query
            rs = stmt.executeQuery();

            //Recorrer la consulta y generar el reporte
            while (rs.next()) {

                ReportFormacion thisObjRepo = new ReportFormacion();
                thisObjRepo.setId_formacion(rs.getString(1));
                thisObjRepo.setTipo_formacion(rs.getString(2));
                thisObjRepo.setFecha_formacion(rs.getString(3));
                thisObjRepo.setTemas(rs.getString(4));
                thisObjRepo.setNumeroAsistentes(rs.getString(5));
                datosReport.add(thisObjRepo);
            }

            //Generar Reporte Excel
            ExcelCreateReport excelReport = new ExcelCreateReport("Reporte", path + tCons + ".xls");
            excelReport.createFormacion(datosReport);

            //Generar vista HTML 
            mihtmlTabla.setBodyTable(datosReport);
            data = mihtmlTabla.getTableHtml();

        } catch (Exception e) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return data;
    }
    
     public String reporte_cita(String fecha_ini, String fecha_fin, String tCons, String path) throws SQLException {

        //Conexion Base de Datos
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Conexion con = new Conexion();
        connection = con.conPostgreSQL;
        String data = "";

        try {

            //Variables Locales
            ReportCitas mihtmlTabla = new ReportCitas();
            List<ReportCitas> datosReport = new ArrayList<>(); //Variable donde se guarda la consulta

            //Preparación del query
            stmt = connection.prepareStatement(mihtmlTabla.sql);
            stmt.setString(1, fecha_ini.trim());
            stmt.setString(2, fecha_fin.trim());
            //Ejecución del query
            rs = stmt.executeQuery();

            //Recorrer la consulta y generar el reporte
            while (rs.next()) {

                ReportCitas thisObjRepo = new ReportCitas();
                thisObjRepo.setCedula(rs.getInt(1));
                thisObjRepo.setPersona(rs.getString(2));
                thisObjRepo.setFecha(rs.getString(3));
                thisObjRepo.setEstado(rs.getString(4));
                datosReport.add(thisObjRepo);
            }

            //Generar Reporte Excel
            ExcelCreateReport excelReport = new ExcelCreateReport("Reporte", path + tCons + ".xls");
            excelReport.createCita(datosReport);

            //Generar vista HTML 
            mihtmlTabla.setBodyTable(datosReport);
            data = mihtmlTabla.getTableHtml();

        } catch (Exception e) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return data;
    }
}
