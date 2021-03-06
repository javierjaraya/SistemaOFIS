/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controladores.ControlSistema;
import Dto.ResponseDTO;
import Dto.SolicitudRegistroDTO;
import Dto.UsuarioDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
public class AdministrarSolicitudRegistro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request != null && request.isRequestedSessionIdValid()) {
            String pagina = "";
            String mensaje1 = "";
            String mensaje2 = "";

            if (request.getParameter("accion") != null && !request.getParameter("accion").equals("")) {
                if (request.getParameter("accion").equals("LISTADO")) {
                    listarSolicitudesRegistro(request, response);
                } else if (request.getParameter("accion").equals("GUARDAR")) {
                    //guardarUsuario(request, response);
                } else if (request.getParameter("accion").equals("AGREGAR")) {
                    //agregarUsuario(request, response);
                } else if (request.getParameter("accion").equals("BORRAR")) {
                    borrarUsuario(request, response);
                } else if (request.getParameter("accion").equals("ACEPTAR")) {
                    aceptarUsuario(request, response);
                } else if (request.getParameter("accion").equals("BUSCAR")) {
                    //buscarUsuario(request, response);
                }
            } else {
                pagina = "/web/administrarSolicitudesRegistro.jsp";
                ServletContext sc = getServletConfig().getServletContext();
                RequestDispatcher rdNext = sc.getRequestDispatcher(pagina);
                rdNext.forward(request, response);
            }
        } else {
            System.out.println(">>>>> Sesion invalida");
            String pagina = "/error.jsp";
            request.getSession().setAttribute("mensajeLogin", "Sesión invalida.");
            request.getSession().setAttribute("flagResultado", "false");
            ServletContext sc = getServletConfig().getServletContext();
            RequestDispatcher rdNext = sc.getRequestDispatcher(pagina);
            rdNext.forward(request, response);
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

    private void listarSolicitudesRegistro(HttpServletRequest req, HttpServletResponse res) {
        int pagina = 1;
        try {
            pagina = Integer.parseInt(req.getParameter("pagina").toString());
        } catch (Exception e) {
        }

        String strCant = "25";
        int cantidad = (strCant.equals("")) ? 0 : Integer.parseInt(strCant);

        //CODIGO VARIABLE
        List<SolicitudRegistroDTO> lista = new ArrayList<SolicitudRegistroDTO>();
        String where = "";

        //mas codigo
        lista = ControlSistema.getInstancia().getControlSolicitudRegistro().getSolicitudesRegistro(pagina, cantidad, where);

        //FIN CODIGO VARIABLE
        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            Gson gson = new Gson();
            String jsonOutput = gson.toJson(lista);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void borrarUsuario(HttpServletRequest req, HttpServletResponse res) {
        int idSolicitudBorrar = Integer.parseInt(req.getParameter("idSolicitudBorrar"));
        ResponseDTO responseJson = new ResponseDTO();
        String mensaje = "";
        SolicitudRegistroDTO solicitud = ControlSistema.getInstancia().getControlSolicitudRegistro().getSolicitudRegistroByIdSolicitud(idSolicitudBorrar);
        responseJson.success = ControlSistema.getInstancia().getControlSolicitudRegistro().eliminarSolicitudRegistro(solicitud.getIdSolicitud());
        boolean status = ControlSistema.getInstancia().getControlUsuario().eliminarUsuario(solicitud.getRun());
        if (responseJson.success == true) {
            responseJson.statusCode = 1;
            mensaje = "El usuario fue eliminado.";
        } else {
            responseJson.statusCode = 0;
            mensaje = "Se a producido un error inesperado al intentar eliminar el usuario.";
        }
        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.statusText = mensaje;
        responseJson.data = solicitud;
        Gson gson = new Gson();
        try {
            String jsonOutput = gson.toJson(responseJson);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void aceptarUsuario(HttpServletRequest req, HttpServletResponse res) {
        int idSolicitudConfirmar = Integer.parseInt(req.getParameter("idSolicitudConfirmar"));
        ResponseDTO responseJson = new ResponseDTO();
        String mensaje = "";
        SolicitudRegistroDTO solicitud = ControlSistema.getInstancia().getControlSolicitudRegistro().getSolicitudRegistroByIdSolicitud(idSolicitudConfirmar);
        UsuarioDTO user = ControlSistema.getInstancia().getControlUsuario().getUsuarioByRun(solicitud.getRun());
        responseJson.success = ControlSistema.getInstancia().getControlSolicitudRegistro().eliminarSolicitudRegistro(solicitud.getIdSolicitud());
        user.setEstado(1);
        boolean status = ControlSistema.getInstancia().getControlUsuario().actualizarUsuario(user);
        if (responseJson.success == true) {
            responseJson.statusCode = 1;
            mensaje = "El usuario fue aceptado.";
        } else {
            responseJson.statusCode = 0;
            mensaje = "Se a producido un error inesperado al intentar aceptar el usuario.";
        }
        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.statusText = mensaje;
        responseJson.data = solicitud;
        Gson gson = new Gson();
        try {
            String jsonOutput = gson.toJson(responseJson);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
