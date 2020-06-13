package servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import models.*;

public class ServletShowKey extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserDAO userdao = new UserDAO();		
	PrintWriter writer;

	@Override 	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

		HttpSession session = req.getSession();			

		if (session.getAttribute("currentUser") != null)
		{
		String user = (String)session.getAttribute("currentUser");
		
		System.out.println("[Found Session User] " + user);

		/* find the token for the stored session attribute of the current user */
		User output = userdao.findToken(user);
		/* set the token to be displayed */
		req.setAttribute("user", output.tokenToString());
		RequestDispatcher view = req.getRequestDispatcher("showkey.jsp");
		view.forward(req, resp);		
		}
		else 
		{
			RequestDispatcher view = req.getRequestDispatcher("login.jsp");
			view.forward(req, resp);
		}
	}
}
