package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.*;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.user.Student;
import com.se.pranita.termproject.model.user.User;
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

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/view_student")
public class ViewStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Statement stmt;
        String query = "SELECT * FROM " + Constants.DATABASENAME + ".`courses` JOIN " + Constants.DATABASENAME + ".`course_user` ON " +
                Constants.DATABASENAME + ".`courses`.`number` = " +
                Constants.DATABASENAME + ".`course_user`.`number` AND " +
                Constants.DATABASENAME + ".`courses`.`term` = " +
                Constants.DATABASENAME + ".`course_user`.`term` AND " +
                Constants.DATABASENAME + ".`courses`.`year` = " +
                Constants.DATABASENAME + ".`course_user`.`year` " +
                " JOIN " + Constants.DATABASENAME + ".`users` ON " +
                Constants.DATABASENAME + ".`users`.`netID` = " +
                Constants.DATABASENAME + ".`course_user`.`netID`" +
                " WHERE " +
                Constants.DATABASENAME + ".`users`.`netID`='" + req.getParameter("netID") + "'";
        try {

            Connection con = ConnectionHandler.getConnection();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                User user = new Student();
                user.setNetID(req.getParameter("netID"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                ((Student)user).setDepartment(rs.getString("department"));
                ((Student)user).setStartYear(rs.getInt("startYear"));
                ((Student)user).setStartTerm(rs.getString("startTerm"));
                ((Student)user).setProgram(rs.getString("program"));

                ArrayList<Course> courses = new ArrayList<>();

                Course course_1 = new Course();
                course_1.setNumber(rs.getString("number"));
                course_1.setTerm(rs.getString("term"));
                course_1.setYear(rs.getInt("year"));
                course_1.setName(rs.getString("name"));

                course_1.setStatus(TermUtil.compareCurrentTerm(TermUtil.Term.getTerm(course_1.getTerm()),
                        course_1.getYear()) <= 0 ? Course.CourseStatus.ENROLLED : Course.CourseStatus.COMPLETED);
                courses.add(course_1);

                while(rs.next()){
                        Course course = new Course();
                        course.setNumber(rs.getString("number"));
                        course.setTerm(rs.getString("term"));
                        course.setYear(rs.getInt("year"));
                        course.setName(rs.getString("name"));

                        course.setStatus(TermUtil.compareCurrentTerm(TermUtil.Term.getTerm(course.getTerm()),
                                course.getYear()) <= 0 ? Course.CourseStatus.ENROLLED : Course.CourseStatus.COMPLETED);
                        courses.add(course);

                }

                user.setCourses(courses);

                req.setAttribute("user", user);

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
