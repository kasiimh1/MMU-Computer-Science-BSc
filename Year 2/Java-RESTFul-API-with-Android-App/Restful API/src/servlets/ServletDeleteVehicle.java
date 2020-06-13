package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import models.VehicleDAO;

/** 
 * @author Kasim Hussain - 15078165
 */

public class ServletDeleteVehicle extends HttpServlet {	

	private static final long SerialVersionUID = 1L;

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		/*
		 * load up delete.jsp 
		 */

		RequestDispatcher view = req.getRequestDispatcher("delete.jsp");
		view.forward(req, resp);		
	}

	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		/**
		 * read input via req.getParameter
		 * make instance of VehicleDAO = dao
		 * create boolean done
		 * set done = dao.deleteVehicle and pass in the Integer we read from getParameter
		 * if done then redirect the web page to home.jsp
		 */

		VehicleDAO dao = new VehicleDAO();		
		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id"));

		boolean done;
		try {
			done = dao.deleteVehicle(vehicle_id);
			System.out.println(done);
			if (done)
			{
				resp.sendRedirect("home");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
}

