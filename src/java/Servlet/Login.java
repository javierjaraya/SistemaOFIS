package Servlet;

import Controladores.ControlSistema;
import Dto.UsuarioDTO;
import java.io.*;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.*;

import javax.servlet.http.*;

public class Login extends HttpServlet {    

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String pagina = "/login.jsp";
        String paginaError = "/login_error.jsp";
        String paginaActiva = null;

        try {
            HttpSession sesion = req.getSession(false);
            if (sesion == null) {
                sesion = req.getSession(true);
            } else {
                paginaActiva = (String) req.getSession().getAttribute("urlActiva");
            }
            //Obtenemos datos del formulario
            String txtRun = (req.getParameter("run") != null) ? req.getParameter("run") : (String) req.getSession().getAttribute("run");
            String txtClave = (req.getParameter("clave") != null) ? req.getParameter("clave") : (String) req.getSession().getAttribute("clave");

            if ((txtRun != null) && (txtClave != null)) {
                //Validar Usuario y password, verificar el perfil
                txtRun = txtRun.toUpperCase();
                System.out.println("Intento inicio sesion , Alias: " + txtRun);

                ControlSistema control = ControlSistema.getInstancia();

                UsuarioDTO usuario = control.getControlUsuario().getUsuarioByRun(txtRun);

                if (usuario.getRun() != null) {

                    if (usuario.getClave().equalsIgnoreCase(txtClave)) {
                        if (usuario.getIdPerfil() > 0) {

                            //USUARIO VALIDO
                            
                            //Carga Perfil
                            sesion.setMaxInactiveInterval(10 * 60);
                            sesion.setAttribute("usuario", usuario);
                            sesion.setAttribute("run", usuario.getRun());
                            sesion.setAttribute("nombres", usuario.getNombres());
                            sesion.setAttribute("apellidos", usuario.getApellidos());
                            sesion.setAttribute("idPerfil", usuario.getIdPerfil());
                            sesion.setAttribute("nombrePerfil", usuario.getNombrePerfil());
                            sesion.setAttribute("estado", usuario.getEstado());

                            pagina = "/web/index.jsp";
                            sesion.setAttribute("urlActiva", pagina);

                            sesion.setAttribute("mensajeLogin", "Contraseña valida");
                        } else {
                            pagina = paginaError;
                            sesion.setAttribute("mensajeLogin", "No posee el perfil para ingresar al sistema, favor contactar al Administrador.");
                        }
                    } else {
                        pagina = paginaError;
                        sesion.setAttribute("mensajeLogin", "Contraseña invalida, intentelo nuevamente.");
                    }
                } else {
                    pagina = paginaError;
                    sesion.setAttribute("mensajeLogin", "El usuario \"" + req.getParameter("run") + "\" no esta registrado.");
                }

            } else {
                if (paginaActiva != null) {
                    pagina = paginaActiva;
                }
            }

        } catch (Exception e) {
            pagina = paginaError;
            req.getSession().setAttribute("mensajeLogin", "Sesion inválida");
            e.printStackTrace();
        }
        System.out.println("Intento inicio sesion , pagina: " + pagina);
        ServletContext sc = getServletConfig().getServletContext();
        RequestDispatcher rdNext = sc.getRequestDispatcher(pagina);
        rdNext.forward(req, res);
    }

}
