/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.controller.EstadoCasoJpaController;
import com.co.sofcoiso.controller.TipoCasoJpaController;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.TipoCaso;
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
@WebServlet(name = "tipoCasoServlet", urlPatterns = {"/tipoCasoServlet"})
public class tipoCasoServlet extends HttpServlet {

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
        TipoCasoJpaController jpaTipoCaso = new TipoCasoJpaController(JPAFactory.getFACTORY());
        TipoCaso tipocaso;
        
        String codigo = request.getParameter("codigo");
        String tipo = request.getParameter("tipo");
        String nombreTipo = request.getParameter("nombreTipo");
        String Descripcion = request.getParameter("Descripcion");
        String crear = request.getParameter("crear");
        
         if (crear != null && !crear.equals("")) {
             tipocaso = new TipoCaso(codigo, tipo, nombreTipo, Descripcion);
            {
                try {
                    jpaTipoCaso.create(tipocaso);
                } catch (Exception ex) {
                    Logger.getLogger(EstadoCasoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             List<TipoCaso> listTipo = jpaTipoCaso.findTipoCasoEntities();
            session.setAttribute("Tipo", listTipo);
            rd = request.getRequestDispatcher("/view/tiposCasos.jsp");
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
