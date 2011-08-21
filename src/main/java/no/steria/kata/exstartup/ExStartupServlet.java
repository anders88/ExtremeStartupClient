package no.steria.kata.exstartup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ExStartupServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(ExStartupServlet.class);
	
	private static final long serialVersionUID = 6315655194291010673L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String parameter = req.getParameter("q");
		String question = parameter;
		HttpSession session = req.getSession();
		Object amAttr = session.getAttribute("answerMachine");
		AnswerMachine answerMachine;
		if (amAttr instanceof AnswerMachine) {
			answerMachine = (AnswerMachine) amAttr;
		} else {
			answerMachine = new AnswerMachine();
			session.setAttribute("answerMachine", answerMachine);
		}
		String answer = answerMachine.answer(question);
		logger.debug("Q: '" + question + "' ? -> " + answer);
		writer.append(answer);
	}
}
