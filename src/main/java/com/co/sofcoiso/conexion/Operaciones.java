/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.conexion;

import com.co.sofcoiso.ExcelReport.ExcelCreateReport;
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

    public String getConsultas(String Reporte, String path) throws SQLException {
        String data = "";

        if (Reporte.equals("Personas")) {
            data = reporte_personas(path);
        }
        return data;
    }

    public String reporte_personas(String path) throws SQLException {

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
                datosReport.add(thisObjRepo);
            }

            //Generar Reporte Excel
            ExcelCreateReport excelReport = new ExcelCreateReport("Reporte", path + "Personas" + ".xls");
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
}
