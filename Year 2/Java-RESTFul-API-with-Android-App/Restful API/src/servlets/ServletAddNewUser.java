package servlets;

import java.io.*;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.codec.digest.DigestUtils;

import models.User;
import models.UserDAO;

/** 
 * @author Kasim Hussain - 15078165
 */

public class ServletAddNewUser extends HttpServlet {	

	private static final long SerialVersionUID = 1L;
	
	UserDAO dao = new UserDAO();	

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		

		/*
		 * load up addnew.jsp 
		 */
		//dao.closeConnection();
		RequestDispatcher view = req.getRequestDispatcher("addnewuser.jsp");
		view.forward(req, resp);	
	}

	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		/**
		 * make instance of UserDAO = dao
		 * read all the input tags via their names
		 * set them to variables
		 * create a new User
		 * pass the saved variables via setters
		 * create boolean done
		 * set done = dao.insert and pass in the User we created and set data to
		 * if done then redirect the web page to home.jsp
		 */

		Random rand = new Random();
		int salt = rand.nextInt();

		String firstname = (String) req.getParameter("firstname");	
		String surname = (String) req.getParameter("surname");	
		String username = (String) req.getParameter("username");	
		String password = (String) req.getParameter("password");	

		/* Convert to SHA512 hash to store the password in the SQL database */
		password = DigestUtils.sha512Hex(password + username);

		/* create a random SHA512 token based of the above parameters scrambled with a random salt int */ 
		String devtoken = DigestUtils.sha512Hex(firstname + username + surname + salt);

		User u = new User();	
		/* set the rest */
		//u.setId(id);
		u.setFirstname(firstname);
		u.setSurname(surname);
		u.setUsername(username);
		u.setUser_type(1);
		u.setPassword(password);
		u.setToken(devtoken);

		boolean done;
		try {
			done = dao.insertUser(u);
			System.out.println(done);
			if (done)
			{
				resp.sendRedirect("home");
				System.out.println("Successfully created a new User and inserted it into the SQL database");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
}
