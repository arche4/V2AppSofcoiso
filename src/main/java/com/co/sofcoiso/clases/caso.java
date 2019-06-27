package com.co.sofcoiso.clases;

import com.co.sofcoiso.Servlet.CasoServlet;
import com.co.sofcoiso.conexion.Conexion;
import com.co.sofcoiso.modelo.EstadoCaso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author manue
 */
public class caso {
    private Connection con;

    public String obtenerFechaActual() {
        final Calendar capturar_fecha = Calendar.getInstance();
        return Integer.toString(capturar_fecha.get(Calendar.YEAR)) + agregarCerosIzquierda(Integer.toString((capturar_fecha.get(Calendar.MONTH)) + 1)) + agregarCerosIzquierda(Integer.toString(capturar_fecha.get(Calendar.DATE)));
    }

    private String agregarCerosIzquierda(final String diames) {
        final StringBuffer retorno = new StringBuffer();
        if (Integer.parseInt(diames) < 10) {
            final String add = "0" + diames;
            retorno.append(add);
        } else {
            retorno.append(diames);
        }
        return retorno.toString();
    }

    public String obtenerHoraActual() {
        final Time sqlTime = new Time(new java.util.Date().getTime());
        return sqlTime.toString();
    }

    public boolean actualizarEstado(Integer casoCodigo, Integer estado) {
        boolean resp;
        final StringBuilder respondo = new StringBuilder();
        final StringBuilder Query = new StringBuilder();
        Statement stmt = null;
        ResultSet Respuesta = null;
        Connection conn = null;
        ResultSet rs = null;
        

        try {

            respondo.delete(0, respondo.length());
            stmt = con.createStatement();
            Query.delete(0, Query.length());
            Conexion objConn = new Conexion();
            conn = objConn.conPostgreSQL;

            //STRINGS DE LOS QUERYS
            Query.append(ActualizarEstado(casoCodigo,estado));
            Respuesta = stmt.executeQuery(Query.toString());
            

            if (Respuesta.next()) {
                resp = true;
            } else {
                resp = false;
            }

        } catch (Exception e) {
            resp = false;
            Logger.getLogger(caso.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            //CERRAR LAS CONEXIONES
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
            }
        }

        return resp;
    }
    
     private String ActualizarEstado(Integer codigo, Integer codigoEstado) {
        final StringBuilder retorno = new StringBuilder();
        final String add = "UPDATE caso SET estado_caso_codigoestado = '" + codigo + "' WHERE codigocaso = '" + codigoEstado + "'";
        retorno.append(add);
        return retorno.toString();
    }

}
