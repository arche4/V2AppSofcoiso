/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.clases.caso;
import com.co.sofcoiso.controller.AccionesCasoJpaController;
import com.co.sofcoiso.controller.CitaJpaController;
import com.co.sofcoiso.controller.FormacionJpaController;
import com.co.sofcoiso.modelo.AccionesCaso;
import com.co.sofcoiso.modelo.Cita;
import com.co.sofcoiso.modelo.Formacion;
import com.co.sofcoiso.report.ReportCasos;
import com.co.sofcoiso.util.JPAFactory;
import java.io.IOException;
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

/**
 *
 * @author manue
 */
@WebServlet(name = "CitasServlet", urlPatterns = {"/CitasServlet"})
public class CitasServlet extends HttpServlet {

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
        CitaJpaController citaJpa = new CitaJpaController(JPAFactory.getFACTORY());
        AccionesCasoJpaController accionesCasoJpa = new AccionesCasoJpaController(JPAFactory.getFACTORY());
        
        String codigoCita = request.getParameter("codigoCita");
        String usuarioCita = request.getParameter("usuarioCita");
        String Fechacita = request.getParameter("cita");
        String comentarioCita = request.getParameter("comentarioCita");
        String btnCitar = request.getParameter("btnCitar");
        
         if (btnCitar != null && !btnCitar.equals("")) {
             Cita cita = new Cita(codigoCita,Fechacita,"Abierta");
            {
                try {
                    citaJpa.create(cita);
                } catch (Exception ex) {
                    Logger.getLogger(FormacionServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Agregamos los comentarios
              try {
                  
                   caso tomarFecha = new caso();

                    String fechaActual = tomarFecha.obtenerFechaActual();
                    String hora_actual = tomarFecha.obtenerHoraActual();

                    SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
                    SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = formatterFecha.parse(fechaActual);
                    String fecha = formatterFecha2.format(date);
                    String fecha_actualizada;

                    //Actualizamos el flujo de los estados del caso
                    //Esto nos sirve para saber por cuantos estados paso el caso.
                    fecha_actualizada = fecha + " " + hora_actual;

                    AccionesCaso accionComentar = new AccionesCaso(Integer.parseInt(codigoCita), usuarioCita, comentarioCita, "", fecha_actualizada);

                        accionesCasoJpa.create(accionComentar);
                    } catch (Exception e) {
                        Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
            List<Cita> listCita = citaJpa.findCitaEntities();
            session.setAttribute("listCita", listCita);
            caso CasosList = new caso();
            List<ReportCasos> listCaso = CasosList.listarCaso();
            session.setAttribute("ListCaso", listCaso);
            List<AccionesCaso> listAccionesCaso = accionesCasoJpa.findAccionesCasoEntities();
            session.setAttribute("listAccionesCaso", listAccionesCaso);
            rd = request.getRequestDispatcher("/view/detalleCaso2.jsp");
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
