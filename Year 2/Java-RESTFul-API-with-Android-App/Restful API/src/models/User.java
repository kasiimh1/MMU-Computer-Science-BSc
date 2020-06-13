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
	private String surname;
	private String username;
	private String password;	
	private String token;
	private int user_type;

	/* getters */
	public int getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
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

	public String getToken() {
		return token;
	}

	/* setters */

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * toString that returns the values of the user
	 */

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", surname=" + surname + ", username=" + username
				+ ", password=" + password + ", token=" + token + ", user_type=" + user_type + "]";
	}

	public String tokenToString() {
		return token;
	}
}
