package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.Resource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Pranita on 20/4/16.
 */
public class CreateResourceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        try {
        Resource resource = new Resource();
        resource.setName(req.getParameter("resource_name"));
        resource.setType(req.getParameter("resource_type"));
        resource.setInfo(req.getParameter("resource_additional"));
        if(resource.save()) {
            response.sendRedirect("/ReserveResource");
        } else {
            session.setAttribute("error", "Invalid Resource Data. Please check and try again!!");
            response.sendRedirect("/error");
        }

        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect("/error");
        }
    }
}
