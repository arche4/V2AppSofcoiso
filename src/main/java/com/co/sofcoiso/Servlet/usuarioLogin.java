/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.clases.Dashboard;
import com.co.sofcoiso.controller.AfpJpaController;
import com.co.sofcoiso.controller.ArlJpaController;
import com.co.sofcoiso.controller.CasoJpaController;
import com.co.sofcoiso.controller.CitasPersonaJpaController;
import com.co.sofcoiso.controller.EpsJpaController;
import com.co.sofcoiso.controller.EstadoCasoJpaController;
import com.co.sofcoiso.controller.FlujocasoJpaController;
import com.co.sofcoiso.controller.FormacionJpaController;
import com.co.sofcoiso.controller.PersonaJpaController;
import com.co.sofcoiso.controller.PersonaasistenteJpaController;
import com.co.sofcoiso.controller.TipoCasoJpaController;
import com.co.sofcoiso.modelo.Usuario;
import com.co.sofcoiso.util.JPAFactory;
import com.co.sofcoiso.controller.UsuarioJpaController;
import com.co.sofcoiso.modelo.Afp;
import com.co.sofcoiso.modelo.Arl;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.CitasPersona;
import com.co.sofcoiso.modelo.Eps;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.Flujocaso;
import com.co.sofcoiso.modelo.Formacion;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.TipoCaso;
import com.co.sofcoiso.report.ReportCasos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
public class usuarioLogin extends HttpServlet {

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
        String cedula = request.getParameter("txtid");
        String clave = request.getParameter("txtclave");

        UsuarioJpaController ujc = new UsuarioJpaController(JPAFactory.getFACTORY());
        EpsJpaController ejc = new EpsJpaController(JPAFactory.getFACTORY());
        ArlJpaController arl = new ArlJpaController(JPAFactory.getFACTORY());
        AfpJpaController afp = new AfpJpaController(JPAFactory.getFACTORY());
        PersonaJpaController per = new PersonaJpaController(JPAFactory.getFACTORY());
        CasoJpaController caso = new CasoJpaController(JPAFactory.getFACTORY());
        EstadoCasoJpaController estado = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        TipoCasoJpaController tipo = new TipoCasoJpaController(JPAFactory.getFACTORY());
        FlujocasoJpaController jpaflujoCaso = new FlujocasoJpaController(JPAFactory.getFACTORY());
        UsuarioJpaController jpaUsuario = new UsuarioJpaController(JPAFactory.getFACTORY());
        CitasPersonaJpaController citaJpa = new CitasPersonaJpaController(JPAFactory.getFACTORY());
        FormacionJpaController formacionJpa = new FormacionJpaController(JPAFactory.getFACTORY());

        
        
        Usuario usuario = ujc.findUsuarioClave(cedula, clave);
        String Mensaje = "";
        if (usuario == null) {
            Mensaje = "Email o Clave no validos";
            session.setAttribute("MENSAJE", Mensaje);
            rd = request.getRequestDispatcher("index.jsp");

        } else {
            rd = request.getRequestDispatcher("view/menu.jsp");
            //Mensaje = "Email o Clave no validos";
        }

        Dashboard dashboard = new Dashboard();
        List<ReportCasos> listEstados = dashboard.countEstado();
        session.setAttribute("listEstados", listEstados);
        session.setAttribute("USUARIO", usuario);
        List<Usuario> listUsuario = jpaUsuario.findUsuarioEntities();
        session.setAttribute("listUsuario", listUsuario);
        List<Eps> listEps = ejc.findEpsEntities();
        session.setAttribute("EPS", listEps);
        List<Arl> ListArl = arl.findArlEntities();
        session.setAttribute("ARL", ListArl);
        List<Afp> ListAfp = afp.findAfpEntities();
        session.setAttribute("AFP", ListAfp);
        List<Persona> ListPersona = per.findPersonaEntities();
        int countPersona = per.getPersonaCount();
        session.setAttribute("Persona", ListPersona);
        session.setAttribute("countPersona", countPersona);
        List<Caso> listCaso = caso.findCasoEntities();
        session.setAttribute("Caso", listCaso);
        int countCaso = caso.getCasoCount();
        session.setAttribute("countCaso", countCaso);
        int countCitas = citaJpa.getCitasPersonaCount();
        session.setAttribute("countCitas", countCitas);
        int countFormaciones = formacionJpa.getFormacionCount();
        session.setAttribute("countFormaciones", countFormaciones);
        List<EstadoCaso> ListEstado = estado.findEstadoCasoEntities();
        session.setAttribute("Estado", ListEstado);
        List<TipoCaso> ListTipo = tipo.findTipoCasoEntities();
        session.setAttribute("Tipo", ListTipo);
        List<Flujocaso> flujoList;
        flujoList = jpaflujoCaso.findFlujocasoEntities();
        session.setAttribute("flujoList", flujoList);
        List<Formacion> formacionlist;
        formacionlist = formacionJpa.findFormacionEntities();
        session.setAttribute("formacion", formacionlist);
        List<CitasPersona> citasList;
        citasList = citaJpa.findCitasPersonaEntities();
        session.setAttribute("Cita", citasList);
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
