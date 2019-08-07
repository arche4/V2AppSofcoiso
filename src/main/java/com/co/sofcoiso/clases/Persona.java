/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.clases;

import com.co.sofcoiso.conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Persona {

    public boolean actualizarPersona(String nombre, String apellido_uno, String apellido_dos, String genero,
            String fecha_nacimiento, String edad, String empresa, String antiguedad_empresa,
            String cargo, String fecha_clinica, Integer cedula, String recomendado) {
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
                Query.append(updateSqlPersona(nombre, apellido_uno, apellido_dos, genero,
                        fecha_nacimiento, edad, empresa, antiguedad_empresa,
                        cargo, fecha_clinica, cedula, recomendado));
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

    private String updateSqlPersona(String nombre, String apellido_uno, String apellido_dos, String genero,
            String fecha_nacimiento, String edad, String empresa, String antiguedad_empresa,
            String cargo, String fecha_clinica, Integer cedula, String recomendado) {
        final StringBuilder retorno = new StringBuilder();
        final String add = "UPDATE persona\n"
                + "SET nombre = value2, apellido_uno= apellido_uno, apellido_dos = apellido_dos, genero = genero, fecha_nacimiento = fecha_nacimiento,\n"
                + "edad = edad, empresa = empresa, antiguedad_empresa = antiguedad_empresa, cargo = cargo, fecha_clinica = fecha_clinica,\n"
                + "nombre = nombre, recomendado = recomendado\n"
                + "WHERE cedula = '';";
        retorno.append(add);
        return retorno.toString();
    }

}
