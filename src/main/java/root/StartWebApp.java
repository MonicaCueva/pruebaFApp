package root;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartWebApp {
    public static void main(String[] args) throws Exception {
        String portStr = System.getenv("PORT");
        int port = (portStr == null) ? 8085 : Integer.parseInt(portStr);
        Server server = new Server(port);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        // change the name of the war as needed.
        webapp.setWar("target/classes/p1.war");
		
		webapp.setAttribute("javax.servlet.context.tempdir",scratchDir);
		ClassLoader jspClassLoader = new URLClassLoader(new URL[0], this.getClass().getClassLoader());
		webapp.setClassLoader(jspClassLoader);
		ServletHolder holderJsp = new ServletHolder("jsp",JspServlet.class);
		holderJsp.setInitOrder(0);
		ServletHolder holderDefault = new ServletHolder("default",DefaultServlet.class);
		holderDefault.setInitParameter("resourceBase",baseUri.toASCIIString());
		holderDefault.setInitParameter("dirAllowed","true");
		webapp.addServlet(holderDefault,"/");

        server.setHandler(webapp);

        server.start();
        server.join();
    }


}