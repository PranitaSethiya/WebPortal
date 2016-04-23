package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.*;
import com.se.pranita.termproject.utils.Constants;
import com.se.pranita.termproject.utils.TermUtil;

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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/view_student")
public class ViewStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Statement stmt;
        String searchQuery = "SELECT * FROM " + Constants.DATABASENAME + ".`users` WHERE netID='" + req.getParameter("netID") + "'";
        try {

            Connection con = ConnectionHandler.getConnection();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(searchQuery);

            if (rs.next()) {
                User user = new Student();
                user.setNetID(req.getParameter("netID"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                ((Student)user).setDepartment(rs.getString("department"));
                ((Student)user).setStartYear(rs.getInt("startYear"));
                ((Student)user).setStartTerm(rs.getString("startTerm"));
                ((Student)user).setProgram(rs.getString("program"));

                req.setAttribute("user", user);

                String courseQuery = "SELECT * FROM " + Constants.DATABASENAME + ".`course_user` WHERE netID='" + req.getParameter("netID") + "'";
                stmt = con.createStatement();
                ResultSet rs2 = stmt.executeQuery(courseQuery);
                ArrayList<Course> courses = new ArrayList<>();
                while(rs2.next()){
                    String courseNameQuery = "SELECT name FROM " + Constants.DATABASENAME + ".`courses` WHERE number='" + rs2.getString("number") + "'" +
                            " AND term='" + rs2.getString("term") + "'" +
                    " AND year=" + rs2.getInt("year") + "";
                    stmt = con.createStatement();
                    ResultSet rs3 = stmt.executeQuery(courseNameQuery);
                    while(rs3.next()){
                        Course course = new Course();
                        course.setNumber(rs2.getString("number"));
                        course.setTerm(rs2.getString("term"));
                        course.setYear(rs2.getInt("year"));
                        course.setName(rs3.getString("name"));

                        course.setStatus(TermUtil.compareCurrentTerm(TermUtil.Term.getTerm(course.getTerm()),
                                course.getYear()) <= 0 ? Course.CourseStatus.ENROLLED : Course.CourseStatus.COMPLETED);
                        courses.add(course);
                    }
                }

                user.setCourses(courses);

                RequestDispatcher rd = getServletContext()
                        .getRequestDispatcher("/view_student.jsp");
                rd.forward(req, resp);

            } else {
                session.setAttribute("error", "Error retrieving user.");
                resp.sendRedirect("/error");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            session.setAttribute("error", ex.getMessage());
            resp.sendRedirect("/error");
        }
    }
}
