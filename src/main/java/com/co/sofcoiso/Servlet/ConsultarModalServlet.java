package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.conexion.Conexion;
import com.co.sofcoiso.controller.CasoJpaController;
import com.co.sofcoiso.controller.CitaJpaController;
import com.co.sofcoiso.controller.EstadoCasoJpaController;
import com.co.sofcoiso.controller.FormacionJpaController;
import com.co.sofcoiso.controller.PersonaDirreccionJpaController;
import com.co.sofcoiso.controller.PersonaJpaController;
import com.co.sofcoiso.controller.TipoCasoJpaController;
import com.co.sofcoiso.controller.UsuarioJpaController;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.Cita;
import com.co.sofcoiso.modelo.ComunaMedellin;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.Formacion;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.PersonaDirreccion;
import com.co.sofcoiso.modelo.TipoCaso;
import com.co.sofcoiso.modelo.Usuario;
import com.co.sofcoiso.report.ReportCasos;
import com.co.sofcoiso.report.ReportPersona;
import com.co.sofcoiso.util.JPAFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String casoCrear = request.getParameter("casoCrear");

        if (cedulaModal != null && !cedulaModal.equals("")) {
            PrintWriter out = response.getWriter();
            JSONObject json = getModalPersona(cedulaModal);
            out.print(json);
        }

        if (cedulaUsuario != null && !cedulaUsuario.equals("")) {
            PrintWriter out = response.getWriter();
            JSONObject json = getModalUsuario(cedulaUsuario);
            out.print(json);
        }

        if (formacionConsulta != null && !formacionConsulta.equals("")) {
            PrintWriter out = response.getWriter();
            JSONObject json = getModalFormacion(formacionConsulta);
            out.print(json);
        }
        if (estadoCaso != null && !estadoCaso.equals("")) {
            PrintWriter out = response.getWriter();
            JSONObject json = getModalEstadoCaso(estadoCaso);
            out.print(json);
        }
        if (cita != null && !cita.equals("")) {
            PrintWriter out = response.getWriter();
            JSONObject json = getModalCita(cita);
            out.print(json);
        }
        if (casoEdit != null && !casoEdit.equals("")) {
            PrintWriter out = response.getWriter();
            JSONObject json = getModalEdit(casoEdit);
            out.print(json);
        }

        if (casoCrear != null && !casoCrear.equals("")) {
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            json.append("Cedula", casoCrear);
            out.print(json);
        }
    }

    public JSONObject getModalPersona(String cedula) {
        List<ReportPersona> listpersona = listarPersona(Integer.parseInt(cedula));
        JSONObject json = new JSONObject();
        for (ReportPersona person : listpersona) {
            ReportPersona persona = new ReportPersona();
            persona = person;

            json.append("Cedula", persona.getCedula());
            json.append("Nombre", persona.getNombre());
            json.append("ApellidoUno", persona.getApellidoUno());
            json.append("ApellidoDos", persona.getApellidoDos());
            json.append("Genero", persona.getGenero());
            json.append("Edad", persona.getEdad());
            json.append("FechaNacimiento", persona.getFechaNacimiento());
            json.append("Telefono", persona.getTelefono());
            json.append("empresa", persona.getEmpresa());
            json.append("cargo", persona.getCargo());
            json.append("Experiencia", persona.getAntiguedadEmpresa());
            json.append("fechaClinica", persona.getFechaClinica());
            json.append("Recomendado", persona.getRecomendado());
            json.append("Eps", persona.getEps());
            json.append("Arl", persona.getArl());
            json.append("Afp", persona.getAfp());
            json.append("Direccion", persona.getDireccion());
            json.append("Comuna", persona.getComuna());

        }
        return json;
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

    public JSONObject getModalCita(String cita) {
        CitaJpaController citaJpa = new CitaJpaController(JPAFactory.getFACTORY());
        Cita citas = citaJpa.findCita(Integer.parseInt(cita));
        JSONObject json = new JSONObject();
        json.append("getIdCita", citas.getIdCita());
        json.append("getCodigoCaso", citas.getCodigoCaso());
        json.append("getFechaCita", citas.getFechaCita());
        json.append("getEstado", citas.getEstado());

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

    public List<ReportPersona> listarPersona(Integer cedula) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String data = "";
        List<ReportPersona> datostabla = new ArrayList<ReportPersona>();

        try {
            Conexion objConn = new Conexion();
            conn = objConn.conPostgreSQL;

            final String sqlListPersona = "SELECT  P.cedula, p.nombre, p.apellido_uno, p.apellido_dos,  "
                    + "p.genero, p.fecha_nacimiento, p.edad, p.empresa,  p.antiguedad_empresa,  "
                    + "p.cargo, p.fecha_clinica, p.telefono, eps.nombre as Eps, afp.nombre as Afp, arl.nombre as Arl, "
                    + "pd.dirreccion, pd.comuna,  p.recomendado  "
                    + "FROM persona p INNER JOIN eps on p.codigoeps = eps.codigoeps "
                    + "INNER JOIN afp on afp.codigoafp = p.codigoafp   "
                    + "INNER JOIN arl  on arl.codigoarl = p.codigoarl "
                    + "INNER JOIN persona_dirreccion pd on p.cedula = pd.cedula_persona "
                    + "where p.cedula = ? ";

            stmt = conn.prepareStatement(sqlListPersona);
            stmt.setInt(1, cedula);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ReportPersona persona = new ReportPersona();
                persona.setCedula(Integer.parseInt(rs.getString(1)));
                persona.setNombre(rs.getString(2));
                persona.setApellidoUno(rs.getString(3));
                persona.setApellidoDos(rs.getString(4));
                persona.setGenero(rs.getString(5));
                persona.setFechaNacimiento(rs.getString(6));
                persona.setEdad(rs.getString(7));
                persona.setEmpresa(rs.getString(8));
                persona.setAntiguedadEmpresa(rs.getString(9));
                persona.setCargo(rs.getString(10));
                persona.setFechaClinica(rs.getString(11));
                persona.setTelefono(rs.getString(12));
                persona.setEps(rs.getString(13));
                persona.setAfp(rs.getString(14));
                persona.setArl(rs.getString(15));
                persona.setDireccion(rs.getString(16));
                persona.setComuna(rs.getString(17));
                persona.setRecomendado(rs.getString(18));

                datostabla.add(persona);

            }

        } catch (Exception e) {
            Logger.getLogger(ConsultarModalServlet.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            //Cerrando conexiones
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();

                }
            } catch (Exception e) {
                Logger.getLogger(ConsultarModalServlet.class.getName()).log(Level.SEVERE, null, e);

            }
        }

        return datostabla;

    }

}
