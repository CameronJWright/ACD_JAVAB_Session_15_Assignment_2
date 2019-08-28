package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WebDestruction
 */
@WebServlet("/WebDestruction")
public class WebDestruction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WebDestruction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		
		out.println("[<a href=\"WebDestruction\">Reload</a>] \r\n"
				+ "	 [<a href=\"WebDestruction?destruction=true\">Destroy Session</a>]\r\n");

		Enumeration params = request.getParameterNames();
		if (params.hasMoreElements()) {
			boolean destruction = Boolean.parseBoolean(request.getParameter("destruction"));
			if (destruction == true)
				session.invalidate();
			session = request.getSession();
			out.println("<h1>Session created Successfully</h1>"
					+ "  Look at the OC4J Console to see whether an HttpSessionEvent was invoked");
			System.out.println("Session created successfully");
			
		}

		if (session.isNew() || session.getAttribute("count") == null) {
			int count = 0;
			session.setAttribute("count", 0);
		} else {
			int count = (Integer) session.getAttribute("count");
			count++;
			session.setAttribute("count", count++);
		}
		out.println("<h2>Session Data:</h2>");
		out.println("New Session: " + session.isNew() + "<br/>");
		out.println("Session ID: " + session.getId() + "<br/>");
		out.println("Creation Time: " + dateFormat.format(session.getCreationTime()) + "<br/>");
		out.println("Last Accessed Time: " + dateFormat.format(session.getLastAccessedTime()) + "<br/>");
		out.println("Number of Accesses: " + session.getAttribute("count"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
