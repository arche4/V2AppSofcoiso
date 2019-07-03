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
        boolean resp = true;
        final StringBuilder respondo = new StringBuilder();
        final StringBuilder Query = new StringBuilder();
        Statement stmt = null;
        ResultSet Respuesta = null;
        Connection con = null;
        ResultSet rs = null;

        try {

            respondo.delete(0, respondo.length());
            Query.delete(0, Query.length());
            Conexion objConn = new Conexion();
            con = objConn.conPostgreSQL;
            stmt = con.createStatement();

            //STRINGS DE LOS QUERYSTRY
            try {
                Query.append(ActualizarEstado(casoCodigo, estado));
                stmt.executeUpdate(Query.toString());
            } catch (Exception e) {
                resp = false;
            Logger.getLogger(caso.class.getName()).log(Level.SEVERE, null, e);
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
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

        return resp;
    }

    private String ActualizarEstado(Integer codigo, Integer codigoEstado) {
        final StringBuilder retorno = new StringBuilder();
        final String add = " UPDATE caso SET estado_caso_codigoestado = '" + codigoEstado + "' WHERE codigocaso = '" + codigo + "'";
        retorno.append(add);
        return retorno.toString();
    }
    
    public boolean actualizarFlujoCaso(String casoCodigo, String estado,String Usuario, String fecha) {
        boolean resp = true;
        final StringBuilder respondo = new StringBuilder();
        final StringBuilder Query = new StringBuilder();
        Statement stmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {

            respondo.delete(0, respondo.length());
            Query.delete(0, Query.length());
            Conexion objConn = new Conexion();
            con = objConn.conPostgreSQL;
            stmt = con.createStatement();

            //STRINGS DE LOS QUERYSTRY
            try {
                Query.append(ActualizarFlujoCaso(casoCodigo, estado, Usuario, fecha));
                stmt.executeUpdate(Query.toString());
            } catch (Exception e) {
                resp = false;
            Logger.getLogger(caso.class.getName()).log(Level.SEVERE, null, e);
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
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

        return resp;
    }

    private String ActualizarFlujoCaso(String codigo, String codigoEstado, String Usuario, String fecha) {
        final StringBuilder retorno = new StringBuilder();
        final String add = " UPDATE flujocaso SET estadocaso = '" + codigoEstado + "',usuario = '" + Usuario + "', fecha_actualizacion = '" + fecha + "' WHERE codigocaso = '" + codigo + "'";
        retorno.append(add);
        return retorno.toString();
    }

}
