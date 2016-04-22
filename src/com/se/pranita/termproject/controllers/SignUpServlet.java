package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.Student;
import com.se.pranita.termproject.model.User;
import com.se.pranita.termproject.model.UserFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Pranita on 16/4/16.
 */
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        try {
            User.UserType type = User.UserType.getUserType(request.getParameter("role"));

            if(type == null){
                session.setAttribute("error", "Invalid User Type.");
                return;
//                throw new Exception("Invalid User Type");
            }

            User user = new UserFactory().getUser(type.getValue());
            user.setNetID(request.getParameter("netID"));
            user.setPassword(request.getParameter("password"));
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));

            if (user.getType() == User.UserType.STUDENT) {
                ((Student) user).setDepartment(request.getParameter("department"));
                ((Student) user).setProgram(request.getParameter("program"));
                ((Student) user).setStartTerm(request.getParameter("sem"));
                ((Student) user).setStartYear(Integer.parseInt(request.getParameter("year")));
            }

            if(user.save()) {
                response.sendRedirect("/");
            } else {
                session.setAttribute("error", "Check your data");
                response.sendRedirect("/error");
            }

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect("/error");
        }


    }
}
