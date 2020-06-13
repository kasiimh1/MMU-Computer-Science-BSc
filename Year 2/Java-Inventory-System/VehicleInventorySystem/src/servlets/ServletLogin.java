package servlets;

import java.io.*;
import org.apache.commons.codec.digest.DigestUtils;
import javax.servlet.*;
import javax.servlet.http.*;
import models.User;
import models.UserDAO;

/** 
 * @author Kasim Hussain - 15078165
 */

public class ServletLogin extends HttpServlet {	

	private static final long SerialVersionUID = 1L;

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		/**
		 * set the current page as login.jsp
		 */

		RequestDispatcher view = req.getRequestDispatcher("login.jsp");
		view.forward(req, resp);	

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/**
		* get the username and password from the input tags save them to their own variables
		*/
		
		String uname = req.getParameter("username");
		String password = req.getParameter("password");

		/*
		 * Provides a way for tracking user's identity over more than one page, store and request information about that user (username and password)
		 */
		
		HttpSession session = req.getSession();			
		
		/* 
		 * use imported jar and make life easier with a simple command to hash the String
		 * rather than import the required libraries into my class and then working 
		 * out the MD5 of the inputed string
		 * 
		 * this just compresses what i need to do in more less two lines of code (one without the import) rather than about 10-15 (including imports)
		 * functionality is the same 
		 */
		
		String hash = DigestUtils.md5Hex(password);
		
		/* 
		 * checking if the password hashes from the MD5 we worked out above match
		 *  the hash we provide and user name are correct 
		 */
		
		UserDAO userdao = new UserDAO();		
		User checkpass = userdao.getUser(uname);	
		
		
		if (uname.equals("admin") || uname.equals("kasiim"))
		{
			String pass = checkpass.getPassword();				
			System.out.println("HASH: " + hash);
			System.out.println("Pass: " + pass);
		
			if (uname.equals("admin") && hash.equals(pass) || uname.equals("kasiim") && hash.equals(pass))
			{			 
				/* 
				 * if user name and password match, set the session variable to true
				 * redirect the user back to the home page
				 */

				resp.sendRedirect("home");
				session.setAttribute("loggedin", true);
				userdao.closeConnection();
			}
			else
			{			
				/* 
				 * if user name and password don't match, set the session variable to false
				 * redirect the user back to the login page so they can try to login again 
				 * or redirect back so the user can reset their password if they have a username that's registered to the database
				 */
				
				resp.sendRedirect("login");
				session.setAttribute("loggedin", false);
				userdao.closeConnection();

			}	
		}
		
		else
		{			
			/* 
			 * if user name and password don't match, set the session variable to false
			 * redirect the user back to the login page so they can try to login again 
			 * or redirect back so the user can reset their password if they have a username that's registered to the database
			 */
			
			resp.sendRedirect("login");
			session.setAttribute("loggedin", false);
		}	
	
	/*	
		RequestDispatcher view = req.getRequestDispatcher("login.jsp");
		view.forward(req, resp);*/		
	}
}