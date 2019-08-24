/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manue
 */
public class Conexion {

    final private String user = "postgres";
    final private String pass = "root";
    final private String host = "jdbc:postgresql://localhost:5432/SofCoiso";
    public Connection conPostgreSQL = null;

    public Conexion() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conPostgreSQL = DriverManager.getConnection(host, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
