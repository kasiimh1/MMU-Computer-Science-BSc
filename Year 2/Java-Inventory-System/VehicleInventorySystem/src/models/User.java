package models;


/**
 * @author Kasim Hussain - 15078165 
 */

public class User {
	
	/*
	 * instance variables
	 */
	
	private int id;
	private String firstname;
	private String surename;
	private String username;
	private String password;
	private int user_type;
		
	/* getters */
	public int getId() {
		return id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getSurename() {
		return surename;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getUser_type() {
		return user_type;
	}
	
	/* setters */
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setSurename(String surename) {
		this.surename = surename;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
	/**
	 * toString that returns the values of the user
	 */
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", surename=" + surename + ", username=" + username
				+ ", password=" + password + ", user_type=" + user_type + "]";
	}
}
