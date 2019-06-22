/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.controller.CasoAccionesJpaController;
import com.co.sofcoiso.controller.CasoJpaController;
import com.co.sofcoiso.controller.PersonaJpaController;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.Caso_;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.TipoCaso;
import com.co.sofcoiso.modelo.Usuario;
import com.co.sofcoiso.modelo.Usuario_;
import com.co.sofcoiso.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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
@WebServlet(name = "CasoServlet", urlPatterns = {"/CasoServlet"})
public class CasoServlet extends HttpServlet {

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
        CasoJpaController jpaCaso = new CasoJpaController(JPAFactory.getFACTORY());
        PersonaJpaController jpaperson = new PersonaJpaController(JPAFactory.getFACTORY());
        String mensaje = "";

        String accion = request.getParameter("accion");
        String persona = request.getParameter("persona");
        String Tipo = request.getParameter("Tipo");
        String fechaAfectacion = request.getParameter("fechaAfectacion");
        String pcl = request.getParameter("pcl");
        String textarea = request.getParameter("textarea");
        String creado = request.getParameter("creado");
        String tiempoInca = request.getParameter("tiempoInca");
        Caso caso;
        EstadoCaso estadocaso;
        TipoCaso tipo;
        List<Usuario> usuarioList;
        if (accion != null && !accion.equals("")) {
            switch (accion) {
                case "crear":
                    //Creamos codigo del caso.
                    Integer codigoCaso;
                    Random numAleatorio = new Random();
                    int valorDado = numAleatorio.nextInt(1000) + 1;
                    codigoCaso = valorDado;
                    Persona per = new Persona(Integer.parseInt(persona));

                    //Por ser primera vez el Estado del caso sera Emitida
                    estadocaso = new EstadoCaso(101);
                    tipo = new TipoCaso(Tipo);
                    Caso crearCaso = new Caso(codigoCaso, textarea, fechaAfectacion, pcl, "", tiempoInca, creado, per, creado, estadocaso, tipo);
                    try {
                        mensaje = jpaCaso.crear(crearCaso);
                    } catch (Exception e) {
                    }
                    List<Persona> listPersonas;
                    listPersonas = jpaperson.findPersonaEntities();
                    session.setAttribute("Persona", listPersonas);
                    List<Caso> listCaso;
                    listCaso = jpaCaso.findCasoEntities();
                    session.setAttribute("Caso", listCaso);
                    rd = request.getRequestDispatcher("/view/registroPersonas.jsp");
                    break;

            }
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
