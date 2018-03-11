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
		
		
		webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");
		
		org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");
		
		
        // change the name of the war as needed.
        webapp.setWar("target/classes/p1.war");
        server.setHandler(webapp);

        server.start();
        server.join();
    }


}