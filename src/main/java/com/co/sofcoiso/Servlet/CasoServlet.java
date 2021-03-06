/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.clases.ClasePersona;
import com.co.sofcoiso.clases.Dashboard;
import com.co.sofcoiso.clases.PersonaClass;
import com.co.sofcoiso.controller.FlujocasoJpaController;
import com.co.sofcoiso.clases.caso;
import com.co.sofcoiso.controller.AccionesCasoJpaController;
import com.co.sofcoiso.controller.CambioCasoJpaController;
import com.co.sofcoiso.controller.CasoJpaController;
import com.co.sofcoiso.controller.CitaJpaController;
import com.co.sofcoiso.controller.EstadoCasoJpaController;
import com.co.sofcoiso.controller.PersonaJpaController;
import com.co.sofcoiso.controller.TipoCasoJpaController;
import com.co.sofcoiso.controller.UsuarioJpaController;
import com.co.sofcoiso.modelo.AccionesCaso;
import com.co.sofcoiso.modelo.CambioCaso;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.Cita;
import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.Flujocaso;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.TipoCaso;
import com.co.sofcoiso.modelo.Usuario;
import com.co.sofcoiso.report.ReportCasos;
import com.co.sofcoiso.report.ReportCitas;
import com.co.sofcoiso.report.ReportPersona;
import com.co.sofcoiso.util.JPAFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
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
        EstadoCasoJpaController jpaEstado = new EstadoCasoJpaController(JPAFactory.getFACTORY());
        TipoCasoJpaController jpaTipo = new TipoCasoJpaController(JPAFactory.getFACTORY());
        FlujocasoJpaController jpaflujoCaso = new FlujocasoJpaController(JPAFactory.getFACTORY());
        UsuarioJpaController jpaUsuario = new UsuarioJpaController(JPAFactory.getFACTORY());
        CambioCasoJpaController seguimientoCaso = new CambioCasoJpaController(JPAFactory.getFACTORY());
        AccionesCasoJpaController accionesCasoJpa = new AccionesCasoJpaController(JPAFactory.getFACTORY());
        CitaJpaController citaJpa = new CitaJpaController(JPAFactory.getFACTORY());
        ClasePersona clasePerson = new ClasePersona();

        String mensaje = "";

        String accion = request.getParameter("accion");
        String persona = request.getParameter("persona");
        String Tipo = request.getParameter("Tipo");
        String fechaAfectacion = request.getParameter("fechaAfectacion");
        String pcl = request.getParameter("pcl");
        String textarea = request.getParameter("textarea");
        String creado = request.getParameter("creado");
        String tiempoInca = request.getParameter("tiempoInca");
        String editar = request.getParameter("editar");
        String hora_actual, estadoCaso, fecha_creacion, fecha;
        String cambiarEstado = request.getParameter("cambiarEstado");
        String usuario = request.getParameter("usuario");
        String Estado = request.getParameter("Estado");
        String casoCodigo = request.getParameter("codigoCaso");
        Part filePart = request.getPart("file");
        String comment = request.getParameter("comment");
        String codigoC = request.getParameter("codigoC");
        String usuarioComenta = request.getParameter("usuarioComenta");
        String comentarios = request.getParameter("comentarios");
        String parteAfectada = request.getParameter("parteAfectada");

        String comentariosEstado = request.getParameter("comentariosEstado");
        String usuarioEstado = request.getParameter("usuarioEstado");

        //Parametros de cambio de usuario 
        String btnAsignar = request.getParameter("btnAsignar");
        String codigoAsingado = request.getParameter("codigoAsingado");
        String usuarioAsignado = request.getParameter("usuarioAsignado");
        String UsuarioCam = request.getParameter("UsuarioCam");
        String comentariosAsignado = request.getParameter("comentariosAsignado");
        String estadoAsignado = request.getParameter("estadoAsignado");

        String codigoCita = request.getParameter("codigoCita");
        String usuarioCita = request.getParameter("usuarioCita");
        String Fechacita = request.getParameter("cita");
        String comentarioCita = request.getParameter("comentarioCita");
        String btnCitar = request.getParameter("btnCitar");

        //ver casos por una persona especifica
        String verCasos = request.getParameter("verCasos");

        //Crear caso 
        String btnCrearCaso = request.getParameter("btnCrearCaso");
        String cedulaCaso = request.getParameter("cedulaCaso");

        Date date;
        EstadoCaso estadocaso;
        TipoCaso tipo;

        if (accion != null && !accion.equals("")) {
            switch (accion) {
                case "crear":
                    //Creamos codigo del caso.
                    Integer codigoCaso = Integer.parseInt(persona);
                    Caso buscarCodigo = jpaCaso.findCaso(codigoCaso);
                    if (buscarCodigo == null) {
                        codigoCaso = codigoCaso + 1;
                    } else {
                        Random r = new Random();
                        int valorDado = r.nextInt(100) + 1;
                        codigoCaso = codigoCaso + valorDado;
                    }
                    Persona per = new Persona(Integer.parseInt(persona));

                    //Por ser primera vez el Estado del caso sera Emitida
                    estadocaso = new EstadoCaso(101);
                    tipo = new TipoCaso(Tipo);
                    Caso crearCaso = new Caso(codigoCaso, textarea, fechaAfectacion, pcl, parteAfectada, tiempoInca, creado, per, creado, estadocaso, tipo);

                    try {
                        mensaje = jpaCaso.crear(crearCaso);

                        //creamos el flujo del caso.
                        caso casoFlujo = new caso();
                        String fechaActual = casoFlujo.obtenerFechaActual();
                        hora_actual = casoFlujo.obtenerHoraActual();

                        SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
                        SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
                        date = formatterFecha.parse(fechaActual);
                        fecha = formatterFecha2.format(date);

                        estadoCaso = "101";
                        fecha_creacion = fecha + " " + hora_actual;

                        Flujocaso flujoCaso = new Flujocaso(codigoCaso, estadoCaso, creado, fecha_creacion, fecha_creacion);
                        jpaflujoCaso.create(flujoCaso);

                        //agregamos el seguimiento del caso
                        CambioCaso casoSeguimiento = new CambioCaso(codigoCaso.toString(), estadoCaso, creado, fecha_creacion);
                        seguimientoCaso.create(casoSeguimiento);
                        caso camEstdo = new caso();
                        boolean respuesta = camEstdo.actualizaCasoAsociado(codigoCaso.toString());
                    } catch (Exception ex) {
                        Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    List<CambioCaso> cambioCasoList;
                    List<Caso> listCaso;
                    List<Persona> listPersonas;
                    List<EstadoCaso> listEstado;
                    List<TipoCaso> listTipo;
                    List<Usuario> usuarioList;
                    List<Flujocaso> flujoList;
                    listPersonas = jpaperson.findPersonaEntities();
                    session.setAttribute("Persona", listPersonas);
                    listCaso = jpaCaso.findCasoEntities();
                    session.setAttribute("Caso", listCaso);
                    listEstado = jpaEstado.findEstadoCasoEntities();
                    session.setAttribute("Estado", listEstado);
                    listTipo = jpaTipo.findTipoCasoEntities();
                    session.setAttribute("Tipo", listTipo);
                    usuarioList = jpaUsuario.findUsuarioEntities();
                    session.setAttribute("Usuario", usuarioList);
                    session.setAttribute("codigoCaso", codigoCaso);
                    List<Cita> citas = citaJpa.findCitaEntities();
                    session.setAttribute("citas", citas);
                    Flujocaso listFlujoCaso = jpaflujoCaso.findFlujocaso(codigoCaso);
                    session.setAttribute("listFlujoCaso", listFlujoCaso);
                    cambioCasoList = seguimientoCaso.findCambioCasoEntities();
                    session.setAttribute("cambioCasoList", cambioCasoList);

                    rd = request.getRequestDispatcher("/view/detalleCaso.jsp");
                    break;

            }
        }

        if (btnCrearCaso != null && !btnCrearCaso.equals("")) {
            //Creamos codigo del caso.
            Integer codigoCaso = Integer.parseInt(cedulaCaso);
            Caso buscarCodigo = jpaCaso.findCaso(codigoCaso);
            if (buscarCodigo != null){
             
                Random r = new Random();
                int valorDado = r.nextInt(10) + 1;
                codigoCaso = codigoCaso + valorDado;
            }
            Persona per = new Persona(Integer.parseInt(cedulaCaso));

            //Por ser primera vez el Estado del caso sera Emitida
            estadocaso = new EstadoCaso(101);
            tipo = new TipoCaso(Tipo);
            Caso crearCaso = new Caso(codigoCaso, textarea, fechaAfectacion, pcl, parteAfectada, tiempoInca, creado, per, creado, estadocaso, tipo);

            try {
                mensaje = jpaCaso.crear(crearCaso);

                //creamos el flujo del caso.
                caso casoFlujo = new caso();
                String fechaActual = casoFlujo.obtenerFechaActual();
                hora_actual = casoFlujo.obtenerHoraActual();

                SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
                date = formatterFecha.parse(fechaActual);
                fecha = formatterFecha2.format(date);

                estadoCaso = "101";
                fecha_creacion = fecha + " " + hora_actual;

                Flujocaso flujoCaso = new Flujocaso(codigoCaso, estadoCaso, creado, fecha_creacion, fecha_creacion);
                jpaflujoCaso.create(flujoCaso);

                //agregamos el seguimiento del caso
                CambioCaso casoSeguimiento = new CambioCaso(codigoCaso.toString(), estadoCaso, creado, fecha_creacion);
                seguimientoCaso.create(casoSeguimiento);
                caso camEstdo = new caso();
                boolean respuesta = camEstdo.actualizaCasoAsociado(cedulaCaso.toString());
            } catch (Exception ex) {
                Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            List<CambioCaso> cambioCasoList;
            List<Caso> listCaso;
            List<Persona> listPersonas;
            List<EstadoCaso> listEstado;
            List<TipoCaso> listTipo;
            List<Usuario> usuarioList;
            List<Flujocaso> flujoList;
            PersonaClass person = new PersonaClass();
            List<ReportPersona> listPersona = person.listarPersona();
            session.setAttribute("listPersona", listPersona);
            listPersonas = jpaperson.findPersonaEntities();
            session.setAttribute("Persona", listPersonas);
            listCaso = jpaCaso.findCasoEntities();
            session.setAttribute("Caso", listCaso);
            listEstado = jpaEstado.findEstadoCasoEntities();
            session.setAttribute("Estado", listEstado);
            listTipo = jpaTipo.findTipoCasoEntities();
            session.setAttribute("Tipo", listTipo);
            usuarioList = jpaUsuario.findUsuarioEntities();
            session.setAttribute("Usuario", usuarioList);
            session.setAttribute("codigoCaso", codigoCaso);
            List<Cita> citas = citaJpa.findCitaEntities();
            session.setAttribute("citas", citas);
            Flujocaso listFlujoCaso = jpaflujoCaso.findFlujocaso(codigoCaso);
            session.setAttribute("listFlujoCaso", listFlujoCaso);
            cambioCasoList = seguimientoCaso.findCambioCasoEntities();
            session.setAttribute("cambioCasoList", cambioCasoList);
            List<ReportPersona> casoXPersona = clasePerson.datosPersona(codigoCaso);
            session.setAttribute("casoXPersona", casoXPersona);

            rd = request.getRequestDispatcher("/view/detalleCaso.jsp");
        }

        if (editar != null && !editar.equals("")) {
            session.setAttribute("codigoCaso", editar);
            List<ReportPersona> casoXPersona = clasePerson.datosPersona(Integer.parseInt(editar));
            session.setAttribute("casoXPersona", casoXPersona);
            List<AccionesCaso> listAccionesCaso = accionesCasoJpa.findAccionesCasoEntities();
            session.setAttribute("listAccionesCaso", listAccionesCaso);
            List<Flujocaso> flujoList = jpaflujoCaso.findFlujocasoEntities();
            session.setAttribute("flujoList", flujoList);
            Caso CasoUnico = jpaCaso.findCaso(Integer.parseInt(editar));
            session.setAttribute("CasoUnico", CasoUnico);
            rd = request.getRequestDispatcher("/view/detalleCaso.jsp");
        }

        if (verCasos != null && !verCasos.equals("")) {
            caso casoXcaso = new caso();
            Integer cedulaPersona = Integer.parseInt(verCasos);
            Persona person = jpaperson.findPersona(cedulaPersona);
            List<ReportCasos> personasxCaso = casoXcaso.listarCasoXPersona(cedulaPersona);
            session.setAttribute("personasxCaso", personasxCaso);
            session.setAttribute("person", person);
            rd = request.getRequestDispatcher("/view/detallePersona.jsp");
        }

        if (btnAsignar != null && !btnAsignar.equals("")) {
            caso camEstdo = new caso();
            boolean respuesta;
            respuesta = camEstdo.actualizarUsuario(Integer.parseInt(codigoAsingado), UsuarioCam);
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
                    CambioCaso casoSeguimiento = new CambioCaso(codigoAsingado, estadoAsignado, usuarioEstado, fecha_actualizada);
                    try {
                        seguimientoCaso.create(casoSeguimiento);
                    } catch (Exception e) {
                        Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    //Cambiamos el estado del caso
                    camEstdo.actualizarFlujoCaso(codigoAsingado, estadoAsignado, usuarioAsignado, fecha_actualizada);
                    //Agrgeamos los comentarios ya rchivos que suban.
                    try {

                        AccionesCaso accionComentar = new AccionesCaso(Integer.parseInt(codigoAsingado), usuarioAsignado, comentariosAsignado, "", fecha_actualizada);

                        accionesCasoJpa.create(accionComentar);
                    } catch (Exception e) {
                        Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, e);
                    }

                } catch (Exception e) {
                    Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }

            List<CambioCaso> listCambio;
            List<EstadoCaso> EstadoList;
            List<TipoCaso> TipoList;
            List<Usuario> ListUsuairio;
            List<Flujocaso> ListFlujo;
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
            caso CasosList = new caso();
            List<ReportCasos> listCaso = CasosList.listarCaso();
            session.setAttribute("ListCaso", listCaso);
            List<AccionesCaso> listAccionesCaso = accionesCasoJpa.findAccionesCasoEntities();
            session.setAttribute("listAccionesCaso", listAccionesCaso);
            session.setAttribute("codigoCaso", codigoAsingado);
            List<ReportPersona> casoXPersona = clasePerson.datosPersona(Integer.parseInt(codigoAsingado));
            session.setAttribute("casoXPersona", casoXPersona);
            rd = request.getRequestDispatcher("/view/detalleCaso2.jsp");

        }
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
                    CambioCaso casoSeguimiento = new CambioCaso(casoCodigo, Estado, usuarioEstado, fecha_actualizada);
                    try {
                        seguimientoCaso.create(casoSeguimiento);
                    } catch (Exception e) {
                        Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, e);
                    }
                    //Cambiamos el estado del caso
                    camEstdo.actualizarFlujoCaso(casoCodigo, Estado, usuarioEstado, fecha_actualizada);
                    //Agrgeamos los comentarios ya rchivos que suban.
                    try {

                        AccionesCaso accionComentar = new AccionesCaso(Integer.parseInt(casoCodigo), usuarioEstado, comentariosEstado, "", fecha_actualizada);

                        accionesCasoJpa.create(accionComentar);
                    } catch (Exception e) {
                        Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, e);
                    }

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
            caso CasosList = new caso();
            List<ReportCasos> listCaso = CasosList.listarCaso();
            session.setAttribute("ListCaso", listCaso);
            List<AccionesCaso> listAccionesCaso = accionesCasoJpa.findAccionesCasoEntities();
            session.setAttribute("listAccionesCaso", listAccionesCaso);
            session.setAttribute("codigoCaso", casoCodigo);
            List<ReportPersona> casoXPersona = clasePerson.datosPersona(Integer.parseInt(casoCodigo));
            session.setAttribute("casoXPersona", casoXPersona);
            rd = request.getRequestDispatcher("/view/detalleCaso2.jsp");
        }
        if (comment != null && !comment.equals("")) {
            //guardamos los comentarios hechos dentro del caso.
            caso accionesFecha = new caso();

            try {

                String actual = accionesFecha.obtenerFechaActual();
                hora_actual = accionesFecha.obtenerHoraActual();

                SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
                date = formatterFecha.parse(actual);
                fecha = formatterFecha2.format(date);
                String fecha_actualizada;
                fecha_actualizada = fecha + " " + hora_actual;

                AccionesCaso accionComentar = new AccionesCaso(Integer.parseInt(codigoC), usuarioComenta, comentarios, "", fecha_actualizada);

                accionesCasoJpa.create(accionComentar);
            } catch (Exception e) {
                Logger.getLogger(CasoServlet.class.getName()).log(Level.SEVERE, null, e);
            }

            List<AccionesCaso> listAccionesCaso = accionesCasoJpa.findAccionesCasoEntities();
            session.setAttribute("listAccionesCaso", listAccionesCaso);
            session.setAttribute("codigoCaso", codigoC);
            caso CasosList = new caso();
            List<ReportCasos> listCaso = CasosList.listarCaso();
            session.setAttribute("ListCaso", listCaso);
            rd = request.getRequestDispatcher("/view/detalleCaso2.jsp");

        }

        if (btnCitar != null && !btnCitar.equals("")) {
            Cita cita = new Cita(codigoCita, Fechacita, "Abierta", Integer.parseInt(codigoCita));
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
                hora_actual = tomarFecha.obtenerHoraActual();

                SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat formatterFecha2 = new SimpleDateFormat("yyyy-MM-dd");
                date = formatterFecha.parse(fechaActual);
                fecha = formatterFecha2.format(date);
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
            List<ReportPersona> casoXPersona = clasePerson.datosPersona(Integer.parseInt(codigoCita));
            session.setAttribute("casoXPersona", casoXPersona);
            Dashboard dashboard = new Dashboard();
            List<ReportCitas> listCitas = dashboard.citasDeldia();
            session.setAttribute("listCitas", listCitas);
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
