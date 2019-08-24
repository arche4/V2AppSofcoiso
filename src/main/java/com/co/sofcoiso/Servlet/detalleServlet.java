/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.clases.caso;
import com.co.sofcoiso.controller.CambioCasoJpaController;
import com.co.sofcoiso.controller.CasoJpaController;
import com.co.sofcoiso.controller.EstadoCasoJpaController;
import com.co.sofcoiso.controller.FlujocasoJpaController;
import com.co.sofcoiso.controller.PersonaJpaController;
import com.co.sofcoiso.controller.TipoCasoJpaController;
import com.co.sofcoiso.controller.UsuarioJpaController;
import com.co.sofcoiso.modelo.AccionesCaso;
import com.co.sofcoiso.modelo.CambioCaso;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.Flujocaso;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.TipoCaso;
import com.co.sofcoiso.modelo.Usuario;
import com.co.sofcoiso.util.JPAFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.servlet.http.Part;

/**
 *
 * @author manue
 */
@WebServlet(name = "detalleServlet", urlPatterns = {"/detalleServlet"})
public class detalleServlet extends HttpServlet {

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
        EstadoCasoJpaController jpaEstado = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        TipoCasoJpaController jpaTipo = new TipoCasoJpaController(JPAFactory.getFACTORY());
        FlujocasoJpaController jpaflujoCaso = new FlujocasoJpaController(JPAFactory.getFACTORY());
        UsuarioJpaController jpaUsuario = new UsuarioJpaController(JPAFactory.getFACTORY());
        CambioCasoJpaController seguimientoCaso = new CambioCasoJpaController(JPAFactory.getFACTORY());

        String cambiarEstado = request.getParameter("cambiarEstado");
        String usuario = request.getParameter("usuario");
        String Estado = request.getParameter("Estado");
        String casoCodigo = request.getParameter("codigoCaso");
        Part filePart = request.getPart("file");
        String comentarios = request.getParameter("comentarios");
        String hora_actual, estadoCaso, fecha_creacion, fecha;
        Date date;
        EstadoCaso estadocaso;
        TipoCaso tipo;
        if (cambiarEstado != null && !cambiarEstado.equals("")) {
            caso camEstdo = new caso();
            boolean respuesta;
            respuesta = camEstdo.actualizarEstado(Integer.parseInt(casoCodigo), Integer.parseInt(Estado));
            if (respuesta == true) {
                try {

                    caso tomarFecha = new caso();

                    String fechaActual = tomarFecha.obtenerFechaActual();
                    hora_actual = tomarFecha.obtenerHoraActual();

                    SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
                    SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
                    date = formatterFecha.parse(fechaActual);
                    fecha = formatterFecha2.format(date);
                    String fecha_actualizada;

                    //Actualizamos el flujo de los estados del caso
                    //Esto nos sirve para saber por cuantos estados paso el caso.
                    fecha_actualizada = fecha + " " + hora_actual;
                    CambioCaso casoSeguimiento = new CambioCaso(casoCodigo, Estado, usuario, fecha_actualizada);
                    try {
                        seguimientoCaso.create(casoSeguimiento);
                    } catch (Exception e) {
                        Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, e);
                    }

                    //Cambiamos el estado del caso
                    camEstdo.actualizarFlujoCaso(casoCodigo, Estado, usuario, fecha_actualizada);

                    //Agrgeamos los comentarios ya rchivos que suban.
                    AccionesCaso acciones;
                    InputStream inputStream = null;
                    //if (filePart.getSize() > 0) {
                    // inputStream = filePart.getInputStream();
                    //   }
                    //   if (inputStream != null) {

                    // acciones = new AccionesCaso(Integer.parseInt(casoCodigo), usuario, comentarios, inputStream.toString());
                    //  }
                } catch (Exception ex) {
                    Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            List<CambioCaso> listCambio;
            List<Caso> casoList;
            List<Persona> personasList;
            List<EstadoCaso> EstadoList;
            List<TipoCaso> TipoList;
            List<Usuario> ListUsuairio;
            List<Flujocaso> ListFlujo;
            personasList = jpaperson.findPersonaEntities();
            session.setAttribute("Persona", personasList);
            casoList = jpaCaso.findCasoEntities();
            session.setAttribute("casoList", casoList);
            EstadoList = jpaEstado.findEstadoCasoEntities();
            session.setAttribute("Estado", EstadoList);
            TipoList = jpaTipo.findTipoCasoEntities();
            session.setAttribute("Tipo", TipoList);
            ListUsuairio = jpaUsuario.findUsuarioEntities();
            session.setAttribute("Usuario", ListUsuairio);
            ListFlujo = jpaflujoCaso.findFlujocasoEntities();
            session.setAttribute("flujoList", ListFlujo);
            listCambio = seguimientoCaso.findCambioCasoEntities();
            session.setAttribute("cambioCasoList", listCambio);
            session.setAttribute("codigo", casoCodigo);
            rd = request.getRequestDispatcher("/view/detalleCaso2.jsp");
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
