package com.blasco991.chatServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddMessage
 */
@WebServlet("/AddMessage")
public class AddMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ServletContext context = getServletContext();
		context.setAttribute("listMessage", Collections.synchronizedList(new ArrayList<com.blasco991.chatServlet.MyEntry<String, String>>()));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterMap().containsKey("author") && request.getParameterMap().containsKey("message")) {
			List<MyEntry<String, String>> listMessagge = (List<MyEntry<String, String>>) getServletContext()
					.getAttribute("listMessage");
			MyEntry<String, String> entry = new MyEntry<String, String>(request.getParameter("author"),
					request.getParameter("message"));
			listMessagge.add(entry);
		}
	}

}
