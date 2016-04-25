package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.dao.UserDAO;
import com.se.pranita.termproject.model.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/view_student")
public class ViewStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User user = new UserDAO().getStudentById(req.getParameter("netID"));
            if (user == null) {
                throw new SQLException("Error retrieving data.");
            }
            req.setAttribute("user", user);

            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/view_student.jsp");
            rd.forward(req, resp);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            HttpSession session = req.getSession(false);
            session.setAttribute("error", ex.getMessage());
            resp.sendRedirect("/error");
        }
    }
}
