/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.conexion.Operaciones;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manue
 */
@WebServlet(name = "ReportServlet", urlPatterns = {"/ReportServlet"})
public class ReportServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);     //habilitar el uso de sesiones
            ServletContext servletContext = getServletContext();
            String contextPath = servletContext.getRealPath(File.separator);
            String respuesta = null;
            PrintWriter out = response.getWriter();
            Operaciones ops = new Operaciones();
            String tCons = request.getParameter("tCons");
            String fecha_i = request.getParameter("fecha_i");
            String fecha_f = request.getParameter("fecha_f");
            
            
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date_i = dt.parse(fecha_i);
            Date date_f = dt.parse(fecha_f);
            
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            String fecha_ini = dt1.format(date_i);
            String fecha_fin =  dt1.format(date_f);
            
            
            try {
                respuesta = ops.getConsultas(tCons, fecha_ini, fecha_fin, contextPath);
            } catch (SQLException ex) {
                Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println(respuesta);
        } catch (ParseException ex) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
