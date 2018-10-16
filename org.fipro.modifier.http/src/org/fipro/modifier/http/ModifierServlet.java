package org.fipro.modifier.http;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fipro.modifier.api.StringModifier;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(service = Servlet.class, property = "osgi.http.whiteboard.servlet.pattern=/modify", scope = ServiceScope.PROTOTYPE)
public class ModifierServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Reference
	private volatile List<StringModifier> modifier;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String input = req.getParameter("value");
		if (input == null) {
			throw new IllegalArgumentException("input can not be null");
		}

		resp.setContentType("text/html");
		resp.getWriter().write("<html><body>Result is " 
				+ modifier.stream().map(x -> "<p>" + x.modify(input) + "</p>").collect(Collectors.joining()) 
				+ "</body></html>");
	}

}