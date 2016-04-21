package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Pranita on 20/4/16.
 */
@WebServlet("/view_resources")
public class ViewResourcesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("doGet resources");
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");
        System.out.println(user);
    }
}
