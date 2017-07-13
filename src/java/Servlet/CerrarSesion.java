package Servlet;


import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;

public class CerrarSesion extends HttpServlet {  	

	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req,res);	 		
	}
	
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.getSession().invalidate();
		  
		ServletContext sc = getServletConfig().getServletContext();
		RequestDispatcher rdNext = sc.getRequestDispatcher("/login");
		rdNext.forward(req, res); 
	}

}
