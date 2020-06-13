package servlets;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import models.*;

public class ServletAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	VehicleDAO dao = new VehicleDAO();
	UserDAO userdao = new UserDAO();		
	Gson gson = new Gson();
	PrintWriter writer;

	@Override 	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		String dev = (String) req.getParameter("access_token");	
		String check = userdao.getToken(dev).tokenToString();

		/* Debug Only */
		System.out.println("[API POST CALL - CLIENT TOKEN] " + dev);
		System.out.println("[API POST CALL - DATABASE TOKEN] " + check);

		if (dev.contentEquals(check))
		{
			writer = resp.getWriter();
			resp.setContentType("text/html);charset=UTF-8");

			Vehicle c = gson.fromJson(req.getParameter("json"), Vehicle.class);		
			System.out.println(c);

			boolean inserted = false;

			try 
			{
				inserted = dao.insertVehicle(c);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

			if (inserted) {
				writer.write("New Vehicle Inserted");
				writer.close();

			} else {
				writer.write("error");

			}
		}
	}


	@Override 	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String dev = (String) req.getParameter("access_token");	
		String check = userdao.getToken(dev).tokenToString();

		/* Debug Only */
		System.out.println("[API DELETE CALL - CLIENT TOKEN] " + dev);
		System.out.println("[API DELETE CALL - DATABASE TOKEN] " + check);

		if (dev.contentEquals(check))
		{
			writer = resp.getWriter();
			resp.setContentType("text/html);charset=UTF-8");

			//here we can do the next check and see if the hash found in the database matches any that we have got in the request
			int id = Integer.valueOf(req.getParameter("vehicle_id"));		
			System.out.println("Removing Vehicle with vehicle_id = " + id);		
			boolean inserted = false;				
			inserted = dao.deleteVehicle(id);		

			if (inserted) {
				writer.write("Vehicle Removed");
			} else {
				writer.write("error");
			}
			writer.close();
		}
	}

	@Override 	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String dev = (String) req.getParameter("access_token");	
		String check = userdao.getToken(dev).tokenToString();

		/* Debug Only */
		System.out.println("[API GET CALL - CLIENT TOKEN] " + dev);
		System.out.println("[API GET CALL - DATABASE TOKEN] " + check);

		if (dev.contentEquals(check))
		{
			writer = resp.getWriter();
			resp.setContentType("text/html);charset=UTF-8");

			ArrayList<Vehicle> allcon = dao.getAllVehicles();
			resp.setContentType("application/json");
			writer = resp.getWriter();
			String conJSON = gson.toJson(allcon);
			writer.write(conJSON);
			writer.close();
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String dev = (String) req.getParameter("access_token");	
		String check = userdao.getToken(dev).tokenToString();

		/* Debug Only */
		System.out.println("[API PUT CALL - CLIENT TOKEN] " + dev);
		System.out.println("[API PUT CALL - DATABASE TOKEN] " + check);

		if (dev.contentEquals(check))
		{
			writer = resp.getWriter();
			resp.setContentType("text/html);charset=UTF-8");

			Vehicle c = gson.fromJson(req.getParameter("json"), Vehicle.class);		
			System.out.println(c);

			boolean inserted = false;

			try 
			{
				inserted = dao.updateVehicle(c, c.getVehicle_id());
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

			if (inserted) {
				writer.write("New Vehicle Updated");
			} else {
				writer.write("error");
			}
			writer.close();
		}
	}
}


