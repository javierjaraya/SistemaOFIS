/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controladores.ControlSistema;
import Dto.ResponseDTO;
import Dto.ResponseTablaDTO;
import Dto.UsuarioDTO;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import javax.servlet.http.*;

/**
 *
 * @author 
 */
public class AdministrarUsuario extends HttpServlet {

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
                
        if (request != null && request.isRequestedSessionIdValid() ) {
            String pagina = "";
            String mensaje1 = "";
            String mensaje2 = "";

            if (request.getParameter("accion") != null && !request.getParameter("accion").equals("")) {
                if (request.getParameter("accion").equals("LISTADO")) {
                    listarUsuarios(request, response);
                } else if (request.getParameter("accion").equals("GUARDAR")) {
                    guardarUsuario(request, response);
                } else if (request.getParameter("accion").equals("AGREGAR")) {
                    agregarUsuario(request, response);
                } else if (request.getParameter("accion").equals("BORRAR")) {
                    borrarUsuario(request, response);
                } else if (request.getParameter("accion").equals("BUSCAR")) {
                    buscarUsuario(request, response);
                }
            } else {
                pagina = "/web/administrarUsuarios.jsp";
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    
    private void listarUsuarios(HttpServletRequest req, HttpServletResponse res) {
        int pagina = 1;
        try {
            pagina = Integer.parseInt(req.getParameter("pagina").toString());
        } catch (Exception e) {
        }

        String strCant = "25";
        int cantidad = (strCant.equals("")) ? 0 : Integer.parseInt(strCant);
        String nombres = req.getParameter("nombres");//Si se quiere buscar por los nombres

        ResponseTablaDTO responseJson = new ResponseTablaDTO();
        //CODIGO VARIABLE      
        List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

        String where = " where 1=1 ";

        if (nombres != null) {
            where += " and nombres LIKE '%" + nombres + "%' ";
        }
        //SIN FILTRO

        //mas codigo
        lista = ControlSistema.getInstancia().getControlUsuario().getUsuarios(pagina, cantidad, where);

        //FIN CODIGO VARIABLE
        int size = lista.size();
        responseJson.setTotal(size);
        res.setCharacterEncoding("UTF-8");
        responseJson.setRows(lista);
        PrintWriter out;
        try {
            Gson gson = new Gson();
            String jsonOutput = gson.toJson(responseJson);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarUsuario(HttpServletRequest req, HttpServletResponse res) {
        Integer telefono = 0, idPerfil = 0, estado = 0;
        String run = "", nombres = "", apellidos = "", correoElectronico = "", direccion = "", clave = "", tipoUsuario = "", sexo = "";

        String parameterNames = "";
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            parameterNames = (String) e.nextElement();
            if (parameterNames.equals("telefono")) {
                telefono = Integer.parseInt(req.getParameter("telefono"));
            } else if (parameterNames.equals("idPerfil")) {
                idPerfil = Integer.parseInt(req.getParameter("idPerfil"));
            } else if (parameterNames.equals("estado")) {
                estado = Integer.parseInt(req.getParameter("estado"));
            } else if (parameterNames.equals("run")) {
                run = req.getParameter("run");
            } else if (parameterNames.equals("nombres")) {
                nombres = req.getParameter("nombres");
            } else if (parameterNames.equals("apellidos")) {
                apellidos = req.getParameter("apellidos");
            } else if (parameterNames.equals("correoElectronico")) {
                correoElectronico = req.getParameter("correoElectronico");
            } else if (parameterNames.equals("direccion")) {
                direccion = req.getParameter("direccion");
            } else if (parameterNames.equals("clave")) {
                clave = req.getParameter("clave");
            } else if (parameterNames.equals("tipoUsuario")) {
                tipoUsuario = req.getParameter("tipoUsuario");
            } else if (parameterNames.equals("sexo")) {
                sexo = req.getParameter("sexo");
            }
        }
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setTelefono(telefono);
        usuario.setIdPerfil(idPerfil);
        usuario.setEstado(estado);
        usuario.setRun(run);
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setDireccion(direccion);
        usuario.setClave(clave);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setSexo(sexo);

        ResponseDTO responseJson = new ResponseDTO();
        String mensaje = "";

        responseJson.success = ControlSistema.getInstancia().getControlUsuario().actualizarUsuario(usuario);
        if (responseJson.success) {
            responseJson.statusCode = 1;
            mensaje = "Usuario actualizado correctamente.";
        } else {
            responseJson.statusCode = 0;
            mensaje = "Se a producido un error inesperado.";
        }

        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.data = usuario;
        responseJson.statusText = mensaje;
        try {
            Gson gson = new Gson();
            String jsonOutput = gson.toJson(responseJson);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void agregarUsuario(HttpServletRequest req, HttpServletResponse res) {
        Integer telefono = 0, idPerfil = 0, estado = 0;
        String run = "", nombres = "", apellidos = "", correoElectronico = "", direccion = "", clave = "", tipoUsuario = "", sexo = "";

        String parameterNames = "";
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            parameterNames = (String) e.nextElement();
            if (parameterNames.equals("telefono")) {
                telefono = Integer.parseInt(req.getParameter("telefono"));
            } else if (parameterNames.equals("idPerfil")) {
                idPerfil = Integer.parseInt(req.getParameter("idPerfil"));
            } else if (parameterNames.equals("estado")) {
                estado = Integer.parseInt(req.getParameter("estado"));
            } else if (parameterNames.equals("run")) {
                run = req.getParameter("run");
            } else if (parameterNames.equals("nombres")) {
                nombres = req.getParameter("nombres");
            } else if (parameterNames.equals("apellidos")) {
                apellidos = req.getParameter("apellidos");
            } else if (parameterNames.equals("correoElectronico")) {
                correoElectronico = req.getParameter("correoElectronico");
            } else if (parameterNames.equals("direccion")) {
                direccion = req.getParameter("direccion");
            } else if (parameterNames.equals("clave")) {
                clave = req.getParameter("clave");
            } else if (parameterNames.equals("tipoUsuario")) {
                tipoUsuario = req.getParameter("tipoUsuario");
            } else if (parameterNames.equals("sexo")) {
                sexo = req.getParameter("sexo");
            }
        }

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setTelefono(telefono);
        usuario.setIdPerfil(idPerfil);
        usuario.setEstado(estado);
        usuario.setRun(run);
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setDireccion(direccion);
        usuario.setClave(clave);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setSexo(sexo);

        ResponseDTO responseJson = new ResponseDTO();
        String mensaje = "";

        responseJson.success = ControlSistema.getInstancia().getControlUsuario().insertarUsuario(usuario);
        

        if (responseJson.success) {
            responseJson.statusCode = 1;
            mensaje = "Usuario agregado correctamente.";
        } else {
            responseJson.statusCode = 0;
            mensaje = "Se a producido un error inesperado.";
        }

        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.data = usuario;
        responseJson.statusText = mensaje;
        
        try {
            Gson gson = new Gson();
            String jsonOutput = gson.toJson(responseJson);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void borrarUsuario(HttpServletRequest req, HttpServletResponse res) {
        String run = req.getParameter("run");

        ResponseDTO responseJson = new ResponseDTO();
        String mensaje = "";

        responseJson.success = ControlSistema.getInstancia().getControlUsuario().eliminarUsuario(run);
        if (responseJson.success == true) {
            responseJson.statusCode = 1;
            mensaje = "El usuario fue eliminado correctamente.";
        } else {
            responseJson.statusCode = 0;
            mensaje = "Se a producido un error inesperado.";
        }

        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.statusText = mensaje;
        Gson gson = new Gson();
        try {
            String jsonOutput = gson.toJson(responseJson);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buscarUsuario(HttpServletRequest req, HttpServletResponse res) {
        String q = "";
        String parameterNames = "";
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            parameterNames = (String) e.nextElement();
            if (parameterNames.equals("q")) {
                q = req.getParameter("q");
            }
        }

        int pagina = 1;
        try {
            pagina = Integer.parseInt(req.getParameter("pagina").toString());
        } catch (Exception e) {
        }

        String strCant = "25";
        int cantidad = (strCant.equals("")) ? 0 : Integer.parseInt(strCant);

        ResponseTablaDTO responseJson = new ResponseTablaDTO();
        //CODIGO VARIABLE
        List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
        String where = " WHERE u.nombres LIKE '%" + q + "%' OR u.apellidos LIKE '%" + q + "%' OR u.run LIKE '%" + q + "%' ";

        //mas codigo
        lista = ControlSistema.getInstancia().getControlUsuario().getUsuarios(pagina, cantidad, where);

        //FIN CODIGO VARIABLE
        int size = lista.size();
        responseJson.setTotal(size);
        res.setCharacterEncoding("UTF-8");
        responseJson.setRows(lista);
        PrintWriter out;
        try {
            Gson gson = new Gson();
            String jsonOutput = gson.toJson(responseJson);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
