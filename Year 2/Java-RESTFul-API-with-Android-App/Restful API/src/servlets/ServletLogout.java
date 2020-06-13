package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** 
 * @author Kasim Hussain - 15078165
 */

public class ServletLogout extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		/*
		 * set the current page as logout.jsp
		 */

		RequestDispatcher view = req.getRequestDispatcher("logout.jsp");
		view.forward(req, resp);

		HttpSession session = req.getSession();

		/* 
		 * user is requesting to log out of the server, set the session variable to false
		 * redirect the user back to the home page
		 */

		session.setAttribute("loggedin", false);		
	}
}