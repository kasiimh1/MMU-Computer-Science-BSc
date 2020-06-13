package models;

/**
 * @author Kasim Hussain - 15078165 
 */

public class Vehicle {

	/*
	 * instance variables
	 */
	private int vehicle_id;
	private String make;
	private String model;
	private int year;
	private int price;
	private String license_number;
	private String colour;
	private int number_doors;
	private String transmission; 
	private int mileage; 
	private String fuel_type;
	private int engine_size;
	private String body_style; 
	private String condition; 
	private String notes;	
	private String sold;

	/* getters */
	public int getVehicle_id() {
		return vehicle_id;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}

	public String getLicense_number() {
		return license_number;
	}

	public String getColour() {
		return colour;
	}

	public int getNumber_doors() {
		return number_doors;
	}

	public String getTransmission() {
		return transmission;
	}

	public int getMileage() {
		return mileage;
	}


	public String getFuel_type() {
		return fuel_type;
	}

	public int getEngine_size() {
		return engine_size;
	}

	public String getBody_style() {
		return body_style;
	}

	public String getSold() {
		return sold;
	}
	/* setters */

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}		

	public void setYear(int year) {
		this.year = year;
	}

	public void setPrice(int price) {
		this.price = price;
	}		

	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getCondition() {
		return condition;
	}

	public String getNotes() {
		return notes;
	}		

	public void setNumber_doors(int number_doors) {
		this.number_doors = number_doors;
	}			

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	public void setEngine_size(int engine_size) {
		this.engine_size = engine_size;
	}

	public void setBody_style(String body_style) {
		this.body_style = body_style;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setSold(String sold) {
		this.sold = sold;
	}

	/**
	 * toString that returns the values of the vehicle
	 */

	@Override
	public String toString() {
		return  "\n---------------------------" 
				+ "\nVehicle ID = " + vehicle_id + "\nVehicle Make = " + make + "\nVehicle Model = " + model + "\nRegistration Year = " + year
				+ " \nPrice = " + price + "\nLicense Number = "  + license_number + "\nColour = " + colour
				+ " \nNumber of Doors = " + number_doors + "\nTransmission = " + transmission + "\nMileage = " + mileage
				+ "\nFuel Type = " + fuel_type + "\nEngine Size = " + engine_size + "\nBody Style = " + body_style
				+ "\nCondition = " + condition + "\nNotes = " + notes + "\nSold = " + sold
				+ "\n---------------------------";
	}
}