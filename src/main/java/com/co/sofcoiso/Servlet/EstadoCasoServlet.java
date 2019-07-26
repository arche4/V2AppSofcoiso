/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.controller.EstadoCasoJpaController;
import com.co.sofcoiso.controller.FormacionJpaController;
import com.co.sofcoiso.modelo.EstadoCaso;
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
@WebServlet(name = "EstadoCasoServlet", urlPatterns = {"/EstadoCasoServlet"})
public class EstadoCasoServlet extends HttpServlet {

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
        EstadoCasoJpaController jpaEstado = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        EstadoCaso estadoCaso;

        String codigo = request.getParameter("codigo");
        String nombreEstado = request.getParameter("nombreEstado");
        String Descripcion = request.getParameter("Descripcion");

        String codigoForm = request.getParameter("codigoForm");
        String nombreEst = request.getParameter("nombreEst");
        String DescripcionEsta = request.getParameter("DescripcionEsta");

        String crear = request.getParameter("crear");
        String btnModificar = request.getParameter("btnModificar");
        String eliminar = request.getParameter("eliminar");

        if (crear != null && !crear.equals("")) {
            estadoCaso = new EstadoCaso(Integer.parseInt(codigo), nombreEstado, Descripcion);
            {
                try {
                    jpaEstado.create(estadoCaso);
                } catch (Exception ex) {
                    Logger.getLogger(FormacionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            List<EstadoCaso> ListEstado = jpaEstado.findEstadoCasoEntities();
            session.setAttribute("Estado", ListEstado);
            rd = request.getRequestDispatcher("/view/estadoCasos.jsp");
        }

        if (btnModificar != null && !btnModificar.equals("")) {
            estadoCaso = new EstadoCaso(Integer.parseInt(codigoForm), nombreEst, DescripcionEsta);
            {
                try {
                    jpaEstado.edit(estadoCaso);
                } catch (Exception ex) {
                    Logger.getLogger(FormacionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<EstadoCaso> ListEstado = jpaEstado.findEstadoCasoEntities();
                session.setAttribute("Estado", ListEstado);
                rd = request.getRequestDispatcher("/view/estadoCasos.jsp");
            }
        }
        if (eliminar != null && !eliminar.equals("")) {
            try {
                jpaEstado.destroy(Integer.parseInt(codigoForm));
            } catch (Exception ex) {
                Logger.getLogger(FormacionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<EstadoCaso> ListEstado = jpaEstado.findEstadoCasoEntities();
            session.setAttribute("Estado", ListEstado);
            rd = request.getRequestDispatcher("/view/estadoCasos.jsp");
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
