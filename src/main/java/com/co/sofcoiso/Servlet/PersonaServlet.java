/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.controller.PersonaJpaController;
import com.co.sofcoiso.modelo.Afp;
import com.co.sofcoiso.modelo.Arl;
import com.co.sofcoiso.modelo.Eps;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.util.JPAFactory;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;
/**
 *
 * @author manue
 */
@WebServlet(name = "PersonaServlet", urlPatterns = {"/PersonaServlet"})
public class PersonaServlet extends HttpServlet {

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
        PersonaJpaController jpaperson = new PersonaJpaController(JPAFactory.getFACTORY());
        String mensaje = "";
 
        
        
        String accion = request.getParameter("accion");
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellidoUno = request.getParameter("primerApellido");
        String apellidoDos = request.getParameter("segundoApellido");
        String genero = request.getParameter("genero");
        String fechaCumpleaños = request.getParameter("cumpleaños");
        String edad = request.getParameter("edad");
        String telefono = request.getParameter("telefono");
        String codigoEps = request.getParameter("eps");
        String codigoArl = request.getParameter("arl");
        String codigoAFP = request.getParameter("afp");
        String empresa = request.getParameter("empresa");
        String cargo = request.getParameter("cargo");
        String fechaClinica = request.getParameter("FechaClinica");
        String añosExperiencia = request.getParameter("anosExperiencia");
        String recomendado = request.getParameter("recomendado");
        String ver = request.getParameter("ver");
     
        Persona persona;
       
        Eps eps = new Eps(codigoEps);
        Arl arl = new Arl(codigoArl);
        Afp afp = new Afp(codigoAFP);
        List<Persona> listPersonas;

        if (accion != null && !accion.equals("")) {
            switch (accion) {
                case "crear":
                    //Crear persona
                    persona = new Persona(cedula, nombre, apellidoUno, apellidoDos, genero, fechaCumpleaños, edad,
                            empresa, eps, arl, afp, fechaClinica, añosExperiencia,recomendado, telefono, cargo);
                    try {
                        mensaje = jpaperson.crear(persona);
                    } catch (Exception e) {
                    }
                    Persona person = new Persona(Integer.parseInt(cedula));
                   


                    listPersonas = jpaperson.findPersonaEntities();
                    session.setAttribute("Persona", listPersonas);
                    rd = request.getRequestDispatcher("/view/registroPersonas.jsp");
                    break;
                case "listar":

                    listPersonas = jpaperson.findPersonaEntities();
                    session.setAttribute("Persona", listPersonas);
                    rd = request.getRequestDispatcher("/view/registroPersonas.jsp");
                    break;

                case "editar":
                     persona = new Persona(cedula, nombre, apellidoUno, apellidoDos, genero, fechaCumpleaños, edad,
                            empresa, eps, arl, afp, fechaClinica, añosExperiencia,recomendado, telefono, cargo);

                    try {
                        jpaperson.edit(persona);
                    } catch (Exception ex) {
                        Logger.getLogger(PersonaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    List<Persona> ListPersona = jpaperson.findPersonaEntities();
                    session.setAttribute("Persona", ListPersona);

                    rd = request.getRequestDispatcher("/view/registroPersonas.jsp");
                    break;
               
                case "volver":
                    rd = request.getRequestDispatcher("/view/listadoPersonas.jsp");
                    break;
                case "verCita":
                    rd = request.getRequestDispatcher("/view/listadoPersonas.jsp");
                    break;

            }
        } else if (ver != null && !ver.equals("")) {
            session.setAttribute("cedula", ver);
            rd = request.getRequestDispatcher("/view/verPersona.jsp");
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
