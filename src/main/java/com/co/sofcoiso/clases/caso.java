package com.co.sofcoiso.clases;

import com.co.sofcoiso.Servlet.CasoServlet;
import com.co.sofcoiso.conexion.Conexion;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.report.ReportCasos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    
     public boolean actualizarUsuario(Integer casoCodigo, String Usuario) {
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
                Query.append(ActualizarUsuario(casoCodigo, Usuario));
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
     
     private String ActualizarUsuario(Integer codigo, String usuario) {
        final StringBuilder retorno = new StringBuilder();
        final String add = " UPDATE caso SET asignado = '" + usuario + "' WHERE codigocaso = '" + codigo + "'";
        retorno.append(add);
        return retorno.toString();
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

    public boolean actualizarFlujoCaso(String casoCodigo, String estado, String Usuario, String fecha) {
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

    public List<ReportCasos> listarCaso() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String data = "";
        List<ReportCasos> datostabla = new ArrayList<ReportCasos>();

        try {
            Conexion objConn = new Conexion();
            conn = objConn.conPostgreSQL;

            final String sqlCasoList = "select c.codigocaso, c.descripcion_caso,  "
                    + "c.fecha_inicio_afectacion,  c.pcl, c.parte_afectada, "
                    + "c.tiempo_incapacidad,  c.creado, c.persona_cedula, "
                    + "c.asignado,  tc.tipo_caso, tc.nombre_tipo_caso, ec.nombre_estado, "
                    + "p.nombre || ' ' || p.apellido_uno || ' ' || p.apellido_dos as nombre "
                    + "from caso c "
                    + "inner join  estado_caso ec on ec.codigoestado = c.estado_caso_codigoestado "
                    + "inner join  tipo_caso tc  on tc.codigo_tipo_caso = c.tipo_caso_codigo_tipo_caso "
                    + "inner join  persona p on p.cedula = c.persona_cedula ";

            stmt = conn.prepareStatement(sqlCasoList);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ReportCasos estados = new ReportCasos();
                estados.setCodigoCaso(rs.getString(1));
                estados.setDescripcion_caso(rs.getString(2));
                estados.setFecha_inicio_afectacion(rs.getString(3));
                estados.setPcl(rs.getString(4));
                estados.setParte_afectada(rs.getString(5));
                estados.setTiempo_incapacidad(rs.getString(6));
                estados.setCreado(rs.getString(7));
                estados.setPersona_cedula(rs.getString(8));
                estados.setAsignado(rs.getString(9));
                estados.setTipo_caso(rs.getString(10));
                estados.setNombre_tipo_caso(rs.getString(11));
                estados.setNombre_estado(rs.getString(12));
                estados.setNombrePersona(rs.getString(13));

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
    
        public boolean actualizaCasoAsociado(String ceudla) {
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
                Query.append(ActualizaCasoAsociado(ceudla));
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

    private String ActualizaCasoAsociado(String cedula) {
        final StringBuilder retorno = new StringBuilder();
        final String add = " UPDATE persona SET caso_asociado = 'Si' WHERE cedula = '" + cedula + "'";
        retorno.append(add);
        return retorno.toString();
    }
    
      public List<ReportCasos> listarCasoXPersona(Integer cedula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String data = "";
        List<ReportCasos> datostabla = new ArrayList<ReportCasos>();

        try {
            Conexion objConn = new Conexion();
            conn = objConn.conPostgreSQL;

            final String sqlCasoList = "select c.codigocaso, c.persona_cedula, tc.tipo_caso, tc.nombre_tipo_caso, ec.nombre_estado " 
                                       +  "from caso c "
                                       +  "inner join  estado_caso ec on ec.codigoestado = c.estado_caso_codigoestado "
                                       +  "inner join  tipo_caso tc  on tc.codigo_tipo_caso = c.tipo_caso_codigo_tipo_caso " 
                                       +  "where c.persona_cedula  = ? ";

            stmt = conn.prepareStatement(sqlCasoList);
            stmt.setInt(1, cedula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ReportCasos estados = new ReportCasos();
                estados.setCodigoCaso(rs.getString(1));
                estados.setPersona_cedula(rs.getString(2));
                estados.setTipo_caso(rs.getString(3));
                estados.setNombre_tipo_caso(rs.getString(4));
                estados.setNombre_estado(rs.getString(5));

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
