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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try
        {
            User user = Authenticator.login(request.getParameter("netid"), request.getParameter("password"));

            if (user != null)
            {

                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                response.sendRedirect("/home");
            }

            else
                response.sendRedirect("/");
        }
        catch (Throwable theException)
        {
            System.out.println(theException);
        }
    }
}
