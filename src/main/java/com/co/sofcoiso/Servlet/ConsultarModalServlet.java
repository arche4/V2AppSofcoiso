package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.controller.CasoJpaController;
import com.co.sofcoiso.controller.EstadoCasoJpaController;
import com.co.sofcoiso.controller.FormacionJpaController;
import com.co.sofcoiso.controller.PersonaDirreccionJpaController;
import com.co.sofcoiso.controller.PersonaJpaController;
import com.co.sofcoiso.controller.UsuarioJpaController;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.ComunaMedellin;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.Formacion;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.PersonaDirreccion;
import com.co.sofcoiso.modelo.Usuario;
import com.co.sofcoiso.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author manue
 */
@WebServlet(name = "ConsultarModalServlet", urlPatterns = {"/ConsultarModalServlet"})
public class ConsultarModalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        String cedulaModal = request.getParameter("selectConsulta");
        String cedulaUsuario = request.getParameter("usuarioConsulta");
        String formacionConsulta = request.getParameter("formacionConsulta");
        String estadoCaso = request.getParameter("estadoCasoConsulta");
        String cita = request.getParameter("citaConsulta");
        String casoEdit = request.getParameter("btnEdtar");
         
        if (cedulaModal != null && !cedulaModal.equals("")) {
            PrintWriter out = response.getWriter();
            
            PersonaJpaController jpaPersona = new PersonaJpaController(JPAFactory.getFACTORY());
            Persona persona = jpaPersona.findPersona(Integer.parseInt(cedulaModal));
      
            JSONObject json = new JSONObject();
            json.append("Cedula", persona.getCedula());
            json.append("Nombre", persona.getNombre());
            json.append("ApellidoUno", persona.getApellidoUno());
            json.append("ApellidoDos", persona.getApellidoDos());
            json.append("Genero", persona.getGenero());
            json.append("Edad", persona.getTelefono());
            json.append("FechaNacimiento", persona.getFechaNacimiento());
            json.append("Telefono", persona.getTelefono());
            json.append("empresa", persona.getEmpresa());
            json.append("cargo", persona.getCargo());
            json.append("Experiencia", persona.getAntiguedadEmpresa());
            json.append("fechaClinica", persona.getFechaClinica());
            json.append("Recomendado", persona.getRecomendado());

            out.print(json);
        }

        if (cedulaUsuario != null && !cedulaUsuario.equals("")) {
            PrintWriter out = response.getWriter();
            JSONObject json = getModalUsuario(cedulaUsuario);
            out.print(json);
        }
        
        if(formacionConsulta != null && !formacionConsulta.equals("")){
            PrintWriter out = response.getWriter();
            JSONObject json = getModalFormacion(formacionConsulta);
            out.print(json);
        }
        if(estadoCaso != null && !estadoCaso.equals("")){
            PrintWriter out = response.getWriter();
            JSONObject json = getModalEstadoCaso(estadoCaso);
            out.print(json);
        }
        if(cita != null && !cita.equals("")){
            PrintWriter out = response.getWriter();
            JSONObject json = getModalCita(cita);
            out.print(json);
        }
        if(casoEdit != null && !casoEdit.equals("")){
            PrintWriter out = response.getWriter();
            JSONObject json = getModalEdit(casoEdit);
            out.print(json);
        }
    }

    public JSONObject getModalUsuario(String cedula) {
        UsuarioJpaController userJPA = new UsuarioJpaController(JPAFactory.getFACTORY());
        Usuario user = userJPA.findUsuario(Integer.parseInt(cedula));
        JSONObject json = new JSONObject();
        json.append("Cedula", user.getCedulaUsuario());
        json.append("Nombre", user.getNombreUsuario());
        json.append("apellido", user.getApellidoUsuario());
        json.append("rol", user.getRol());
        json.append("clave", user.getClave());

        return json;

    }
    
    public JSONObject getModalFormacion(String codigoFormacion) {
        FormacionJpaController formacionJPA = new FormacionJpaController(JPAFactory.getFACTORY());
        Formacion formacion = formacionJPA.findFormacion(codigoFormacion);
        JSONObject json = new JSONObject();
        json.append("codigo", formacion.getIdFormacion());
        json.append("Tipo", formacion.getTipoFormacion());
        json.append("fehcaFormacion", formacion.getFechaFormacion());
        json.append("tema", formacion.getTemas());
        json.append("cantidadAsistentes", formacion.getNumeroAsistentes());

        return json;

    }
    
    public JSONObject getModalEstadoCaso(String estado) {
        EstadoCasoJpaController japEstadoCaso = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        EstadoCaso estadoCaso = japEstadoCaso.findEstadoCaso(Integer.parseInt(estado));
        JSONObject json = new JSONObject();
        json.append("codigo", estadoCaso.getCodigoestado());
        json.append("nombre", estadoCaso.getNombreEstado());
        json.append("Descripcion", estadoCaso.getDescripcionestado());

        return json;

    }
    
    public JSONObject getModalCita(String codigoFormacion) {
        FormacionJpaController formacionJPA = new FormacionJpaController(JPAFactory.getFACTORY());
        Formacion formacion = formacionJPA.findFormacion(codigoFormacion);
        JSONObject json = new JSONObject();
        json.append("codigo", formacion.getIdFormacion());
        json.append("Tipo", formacion.getTipoFormacion());
        json.append("fehcaFormacion", formacion.getFechaFormacion());
        json.append("tema", formacion.getTemas());
        json.append("cantidadAsistentes", formacion.getNumeroAsistentes());

        return json;

    }
    
     public JSONObject getModalEdit(String casoCodigo) {
        CasoJpaController jpaCaso = new CasoJpaController(JPAFactory.getFACTORY());
        Caso CasoUnico = jpaCaso.findCaso(Integer.parseInt(casoCodigo));
        EstadoCasoJpaController jpaEstado = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        List<EstadoCaso> listEstado = jpaEstado.findEstadoCasoEntities();
        JSONObject json = new JSONObject();
        json.append("codigo", CasoUnico.getCodigocaso());
        json.append("DescripcionCaso", CasoUnico.getDescripcionCaso());
        json.append("fechaAfectacion", CasoUnico.getFechaInicioAfectacion());
        json.append("pcl", CasoUnico.getPcl());
        json.append("parteAfectada", CasoUnico.getParteAfectada());
        json.append("tiempoIncapacidad", CasoUnico.getTiempoIncapacidad());
        json.append("creado", CasoUnico.getCreado());
        json.append("personaCedula", CasoUnico.getPersonaCedula());
        json.append("asignado", CasoUnico.getAsignado());
        
       
        return json;

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
