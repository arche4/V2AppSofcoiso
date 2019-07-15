/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.clases;

import com.co.sofcoiso.conexion.Conexion;
import com.co.sofcoiso.report.ReportCasos;
import com.co.sofcoiso.report.ReportPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

}
