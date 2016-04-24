package com.se.pranita.termproject.controllers.courses;

import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.model.user.User;
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

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/course_info")
public class CourseInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");
        System.out.println(user);

        try {
            ArrayList<Course> courses = new ArrayList<>();
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM " + Constants.DATABASENAME + ".`courses` JOIN " + Constants.DATABASENAME + ".`course_user` ON " +
                    Constants.DATABASENAME + ".`courses`.`number` = " +
                    Constants.DATABASENAME + ".`course_user`.`number` AND " +
                    Constants.DATABASENAME + ".`courses`.`term` = " +
                    Constants.DATABASENAME + ".`course_user`.`term` AND " +
                    Constants.DATABASENAME + ".`courses`.`year` = " +
                    Constants.DATABASENAME + ".`course_user`.`year` WHERE " +
                    Constants.DATABASENAME + ".`course_user`.`netID`='" + user.getNetID() + "'";

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);

            while (rs.next()) {
                Course course = new Course();
                course.setName(rs.getString("name"));
                course.setNumber(rs.getString("number"));
                course.setDepartment(rs.getString("department"));
                course.setCourse_syllabus(rs.getString("course_syllabus"));
                course.setIns_office(rs.getString("ins_office_hour"));
                course.setIns_office(rs.getString("ins_office_hour"));
                course.setIns_office_hour(rs.getString("ins_office"));
                course.setTa_name(rs.getString("ta_name"));
                course.setTa_office(rs.getString("ta_office_hour"));
                course.setTa_office_hour(rs.getString("ta_office"));
                course.setTa_email(rs.getString("ta_email"));
                course.setTerm(rs.getString("term"));
                course.setYear(rs.getInt("year"));
                course.setInstructor(user.getNetID());
                course.setInstructor_name(user.getFirstName() + " " + user.getLastName());

                courses.add(course);

                System.out.println("JSON: " + course.toJSON());
            }


            System.out.println(courses);
            req.setAttribute("courses", courses);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/course_info.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
