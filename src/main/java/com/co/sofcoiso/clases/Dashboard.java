/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.clases;

import com.co.sofcoiso.conexion.Conexion;
import com.co.sofcoiso.report.ReportCasos;
import com.co.sofcoiso.report.ReportCitas;
import com.co.sofcoiso.report.ReportPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Dashboard {

    public Dashboard() {

    }

    public List<ReportCasos> countEstado() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String data = "";
        List<ReportCasos> datostabla = new ArrayList<>();

        try {
            Conexion objConn = new Conexion();
            conn = objConn.conPostgreSQL;

            final String sqlPersonaCount = "select ec.nombre_estado as estado, "
                    + "COUNT(c.codigocaso) as cantidad "
                    + "from estado_caso ec "
                    + "inner join caso c "
                    + "on ec.codigoestado = c.estado_caso_codigoestado "
                    + "GROUP BY ec.nombre_estado";

            stmt = conn.prepareStatement(sqlPersonaCount);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ReportCasos estados = new ReportCasos();
                estados.setNombreEstado(rs.getString(1));
                estados.setCantidadEstado(rs.getString(2));
                datostabla.add(estados);
            }

        } catch (Exception e) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            //Cerrando conexiones
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, e);

            }
        }

        return datostabla;

    }

    public List<ReportCitas> citasDeldia() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String data = "";
        List<ReportCitas> datostabla = new ArrayList<>();

        caso tomarFecha = new caso();
        String fechaActual = tomarFecha.obtenerFechaActual();
        SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = formatterFecha.parse(fechaActual);
            String fecha = formatterFecha2.format(date);
            
            Conexion objConn = new Conexion();
            conn = objConn.conPostgreSQL;

            final String sqlCitas = "select * from cita where fecha_cita = ? ";

            stmt = conn.prepareStatement(sqlCitas);
            stmt.setString(1, fecha);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ReportCitas cita = new ReportCitas();
                cita.setIdCita(rs.getString(1));
                cita.setPersona(rs.getString(2));
                cita.setFecha(rs.getString(3));
                cita.setEstado(rs.getString(4));
                datostabla.add(cita);
            }

        } catch (Exception e) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            //Cerrando conexiones
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, e);

            }
        }

        return datostabla;

    }

}
