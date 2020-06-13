import java.util.ArrayList;
import java.util.Scanner;
import models.Vehicle;
import models.VehicleDAO;

/** 
 * @author Kasim Hussain - 15078165
 */

public class Controller {

	public static void main(String[] args) throws Exception {	

		int dataInt = 0;
		String dataString = "";

		VehicleDAO dao = new VehicleDAO();

		int option = 0;
		while (option != 6)
		{		
			System.out.println("\n--------------------------");
			System.out.println(" Vehicle Inventory System\n Choose from these options");
			System.out.println("--------------------------");
			System.out.println("1 - Retrieve all vehicles");
			System.out.println("2 - Search for vehicle");
			System.out.println("3 - Insert new vehicle into database");
			System.out.println("4 - Update existing vehicle details");
			System.out.println("5 - Delete vehicle from database");
			System.out.println("6 - Exit");
			System.out.print("Enter choice > ");

			/**
			 * Get the users option via the scanner utility which then passes that variable to the switch case 
			 * prints and invokes the matching case  		
			 */

			Scanner input = new Scanner(System.in);			
			option = input.nextInt();		
			switch (option)		
			{
			case 1: 	 

				ArrayList<Vehicle> getAllList = new VehicleDAO().getAllVehicles();

				//for every vehicle within the ArrayList print it to a new line
				for (Vehicle find : getAllList)
				{
					System.out.println(find);
				}				

				break;

			case 2:

				System.out.print("\nEnter the vehicles id: ");
				int vehicle_input = input.nextInt();	
				//print the found vehicle to console
				System.out.println(dao.getVehicle(vehicle_input));

				break;

			case 3:	 	

				/**
				 * If you enter a string without a space you may have to press the ENTER button twice as this implementation allows for strings with a space.
				 * A result of doing it this way may sometimes make the console output look out of place and not in a nice format.
				 */

				System.out.print("Enter vehicle id: ");
				int vehicle_id = input.nextInt();					
				System.out.print("Enter vehicle make: ");				
				String make = input.next();			
				input.nextLine();
				System.out.print("Enter vehicle model: ");	 			
				String model = input.next();;	
				input.nextLine();
				System.out.print("Enter vehicle year: ");
				int year = input.nextInt();	 	
				System.out.print("Enter vehicle price: ");
				int price = input.nextInt();				
				System.out.print("Enter vehicle license number: ");				
				String license = input.next();
				System.out.print("Enter vehicle colour: ");
				String colour = input.next();		
				System.out.print("Enter number of doors: ");
				int doors = input.nextInt();	
				System.out.print("Enter vehicle transmission type (Automatic, Manual, Semi-Auto): ");
				String transmission = input.next();				
				input.nextLine();
				System.out.print("Enter vehicle mileage: ");
				int mileage = input.nextInt();							
				System.out.print("Enter vehicle engine type (Petrol, Diesel, Hybrid, Electric): ");
				String fuel = input.next();		
				System.out.print("Enter engine size: ");
				int engine_size = input.nextInt();					
				System.out.print("Enter vehicle body style: ");				
				String body_style = input.next();	
				input.nextLine();
				System.out.print("Enter vehicle condition (e.g. new, good, fair): ");
				String condition = input.next();	
				input.nextLine();
				System.out.print("Enter any other vehicle notes (special features such as satnav): ");
				String notes = input.next();	
				input.nextLine();
				System.out.print("Enter sold status yes/no: ");
				String sold = input.next();					

				/**
				 * Create a new instance of Vehicle pass the new values on the variables that the user inputed above
				 * set them variables to the matching items within the Vehicle Object and then send that to the insertVehicle
				 * method in the VehicleDAO to insert it into the database
				 */

				Vehicle vehicle = new Vehicle();

				vehicle.setVehicle_id(vehicle_id);
				vehicle.setMake(make);
				vehicle.setModel(model);
				vehicle.setYear(year);
				vehicle.setPrice(price);					
				vehicle.setLicense_number(license);
				vehicle.setColour(colour);
				vehicle.setNumber_doors(doors);
				vehicle.setTransmission(transmission);
				vehicle.setMileage(mileage);
				vehicle.setFuel_type(fuel);
				vehicle.setEngine_size(engine_size);
				vehicle.setBody_style(body_style);
				vehicle.setCondition(condition);
				vehicle.setNotes(notes);	
				vehicle.setSold(sold);

				dao.insertVehicle(vehicle);			

				break;

			case 4: 

				System.out.print("Enter vehicle_id: ");
				int veh = input.nextInt();	

				Vehicle e = dao.getVehicle(veh);

				/**
				 * Create a new instance of Vehicle(); 
				 * get all the information for that vehicle via a getter and save that to the matching variable waiting to be updated 
				 */

				int vehicle_idUpdate = e.getVehicle_id();
				String makeUpdate = e.getMake();
				String modelUpdate = e.getModel();
				int yearUpdate = e.getYear();
				int priceUpdate = e.getPrice();
				String license_numberUpdate = e.getLicense_number();
				String colourUpdate = e.getColour();
				int number_doorsUpdate = e.getNumber_doors();
				String transmissionUpdate = e.getTransmission();
				int mileageUpdate = e.getMileage();
				String fuel_typeUpdate = e.getFuel_type();
				int engine_sizeUpdate = e.getEngine_size();
				String body_styleUpdate = e.getBody_style();
				String conditionUpdate = e.getCondition();
				String notesUpdate = e.getNotes();	
				String soldUpdate = e.getSold();

				System.out.print("Enter columns name you wish to edit data in: ");
				System.out.print("\nInsert like shown: vehicle_id, make, model, license_number, colour, transmission, fuel_type, body_style, condition, notes, year, price, number_doors, mileage, engine_size, sold\n");
				String column = input.next();	 				

				//check what column the user wishes to update Integer or String via scanning in the String of the column they want to update

				if (column.equalsIgnoreCase("make") || column.equalsIgnoreCase("model") || column.equalsIgnoreCase("license_number") || column.equalsIgnoreCase("colour") || column.equalsIgnoreCase("transmission") || column.equalsIgnoreCase("fuel_type") || column.equalsIgnoreCase("body_style") || column.equalsIgnoreCase("condition") || column.equalsIgnoreCase( "notes") || column.equalsIgnoreCase("sold"))
				{
					System.out.print("Enter " + column + " of type String: ");
					dataString = input.next();	
				}
				if (column.equalsIgnoreCase("vehicle_id") || column.equalsIgnoreCase("year") || column.equalsIgnoreCase("price") || column.equalsIgnoreCase("number_doors") || column.equalsIgnoreCase("mileage") || column.equalsIgnoreCase( "engine_size")) {
					System.out.print("Enter " + column + " of type Int: ");
					dataInt = input.nextInt();	
				}	

				/**				
				 * if it matches in the switch case it tells the program what String or Integer the new input represents in the instance of the Vehicle
				 * overwriting the previous value of that variable and then only changes that one
				 */

				switch (column)		
				{
				//string based
				case "make":					
					makeUpdate = dataString;
					break;

				case "model":
					modelUpdate = dataString;
					break;

				case "license_number":		
					license_numberUpdate = dataString;
					break;

				case "colour":	
					colourUpdate = dataString;
					break;

				case "transmission":
					transmission = dataString;
					break;					

				case "fuel_type":
					fuel_typeUpdate = dataString;
					break;

				case "body_style":
					body_style = dataString;
					break;

				case "condition":
					conditionUpdate = dataString;
					break;

				case "notes":	
					notesUpdate = dataString;
					break;

				case "sold":
					soldUpdate = dataString;
					break;

					//int based 					
				case "vehicle_id":
					vehicle_idUpdate = dataInt;
					break;

				case "year":
					yearUpdate = dataInt;
					break;

				case "price":
					priceUpdate = dataInt;
					break;					

				case "number_doors":
					number_doorsUpdate = dataInt;
					break;

				case "mileage":
					mileageUpdate = dataInt;
					break;

				case "engine_size":
					engine_sizeUpdate = dataInt;										
					break;

				default:
					break;
				}

				Vehicle send = new Vehicle();		
				send.setVehicle_id(vehicle_idUpdate);
				send.setMake(makeUpdate);
				send.setModel(modelUpdate);
				send.setYear(yearUpdate);
				send.setPrice(priceUpdate);					
				send.setLicense_number(license_numberUpdate);
				send.setColour(colourUpdate);
				send.setNumber_doors(number_doorsUpdate);
				send.setTransmission(transmissionUpdate);
				send.setMileage(mileageUpdate);
				send.setFuel_type(fuel_typeUpdate);
				send.setEngine_size(engine_sizeUpdate);
				send.setBody_style(body_styleUpdate);
				send.setCondition(conditionUpdate);
				send.setNotes(notesUpdate);	
				send.setSold(soldUpdate);

				//send the full vehicle object to be updated via the updateVehicle

				dao.updateVehicle(send, veh);	

				break;

			case 5: 	 

				/**
				 * Scan the Integer value that the user wants to delete from the database,
				 * pass that to the deleteVehicle method				 * 
				 */
				System.out.print("\nEnter the vehicles id: ");
				int vehicle_delete = input.nextInt(); 			
				dao.deleteVehicle(vehicle_delete);		

				break;		

			case 6: 

				/**
				 * Call System.exit(0) to terminate the program
				 */

				System.out.println("\nExiting");
				System.exit(0);
				break;

			}
		}
	}
}