package no.steria.kata.exstartup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExStartupServer extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.append("Anders");
		System.out.println("Ping");
		Object question = req.getParameter("q");
		if (question != null) {
			System.out.println("Question (" + question.getClass() + ") : " + question.toString());
		} else {
			System.out.println("NULL");
		}
	}
}
