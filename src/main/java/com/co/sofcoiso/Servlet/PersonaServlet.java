/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.controller.PersonaDirreccionJpaController;
import com.co.sofcoiso.controller.PersonaJpaController;
import com.co.sofcoiso.modelo.Afp;
import com.co.sofcoiso.modelo.Arl;
import com.co.sofcoiso.modelo.Eps;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.PersonaDirreccion;
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
        String fechaCumpleaños = request.getParameter("cumpleanos");
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
        String comuna = request.getParameter("comuna");
        String direccion = request.getParameter("direccion");
        String ver = request.getParameter("ver");
        String cedulaPerson = request.getParameter("cedulaPerson");
        String empresaUsuaria = request.getParameter("empresaUsuaria");
        String sectorEconomico = request.getParameter("sectorEconomico");

        //Modificar persona 
        String btnModificar = request.getParameter("btnModificar");
        String cedulaPersona = request.getParameter("cedulaPerson");
        String nomPerson = request.getParameter("nomPerson");
        String ApellidoUnoPeson = request.getParameter("ApellidoUnoPeson");
        String ApellidodosPeson = request.getParameter("ApellidodosPeson");
        String generoPerson = request.getParameter("generoPerson");
        String EdadPerson = request.getParameter("EdadPerson");
        String FechaNacimientoPerson = request.getParameter("FechaNacimientoPerson");
        String TelefonoPerson = request.getParameter("TelefonoPerson");
        String comunaPerson = request.getParameter("comunaPerson");
        String direccionPerson = request.getParameter("direccionPerson");
        String empresaPerson = request.getParameter("empresaPerson");
        String cargoPerson = request.getParameter("cargoPerson");
        String ExperienciaPerson = request.getParameter("ExperienciaPerson");
        String fechaClinicaPerson = request.getParameter("fechaClinicaPerson");
        String RecomendadoPerson = request.getParameter("RecomendadoPerson");

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
                            empresa, añosExperiencia, cargo, fechaClinica, afp, arl, eps, telefono, empresaUsuaria,
                            sectorEconomico, recomendado, "No");
                    try {
                        mensaje = jpaperson.crear(persona);
                    } catch (Exception e) {
                    }
                    //Agregamos el contacto de la persona
                    Persona person = new Persona(Integer.parseInt(cedula));
                    PersonaDirreccion personDirreccion = new PersonaDirreccion(Integer.parseInt(cedula), direccion, comuna);
                    PersonaDirreccionJpaController jpaDir = new PersonaDirreccionJpaController(JPAFactory.getFACTORY());
                    try {
                        jpaDir.create(personDirreccion);
                    } catch (Exception e) {
                        Logger.getLogger(PersonaServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    listPersonas = jpaperson.findPersonaEntities();
                    session.setAttribute("Persona", listPersonas);
                    session.setAttribute("MensajePersona", mensaje);

                    rd = request.getRequestDispatcher("/view/registroPersonas.jsp");
                    break;
                case "listar":

                    listPersonas = jpaperson.findPersonaEntities();
                    session.setAttribute("Persona", listPersonas);
                    rd = request.getRequestDispatcher("/view/registroPersonas.jsp");
                    break;

                case "btnModificar":
                    persona = new Persona(cedula, nombre, apellidoUno, apellidoDos, genero, fechaCumpleaños, edad,
                            empresa, añosExperiencia, cargo, fechaClinica, afp, arl, eps, telefono, empresaUsuaria,
                            sectorEconomico, recomendado, "Si");

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

        if (btnModificar != null && !btnModificar.equals("")) {

            //Agregamos el contacto de la persona
            PersonaDirreccion personDirreccion = new PersonaDirreccion(Integer.parseInt(cedulaPersona), direccionPerson, comunaPerson);
            PersonaDirreccionJpaController jpaDir = new PersonaDirreccionJpaController(JPAFactory.getFACTORY());
            try {
                jpaDir.edit(personDirreccion);
            } catch (Exception e) {
                Logger.getLogger(PersonaServlet.class.getName()).log(Level.SEVERE, null, e);
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
