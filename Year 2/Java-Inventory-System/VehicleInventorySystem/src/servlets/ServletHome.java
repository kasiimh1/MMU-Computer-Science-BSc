package servlets;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import models.Vehicle;
import models.VehicleDAO;

/** 
 * @author Kasim Hussain - 15078165
 */

public class ServletHome extends HttpServlet {	
	
	private static final long SerialVersionUID = 1L;
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		VehicleDAO dao = new VehicleDAO();	
		
		//set the ArrayList to allCons which grabs all the information in the database for each car
		ArrayList<Vehicle> allCons = dao.getAllVehicles();		
		
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
		
		//set the ArrayList to allCons so we can call it within index.jsp via <c:forEach>		
		req.setAttribute("allCons", allCons);
		view.forward(req, resp);		
	}
}
