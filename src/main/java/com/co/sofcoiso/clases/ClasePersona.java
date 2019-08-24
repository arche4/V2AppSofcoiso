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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class ClasePersona {

    public boolean actualizarPersona(Integer cedula, String nomPerson, String ApellidoUnoPeson, String ApellidodosPeson, String generoPerson, 
            String EdadPerson, String FechaNacimientoPerson, String TelefonoPerson, String empresaPerson, String cargoPerson, 
            String ExperienciaPerson, String fechaClinicaPerson, String RecomendadoPerson) {
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
                Query.append(updateSqlPersona(cedula, nomPerson, ApellidoUnoPeson, ApellidodosPeson, generoPerson, EdadPerson, FechaNacimientoPerson, TelefonoPerson, empresaPerson, cargoPerson, ExperienciaPerson, 
                     fechaClinicaPerson, RecomendadoPerson));
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

    private String updateSqlPersona(Integer cedula, String nomPerson, String ApellidoUnoPeson, String ApellidodosPeson, String generoPerson, 
            String EdadPerson, String FechaNacimientoPerson, String TelefonoPerson, String empresaPerson, String cargoPerson, 
            String ExperienciaPerson, String fechaClinicaPerson, String RecomendadoPerson) {
        final StringBuilder retorno = new StringBuilder();
        final String add = "UPDATE persona "
                + "SET nombre = '"+nomPerson+"',apellido_uno='"+ApellidoUnoPeson+"', apellido_dos = '"+ApellidodosPeson+"', "
                + "genero = '"+generoPerson+"', fecha_nacimiento = '"+FechaNacimientoPerson+"', telefono='"+TelefonoPerson+"', "
                + "edad = '"+EdadPerson+"', empresa = '"+empresaPerson+"', antiguedad_empresa = '"+ExperienciaPerson+"', cargo = '"+cargoPerson+"', "
                + "fecha_clinica = '"+fechaClinicaPerson+"', "
                + "recomendado = '"+RecomendadoPerson+"' "
                + "WHERE cedula = "+cedula+" ";
        retorno.append(add);
        return retorno.toString();
    }

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
    
    public List<ReportPersona> datosPersona(Integer codigoCaso){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String data = "";
        List<ReportPersona> datostabla = new ArrayList<ReportPersona>();

        try {
            Conexion objConn = new Conexion();
            conn = objConn.conPostgreSQL;

            final String sqlCasoList = "select p.cedula, p.genero, p.edad, p.cargo, p.empresa, p.fecha_clinica, p.telefono "
                    + "from caso c "
                    + "inner join persona p on p.cedula = c.persona_cedula  "
                    + "where codigocaso = ? ";

            stmt = conn.prepareStatement(sqlCasoList);
            stmt.setInt(1, codigoCaso);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ReportPersona persona = new ReportPersona();
                persona.setCedula(rs.getInt(1));
                persona.setGenero(rs.getString(2));
                persona.setEdad(rs.getString(3));
                persona.setCargo(rs.getString(4));
                persona.setEmpresa(rs.getString(5));
                persona.setFechaClinica(rs.getString(6));
                persona.setTelefono(rs.getString(7));

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
