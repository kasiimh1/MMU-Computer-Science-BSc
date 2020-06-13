package servlets;

import java.io.*;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import models.User;
import models.UserDAO;
import javax.servlet.*;
import javax.servlet.http.*;

/** 
 * @author Kasim Hussain - 15078165
 */

public class ServletTempPassword extends HttpServlet {	

	private static final long SerialVersionUID = 1L;

	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		/**
		 * set the current page as reset.jsp
		 */

		RequestDispatcher view = req.getRequestDispatcher("reset.jsp");
		view.forward(req, resp);		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserDAO userdao = new UserDAO();	

		/**
		 * get the username and password from the input tags save them to their own variables
		 */

		String uname = req.getParameter("username");		
		String pw = req.getParameter("password"); 
		String hash = "";

		// if the username we read in is one that's hardcoded below then we can allow the user to reset their password

		/* rewrote this so the account are not dynamic, it now pulls and checks them from the user input -> database */
		User user = userdao.getUser(uname);

		if (user != null) 
		{
			String currentUser = user.getUsername();

			if (uname.contentEquals(currentUser) == true)
			{	
				//now we convert it to SHA512 + salt hash and send it to be inserted into the database
				hash = DigestUtils.sha512Hex(pw + uname);

				//send the new password to be inserted into the database						
				user.setPassword(hash);				
				try {
					userdao.updateUser(user, uname);
				} catch (SQLException e) {
					e.printStackTrace();
				}	

				//if the update was successful then display this message on the web page
				String message = "Password Updated Successfully";

				//set the message attribute so it can be called in the .jsp file
				req.setAttribute("message", message);				
			}

			else {
				//if the user doesn't have an account we throw them this error message to be displayed on the web page
				String message = "You don't have an existing account";
				//set the message attribute so it can be called in the .jsp file
				req.setAttribute("message", message);
			}

			RequestDispatcher view = req.getRequestDispatcher("reset.jsp");
			view.forward(req, resp);		
		}	
	}
}