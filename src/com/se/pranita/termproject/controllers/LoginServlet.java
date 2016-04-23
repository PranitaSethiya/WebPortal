package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.Authenticator;
import com.se.pranita.termproject.model.ConnectionHandler;
import com.se.pranita.termproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Pranita on 14/4/16.
 */
@WebServlet(name = "com.se.pranita.termproject.controllers.LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            User user = Authenticator.login(request.getParameter("netid"), request.getParameter("password"));
            HttpSession session = request.getSession(false);
            if (user != null)
            {
                session.setAttribute("currentSessionUser", user);
                response.sendRedirect("/home");
            }
            else {
                session.setAttribute("error", "Invalid Username or Password");
                response.sendRedirect("/error");
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
