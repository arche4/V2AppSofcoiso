/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.Servlet;

import com.co.sofcoiso.controller.UsuarioJpaController;
import com.co.sofcoiso.modelo.Usuario;
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
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        RequestDispatcher rd = null;
        UsuarioJpaController ujc = new UsuarioJpaController(JPAFactory.getFACTORY());

        String idusuario = request.getParameter("cedula");
        String nombreUsuario = request.getParameter("nombre");
        String apellidoUsuario = request.getParameter("apellido");
        String claveUsuario = request.getParameter("clave");
        String rolUsuario = request.getParameter("rol");

        String accion = request.getParameter("accion");
        String eliminar = request.getParameter("eliminar");
        String btnModificar = request.getParameter("btnModificar");

        String cedulaUser = request.getParameter("cedulaUser");
        String nomUser = request.getParameter("nomUser");
        String apellidoUser = request.getParameter("apellidoUser");
        String rolUser = request.getParameter("rolUser");
        String ClaveUser = request.getParameter("ClaveUser");

        if (accion != null && !accion.equals("")) {
            switch (accion) {
                case "crear":

                    Usuario usuario = new Usuario(Integer.parseInt(idusuario), nombreUsuario, apellidoUsuario, claveUsuario, rolUsuario);
                    try {
                        ujc.create(usuario);
                        List<Usuario> ListUsuario = ujc.findUsuarioEntities();
                        session.setAttribute("listUsuario", ListUsuario);
                        rd = request.getRequestDispatcher("/view/usuario.jsp");
                    } catch (Exception e) {
                        Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, e);
                    }

                    break;
                case "listar":
                    List<Usuario> lUsuario = ujc.findUsuarioEntities();
                    session.setAttribute("Usuario", lUsuario);
                    rd = request.getRequestDispatcher("/view/usuario.jsp");
                    break;

                case "modificar":
                    Usuario user = new Usuario(Integer.parseInt(idusuario), nombreUsuario, apellidoUsuario, claveUsuario, rolUsuario);
                    try {
                        ujc.edit(user);
                    } catch (Exception ex) {
                        Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    List<Usuario> usuarioList = ujc.findUsuarioEntities();
                    session.setAttribute("Usuario", usuarioList);
                    rd = request.getRequestDispatcher("/view/usuario.jsp");
                    break;
                case "volver":
                    rd = request.getRequestDispatcher("/view/usuario.jsp");
                    break;

            }
        }
        if (btnModificar != null && !btnModificar.equals("")) {
            try {
               Usuario user = new Usuario(Integer.parseInt(cedulaUser), nomUser, apellidoUser, ClaveUser, rolUser);
                ujc.edit(user);
            } catch (Exception ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Usuario> userList = ujc.findUsuarioEntities();
            session.setAttribute("listUsuario", userList);
            rd = request.getRequestDispatcher("/view/usuario.jsp");
        }

        if (eliminar != null && !eliminar.equals("")) {
            try {
                ujc.destroy(Integer.parseInt(cedulaUser));
            } catch (Exception ex) {
                Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Usuario> userList = ujc.findUsuarioEntities();
            session.setAttribute("listUsuario", userList);
            rd = request.getRequestDispatcher("/view/usuario.jsp");
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
