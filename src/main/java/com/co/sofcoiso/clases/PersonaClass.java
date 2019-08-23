/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.clases;
import com.co.sofcoiso.conexion.Conexion;
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
public class PersonaClass {
    
    
    public List<ReportPersona> listarPersona() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String data = "";
        List<ReportPersona> datostabla = new ArrayList<ReportPersona>();

        try {
            Conexion objConn = new Conexion();
            conn = objConn.conPostgreSQL;

            final String sqlCasoList = "select cedula, nombre || ' ' || apellido_uno || ' ' || apellido_dos as nombre, "
                    + "fecha_clinica, caso_asociado from persona  ";

            stmt = conn.prepareStatement(sqlCasoList);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ReportPersona persona = new ReportPersona();
                persona.setCedula(rs.getInt(1));
                persona.setNombre(rs.getString(2));
                persona.setFechaClinica(rs.getString(3));
                persona.setCasosociado(rs.getString(4));

                datostabla.add(persona);
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
