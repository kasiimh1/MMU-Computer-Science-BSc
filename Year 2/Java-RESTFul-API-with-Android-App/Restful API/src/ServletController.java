
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;

/** 
 * @author Kasim Hussain - 15078165
 */

public class ServletController {	

	public static void main(String[] args) throws Exception {

		/**
		 * set the new server port
		 * set the resource base of the web app
		 * set url for context path
		 */ 	

		Server server = new Server(8005);
		WebAppContext ctx = new WebAppContext();
		ctx.setResourceBase("webapp");
		ctx.setContextPath("/vehicledb");

		//config 
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
		ClassList classlist = ClassList.setServerDefault(server);
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

		//mappings for each .jsp page in the webapp folder
		ctx.addServlet("servlets.ServletHome", "/home");
		ctx.addServlet("servlets.ServletAddNewVehicle", "/addnew");
		ctx.addServlet("servlets.ServletDeleteVehicle", "/delete");
		ctx.addServlet("servlets.ServletUpdateVehicle", "/update");
		ctx.addServlet("servlets.ServletLogin", "/login");
		ctx.addServlet("servlets.ServletLogout", "/logout");
		ctx.addServlet("servlets.ServletSearch", "/search");
		ctx.addServlet("servlets.ServletTempPassword", "/reset");
		//add api mapping
		ctx.addServlet("servlets.ServletAPI", "/api");
		ctx.addServlet("servlets.ServletAddNewUser", "/addnewuser");
		ctx.addServlet("servlets.ServletShowKey", "/showkey");
		//setting the handler and starting server
		server.setHandler(ctx);
		server.start();
		server.join();		

	}
}
