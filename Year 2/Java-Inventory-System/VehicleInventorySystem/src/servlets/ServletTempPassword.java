package servlets;

import java.io.*;
import java.sql.SQLException;
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

		UserDAO dao = new UserDAO();
		
		/**
		* get the username and password from the input tags save them to their own variables
		*/
		
		String uname = req.getParameter("username");		
		String pw = req.getParameter("password"); 
		String hash = "";
		
		// if the username we read in is one that's hardcoded below then we can allow the user to reset their password

		if (uname.equals("admin") || uname.equals("kasiim") == true) {
			
			//now we convert it to MD5 hash and send it to be inserted into the database
			hash = DigestUtils.md5Hex(pw);
			//send the new password to be inserted into the database
			User send = new User();		
			send.setPassword(hash);				
			try {
				dao.updateUser(send, uname);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
			//if the update was successful then display this message on the web page
			String message = "Password Updated Successfully";
			
			//set the message attribute so it can be called in the .jsp file
			req.setAttribute("message", message);
			
			//close the connection otherwise we get file is currently locked
			dao.closeConnection();
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