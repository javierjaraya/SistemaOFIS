/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Dto.CarroCompraDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AdministrarComprasCliente extends HttpServlet {

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
                if (request.getParameter("accion").equals("GUARDAR")) {
                    guardar(request, response);
                } else if (request.getParameter("accion").equals("AGREGAR")) {
                    agregar(request, response);
                } else if (request.getParameter("accion").equals("BORRAR")) {
                    borrar(request, response);
                } else if (request.getParameter("accion").equals("OBTENER_TOTAL_CARRO")) {
                    obtenerTotalCarro(request, response);
                } else if (request.getParameter("accion").equals("OBTENER_CARRO")) {
                    obtenerCarro(request, response);
                } else if (request.getParameter("accion").equals("OBTENER_CARRO_CONFIRMACION")) {
                    obtenerCarroConfirmacion(request, response);
                } else if (request.getParameter("accion").equals("VACIAR_CARRO")) {
                    vaciarCarro(request, response);
                }
            } else {
                pagina = "/web/administrarMisCompras.jsp";
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

    private void guardar(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void agregar(HttpServletRequest req, HttpServletResponse res) {
        CarroCompraDTO carrito;
        if(req.getSession().getAttribute("carrito") == null){
            carrito = new CarroCompraDTO();
            req.getSession().setAttribute("carrito", carrito);
        }else{
            carrito = (CarroCompraDTO) req.getSession().getAttribute("carrito");
        }
        
        int idProducto = Integer.parseInt(req.getParameter("idProducto"));
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        
        
    }

    private void borrar(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void obtenerTotalCarro(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void obtenerCarroConfirmacion(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void vaciarCarro(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void obtenerCarro(HttpServletRequest request, HttpServletResponse response) {
        
    }
    

}
