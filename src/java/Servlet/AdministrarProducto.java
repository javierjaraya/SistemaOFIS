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
import Dto.ResponseTablaDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class AdministrarProducto extends HttpServlet {

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
                    listarProductos(request, response);
                } else if (request.getParameter("accion").equals("GUARDAR")) {
                    guardarProducto(request, response);
                } else if (request.getParameter("accion").equals("AGREGAR")) {
                    agregarProducto(request, response);
                } else if (request.getParameter("accion").equals("BORRAR")) {
                    borrarProducto(request, response);
                } else if (request.getParameter("accion").equals("BUSCAR")) {
                    buscarProducto(request, response);
                } else if (request.getParameter("accion").equals("BUSCAR_BY_ID")) {
                    buscarProductoByID(request, response);
                } else if (request.getParameter("accion").equals("LISTADO_BY_PAGINACION")) {
                    listadoPaginacion(request, response);
                }
            } else {
                pagina = "/web/administrarProductos.jsp";
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

    private void listarProductos(HttpServletRequest req, HttpServletResponse res) {
        int pagina = 1;
        try {
            pagina = Integer.parseInt(req.getParameter("pagina").toString());
        } catch (Exception e) {
        }

        String strCant = "25";
        int cantidad = (strCant.equals("")) ? 0 : Integer.parseInt(strCant);

        //CODIGO VARIABLE
        List<ProductoDTO> lista = new ArrayList<ProductoDTO>();
        String where = "";

        //mas codigo
        lista = ControlSistema.getInstancia().getControlProducto().getProductos(pagina, cantidad, where);

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

    private void guardarProducto(HttpServletRequest req, HttpServletResponse res) {
        Integer idProducto = 0, stock = 0, idCategoria = 0;
        String nombreProducto = "", descripcionProducto = "";
        double precio = 0;

        String parameterNames = "";
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            parameterNames = (String) e.nextElement();
            if (parameterNames.equals("idProducto")) {
                idProducto = Integer.parseInt(req.getParameter("idProducto"));
            } else if (parameterNames.equals("stock")) {
                stock = Integer.parseInt(req.getParameter("stock"));
            } else if (parameterNames.equals("idCategoria")) {
                idCategoria = Integer.parseInt(req.getParameter("idCategoria"));
            } else if (parameterNames.equals("nombreProducto")) {
                nombreProducto = req.getParameter("nombreProducto");
            } else if (parameterNames.equals("descripcionProducto")) {
                descripcionProducto = req.getParameter("descripcionProducto");
            } else if (parameterNames.equals("precio")) {
                precio = Double.parseDouble(req.getParameter("precio"));
            }
        }
        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(idProducto);
        producto.setStock(stock);
        producto.setIdCategoria(idCategoria);
        producto.setNombreProducto(nombreProducto);
        producto.setDescripcionProducto(descripcionProducto);
        producto.setPrecio(precio);

        ResponseDTO responseJson = new ResponseDTO();
        String mensaje = "";

        responseJson.success = ControlSistema.getInstancia().getControlProducto().actualizarProducto(producto);
        if (responseJson.success) {
            responseJson.statusCode = 1;
            mensaje = "Producto actualizado correctamente.";
        } else {
            responseJson.statusCode = 0;
            mensaje = "Se a producido un error inesperado.";
        }

        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.data = producto;
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

    private void agregarProducto(HttpServletRequest req, HttpServletResponse res) {
        Integer stock = 0, idCategoria = 0;
        String nombreProducto = "", descripcionProducto = "";
        double precio = 0;

        int idProducto = ControlSistema.getInstancia().getControlProducto().getID();

        String parameterNames = "";
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            parameterNames = (String) e.nextElement();
            if (parameterNames.equals("stock")) {
                stock = Integer.parseInt(req.getParameter("stock"));
            } else if (parameterNames.equals("idCategoria")) {
                idCategoria = Integer.parseInt(req.getParameter("idCategoria"));
            } else if (parameterNames.equals("nombreProducto")) {
                nombreProducto = req.getParameter("nombreProducto");
            } else if (parameterNames.equals("descripcionProducto")) {
                descripcionProducto = req.getParameter("descripcionProducto");
            } else if (parameterNames.equals("precio")) {
                precio = Double.parseDouble(req.getParameter("precio"));
            }
        }

        ProductoDTO producto = new ProductoDTO();
        producto.setIdProducto(idProducto);
        producto.setStock(stock);
        producto.setIdCategoria(idCategoria);
        producto.setNombreProducto(nombreProducto);
        producto.setDescripcionProducto(descripcionProducto);
        producto.setPrecio(precio);

        ResponseDTO responseJson = new ResponseDTO();
        String mensaje = "";

        responseJson.success = ControlSistema.getInstancia().getControlProducto().insertarProducto(producto);
        /*
        try {

            FileItemFactory file_factory = new DiskFileItemFactory();
            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
            List items = servlet_up.parseRequest(req);
            for (int i = 0; i < items.size(); i++) {
                FileItem representa un archivo en memoria que puede ser pasado al disco duro
                FileItem item = (FileItem) items.get(i);
                //item.isFormField() false=input file; true=text field
                if (!item.isFormField()) {
                    //cual sera la ruta al archivo en el servidor
                    File archivo_server = new File("c:/subidos/" + item.getName());
                    //y lo escribimos en el servido
                    item.write(archivo_server);
                }
            }

        } catch (Exception ex) {
            
        }*/

        if (responseJson.success) {
            responseJson.statusCode = 1;
            mensaje = "Producto agregado correctamente.";
        } else {
            responseJson.statusCode = 0;
            mensaje = "Se a producido un error inesperado.";
        }

        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.data = producto;
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

    private void borrarProducto(HttpServletRequest req, HttpServletResponse res) {
        int idProducto = Integer.parseInt(req.getParameter("idProducto"));

        ResponseDTO responseJson = new ResponseDTO();
        String mensaje = "";

        ProductoDTO producto = ControlSistema.getInstancia().getControlProducto().getProductoByID(idProducto);
        DetalleCompraDTO detalle = ControlSistema.getInstancia().getControlDetalleCompra().getDetalleCompraByIdProducto(idProducto);

        if (detalle == null) {

            responseJson.success = ControlSistema.getInstancia().getControlProducto().eliminarProducto(idProducto);

            if (responseJson.success == true) {
                responseJson.statusCode = 1;
                mensaje = "El producto fue eliminado correctamente.";
            } else {
                responseJson.statusCode = 0;
                mensaje = "Se a producido un error inesperado.";
            }
        } else {
            responseJson.success = false;
            responseJson.statusCode = 0;
            mensaje = "El producto esta relacionado a una compra.";
        }

        res.setCharacterEncoding("UTF-8");
        PrintWriter out;
        responseJson.statusText = mensaje;
        responseJson.data = producto;
        Gson gson = new Gson();
        try {
            String jsonOutput = gson.toJson(responseJson);
            out = res.getWriter();
            out.println(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buscarProducto(HttpServletRequest req, HttpServletResponse res) {
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
        List<ProductoDTO> lista = new ArrayList<ProductoDTO>();
        String where = " WHERE p.nombreProduco LIKE '%" + q + "%' OR p.descripcionProducto LIKE '%" + q + "%' ";

        //mas codigo
        lista = ControlSistema.getInstancia().getControlProducto().getProductos(pagina, cantidad, where);

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

    private void buscarProductoByID(HttpServletRequest req, HttpServletResponse res) {
        int idProducto = 0;
        String parameterNames = "";
        for (Enumeration e = req.getParameterNames(); e.hasMoreElements();) {
            parameterNames = (String) e.nextElement();
            if (parameterNames.equals("idProducto")) {
                idProducto = Integer.parseInt(req.getParameter("idProducto"));
            }
        }

        int pagina = 1;
        try {
            pagina = Integer.parseInt(req.getParameter("pagina").toString());
        } catch (Exception e) {
        }

        String strCant = "25";
        int cantidad = (strCant.equals("")) ? 0 : Integer.parseInt(strCant);

        ResponseDTO responseJson = new ResponseDTO();
        //CODIGO VARIABLE
        List<ProductoDTO> lista = new ArrayList<ProductoDTO>();
        String where = " WHERE p.idProducto = " + idProducto + " ";

        //mas codigo
        lista = ControlSistema.getInstancia().getControlProducto().getProductos(pagina, cantidad, where);

        ProductoDTO producto = new ProductoDTO();
        producto = lista.get(0);

        //FIN CODIGO VARIABLE
        int size = lista.size();
        res.setCharacterEncoding("UTF-8");
        responseJson.data = producto;
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

    private void listadoPaginacion(HttpServletRequest req, HttpServletResponse res) {
        int pagina = 1;
        try {
            pagina = Integer.parseInt(req.getParameter("pagina").toString());
        } catch (Exception e) {
        }

        String strCant = req.getParameter("por_pagina").toString();
        int cantidad = (strCant.equals("")) ? 0 : Integer.parseInt(strCant);

        String orden = req.getParameter("orden").toString();
        if (orden == "Defecto") {
            orden = "";
        } else if (orden == "A-Z") {
            orden = " ORDER BY p.nombreProducto ASC";
        } else if (orden == "Z-A") {
            orden = " ORDER BY p.nombreProducto DESC ";
        } else if (orden == "Menor-Mayor") {
            orden = " ORDER BY p.precio ASC ";
        } else if (orden == "Mayor-Menor") {
            orden = " ORDER BY p.precio DESC ";
        }

        //CODIGO VARIABLE
        List<ProductoDTO> lista = new ArrayList<ProductoDTO>();
        String where = "";

        //mas codigo
        lista = ControlSistema.getInstancia().getControlProducto().getProductos(pagina, cantidad, where);

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
}
