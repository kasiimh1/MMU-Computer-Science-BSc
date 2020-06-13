package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import models.Vehicle;
import models.VehicleDAO;

/** 
 * @author Kasim Hussain - 15078165
 */

public class ServletAddNewVehicle extends HttpServlet {	

	private static final long SerialVersionUID = 1L;

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		/*
		 * load up addnew.jsp 
		 */
		
		RequestDispatcher view = req.getRequestDispatcher("addnew.jsp");
		view.forward(req, resp);	
	}

	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		/**
		 * make instance of VehicleDAO = dao
		 * read all the input tags via their names
		 * set them to variables
		 * create a new Vehicle
		 * pass the saved variables via setters
		 * create boolean done
		 * set done = dao.insert and pass in the vehicle we created and set data to
		 * if done then redirect the web page to home.jsp
		 */
		
		VehicleDAO dao = new VehicleDAO();		
		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id"));
		String make = (String) req.getParameter("make");	
		String model = (String) req.getParameter("model");	
		int year = Integer.valueOf(req.getParameter("year"));	
		int price = Integer.valueOf(req.getParameter("price"));	
		String license = (String) req.getParameter("license_number");	
		String colour = (String) req.getParameter("colour");	
		int doors = Integer.valueOf(req.getParameter("number_doors"));	
		String transmission = (String) req.getParameter("transmission");	
		int mileage = Integer.valueOf(req.getParameter("mileage"));	
		String fuel = (String) req.getParameter("fuel_type");	
		int engine = Integer.valueOf(req.getParameter("engine_size"));	
		String body = (String) req.getParameter("body_style");	
		String condition = (String) req.getParameter("condition");	
		String notes = (String) req.getParameter("notes");	
		String sold = (String) req.getParameter("sold");

		Vehicle c = new Vehicle();		
		c.setVehicle_id(vehicle_id);
		c.setMake(make);
		c.setModel(model);
		c.setYear(year);
		c.setPrice(price);					
		c.setLicense_number(license);
		c.setColour(colour);
		c.setNumber_doors(doors);
		c.setTransmission(transmission);
		c.setMileage(mileage);
		c.setFuel_type(fuel);
		c.setEngine_size(engine);
		c.setBody_style(body);
		c.setCondition(condition);
		c.setNotes(notes);	
		c.setSold(sold);

		boolean done;
		try {
			done = dao.insertVehicle(c);
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
