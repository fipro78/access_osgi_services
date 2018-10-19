package org.fipro.modifier.http;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.fipro.modifier.api.StringModifier;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component(
    service=Servlet.class,
    property= "osgi.http.whiteboard.servlet.pattern=/modify",
    scope=ServiceScope.PROTOTYPE)
public class ModifierServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
 
    @Reference
    private volatile List<StringModifier> modifier;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 
        String input = req.getParameter("value");
        if (input == null) {
            throw new IllegalArgumentException("input can not be null");
        }
        
        resp.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(
            		modifier.stream().map(x -> x.modify(input)).collect(Collectors.toList()));
            resp.getWriter().write(json);
        } catch (JsonProcessingException e) {
            resp.getWriter().write(objectMapper.writeValueAsString(e));
        }
    }
    
}