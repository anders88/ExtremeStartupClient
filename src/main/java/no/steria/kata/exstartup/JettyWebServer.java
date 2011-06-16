package no.steria.kata.exstartup;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyWebServer {
	public static void main(String[] args) throws Exception {
		Server server = new Server(8081);
		server.addHandler(new WebAppContext("src/main/webapp", "/"));
		server.start();
		System.out.println("http://localhost:8081/");
	}
}
