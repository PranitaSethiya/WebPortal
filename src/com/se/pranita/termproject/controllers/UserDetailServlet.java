package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.*;
import com.se.pranita.termproject.utils.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/phd_students")
public class UserDetailServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
//        User user = (User) session.getAttribute("currentSessionUser");
//        System.out.println(user);

        try {
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM " + Constants.DATABASENAME + ".`users` WHERE `program`='Ph.D'";

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);

            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new Student();
                user.setNetID(rs.getString("netID"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setType(User.UserType.STUDENT);

                ((Student) user).setStartTerm(rs.getString("startTerm"));
                ((Student) user).setStartYear(rs.getInt("startYear"));
                ((Student) user).setProgram(rs.getString("program"));
                ((Student) user).setDepartment(rs.getString("department"));
                System.out.println(user.toJSON());
                users.add(user);
            }

            System.out.println(users);
            req.setAttribute("users", users);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/phd_students.jsp");
            rd.forward(req, resp);
//            resp.sendRedirect("/view_courses");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }
}
