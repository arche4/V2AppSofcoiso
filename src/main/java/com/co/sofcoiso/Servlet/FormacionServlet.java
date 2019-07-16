/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.controller.FormacionJpaController;
import com.co.sofcoiso.modelo.Formacion;
import com.co.sofcoiso.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "FormacionServlet", urlPatterns = {"/FormacionServlet"})
public class FormacionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        FormacionJpaController formacionJPA = new FormacionJpaController(JPAFactory.getFACTORY());
        Formacion forma;

        String idFormacion = request.getParameter("idFormacion");
        String tipoFormacion = request.getParameter("tipoFormacion");
        String formacion = request.getParameter("formacion");
        String tema = request.getParameter("tema");
        String nAsistentes = request.getParameter("nAsistentes");

        String crear = request.getParameter("crear");
        String editar = request.getParameter("editar");
        String verFormacion = request.getParameter("verFormacion");
        String modificar = request.getParameter("editar");
        String volver = request.getParameter("volver");

        if (crear != null && !crear.equals("")) {
            forma = new Formacion(idFormacion, tipoFormacion, formacion, tema, nAsistentes);
            {
                try {
                    formacionJPA.create(forma);
                } catch (Exception ex) {
                    Logger.getLogger(FormacionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            List<Formacion> listFormacion = formacionJPA.findFormacionEntities();
            session.setAttribute("formacion", listFormacion);
            rd = request.getRequestDispatcher("/view/listadoFormaciones.jsp");
        }

         if (editar != null && !editar.equals("")) {
                    forma = new Formacion(idFormacion, tipoFormacion, formacion, tema, nAsistentes);
                     {
                        try {
                            formacionJPA.edit(forma);
                        } catch (Exception ex) {
                            Logger.getLogger(FormacionServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        List<Formacion> listF = formacionJPA.findFormacionEntities();
                        session.setAttribute("formacion", listF);
                        rd = request.getRequestDispatcher("/view/listadoFormaciones.jsp");
                    }
            }
        
        if (verFormacion != null && !verFormacion.equals("")) {
            session.setAttribute("formacionver", verFormacion);
            rd = request.getRequestDispatcher("/view/verFormaciones.jsp");
        }
        if (volver != null && !volver.equals("")) {
            rd = request.getRequestDispatcher("/view/listadoFormaciones.jsp");
        }

    rd.forward(request, response);
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
