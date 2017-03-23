package com.blasco991.chatServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListMessages
 */
@WebServlet("/ListMessages")
public class ListMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		List<MyEntry<String, String>> list = (List<MyEntry<String, String>>) context.getAttribute("listMessage");

		if (list != null) {
			synchronized (list) {
				int limit = request.getParameterMap().containsKey("howmany")
						? Integer.parseInt(request.getParameter("howmany")) : list.size();
				int i = 0;
				response.setContentType("text/xml;charset=UTF-8");
				StringBuilder stringBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				stringBuilder.append("<messages>");

				for (MyEntry<String, String> entry : list) {
					stringBuilder.append("<message>");
					stringBuilder.append("<index>").append(i++).append("</index>");
					stringBuilder.append("<author>").append(entry.getKey()).append("</author>");
					stringBuilder.append("<text>").append(entry.getValue()).append("</text>");
					stringBuilder.append("</message>");
					if (i == limit)
						break;
				}
				stringBuilder.append("</messages>");
				response.getWriter().print(stringBuilder.toString());

			}
		} else {
			response.getWriter().println("List messages empty!");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
