package com.se.pranita.termproject.controllers;

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

        System.out.println("in doGet");

        Connection conn = ConnectionHandler.getConnection();
        try {
            Statement statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }

//        try
//        {
//            User user = new User();
//            user.setNetID(request.getParameter("uname"));
//            user.setPassword(request.getParameter("pass"));
//
//            user = UserAuthentication.login(user);
//
//            if (user.isValid())
//            {
//
//                HttpSession session = request.getSession(true);
//                session.setAttribute("currentSessionUser", user);
//                response.sendRedirect("dashBoard.jsp");
//            }
//
//            else
//                response.sendRedirect("badLogin.jsp");
//        }
//        catch (Throwable theException)
//        {
//            System.out.println(theException);
//        }
    }
}
