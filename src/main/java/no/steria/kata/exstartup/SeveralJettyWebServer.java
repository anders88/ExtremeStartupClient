package no.steria.kata.exstartup;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class SeveralJettyWebServer {
	public static void main(String[] args) throws Exception {
		start(8081);
		start(8082);
		start(8083);
		start(8084);
		start(8085);
		start(8086);
		start(8087);
		start(8088);
		start(8089);
	}

	private static void start(int port) throws Exception {
		Server server = new Server(port);
		server.addHandler(new WebAppContext("src/main/webapp", "/"));
		server.start();
		System.out.println("http://localhost:" + port + "/");
	}
}
