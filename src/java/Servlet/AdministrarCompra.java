/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controladores.ControlSistema;
import Dto.CompraDTO;
import Dto.DetalleCompraDTO;
import Dto.ProductoDTO;
import Dto.ResponseDTO;
import Dto.UsuarioDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
public class AdministrarCompra extends HttpServlet {

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
                    //listarUsuarios(request, response);
                } else if (request.getParameter("accion").equals("GUARDAR")) {
                    //guardarUsuario(request, response);
                } else if (request.getParameter("accion").equals("AGREGAR")) {
                    agregarCompra(request, response);
                } else if (request.getParameter("accion").equals("BORRAR")) {
                    //borrarUsuario(request, response);
                } else if (request.getParameter("accion").equals("BUSCAR")) {
                    //buscarUsuario(request, response);
                }
            } else {
                pagina = "/web/administrarCampras.jsp";
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

    private void agregarCompra(HttpServletRequest req, HttpServletResponse res) {
        Integer idProducto = 0, cantidad = 0;
        String direccion = "";

        String parameterNames = "";
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            parameterNames = (String) e.nextElement();
            if (parameterNames.equals("idProducto")) {
                idProducto = Integer.parseInt(req.getParameter("idProducto"));
            } else if (parameterNames.equals("cantidad")) {
                cantidad = Integer.parseInt(req.getParameter("cantidad"));
            } else if (parameterNames.equals("direccion")) {
                direccion = req.getParameter("direccion");
            }
        }
        //Cliente
        String run = (String) req.getSession().getAttribute("run");
        UsuarioDTO usuario = ControlSistema.getInstancia().getControlUsuario().getUsuarioByRun(run);
        //Producto
        ProductoDTO producto = ControlSistema.getInstancia().getControlProducto().getProductoByID(idProducto);
        //Compra
        int idCompra = ControlSistema.getInstancia().getControlCompra().getID();
        CompraDTO compra = new CompraDTO();
        compra.setIdCompra(idCompra);
        compra.setDireccion(direccion);
        compra.setEstado("En bodega");
        compra.setMetodoDespacho("Reparto a domicilio");
        compra.setPersonaRetira(usuario.getNombres() + " " + usuario.getApellidos());
        compra.setRun(run);
        //Detalle compra
        DetalleCompraDTO detalle = new DetalleCompraDTO();
        detalle.setIdCompra(idCompra);
        detalle.setIdProducto(idProducto);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(producto.getPrecio() * cantidad);

        ResponseDTO responseJson = new ResponseDTO();

        if (producto.getStock() >= cantidad) {
            //INSERT
            responseJson.success = ControlSistema.getInstancia().getControlCompra().insertarCompra(compra);
            ControlSistema.getInstancia().getControlDetalleCompra().insertarDetalleCompra(detalle);
            //UPDATE
            producto.setStock(producto.getStock() - cantidad);
            ControlSistema.getInstancia().getControlProducto().actualizarProducto(producto);

            if (responseJson.success) {
                responseJson.statusCode = 1;
                responseJson.statusText = "Compra realizada correctamente.";
            } else {
                responseJson.statusCode = 0;
                responseJson.statusText = "Se a producido un error inesperado.";
            }

        } else {
            responseJson.success = false;
            responseJson.statusCode = 0;
            responseJson.statusText = "No hay stock disponible.";
        }

        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.setData(compra);
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
