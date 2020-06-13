package servlets;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;
import models.Vehicle;
import models.VehicleDAO;

public class ServletSearch extends HttpServlet {

	private static final long SerialVersionUID = 1L;

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		VehicleDAO dao = new VehicleDAO();	

		//set the ArrayList to allCons which grabs all the information in the database for each car
		ArrayList<Vehicle> allCons = dao.getAllVehicles();	

		//create a new ArrayList so we can store the items that need displaying in it
		ArrayList<Vehicle> newList = new ArrayList<Vehicle>();


		//convert the searched string all to one case and set it to a String variable 
		String stringCheck = req.getParameter("search").toLowerCase();	

		//set it to a String variable where we can find out what option the user has selected
		String optionSelected = req.getParameter("option");


		//if the option selected string matches it runs this part of the code
		if (optionSelected.equals("make") == true)
		{	
			//iterate through the ArrayList 
			for (Vehicle find : allCons)
			{
				//if the string converted to lower case matches add this item to the new ArrayList
				if (find.getMake().toLowerCase().contains(stringCheck) == true)
				{
					newList.add(find);			
				}	
				//set the new list to allCons
				allCons = newList;
				//set the ArrayList to allCons so we can call it within index.jsp via <c:forEach>		
				req.setAttribute("allCons", allCons);
			}
		}	

		//if the option selected string matches it runs this part of the code
		if (optionSelected.equals("model") == true)
		{	
			//iterate through the ArrayList 
			for (Vehicle find : allCons)
			{				
				//if the string converted to lower case matches add this item to the new ArrayList
				if (find.getModel().toLowerCase().contains(stringCheck) == true)
				{
					newList.add(find);			
				}	
				//set the new list to allCons
				allCons = newList;
				//set the ArrayList to allCons so we can call it within index.jsp via <c:forEach>		
				req.setAttribute("allCons", allCons);
			}
		}	

		//if the option selected string matches it runs this part of the code
		if (optionSelected.equals("price") == true)
		{					
			//this is a set to a type Integer because getPrice is of type int otherwise we cannot compare the two
			int intCheck = Integer.valueOf(req.getParameter("search"));
			//iterate through the ArrayList 
			for (Vehicle find : allCons)
			{
				//if the Integer is less than and equal to the inputed number than add this item to the new ArrayList
				if (intCheck >= find.getPrice())
				{
					newList.add(find);			
				}				
				//set the new list to allCons	
				allCons = newList;
				//set the ArrayList to allCons so we can call it within index.jsp via <c:forEach>		
				req.setAttribute("allCons", allCons);
			}
		}	

		RequestDispatcher view = req.getRequestDispatcher("search.jsp");
		view.forward(req, resp);			
	}


	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		RequestDispatcher view = req.getRequestDispatcher("search.jsp");
		view.forward(req, resp);

	}
}


