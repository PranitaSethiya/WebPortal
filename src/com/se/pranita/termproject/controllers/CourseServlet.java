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
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pranita on 21/4/16.
 */
@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        course_num=a&course_name=a&sem=Fall&year=a&course_syllabus=&ins_office=&ins_office_hour=&ta_name=&ta_email=&ta_office=&ta_office_hour=
        HttpSession session = req.getSession(false);
        try {
            Course course = new Course();
            course.setNumber(req.getParameter("course_num"));
            course.setName(req.getParameter("course_name"));
            course.setDepartment(req.getParameter("department"));
            course.setTerm(req.getParameter("sem"));
            course.setYear(Integer.parseInt(req.getParameter("year")));
            course.setCourse_syllabus(req.getParameter("course_syllabus"));
            course.setIns_office(req.getParameter("ins_office"));
            course.setInstructor(((User) session.getAttribute("currentSessionUser")).getNetID());
            course.setIns_office_hour(req.getParameter("ins_office_hour"));
            course.setTa_name(req.getParameter("ta_name"));
            course.setTa_email(req.getParameter("ta_email"));
            course.setTa_office(req.getParameter("ta_office"));
            course.setTa_office_hour(req.getParameter("ta_office_hour"));

            boolean failed = false;
            if (course.save()) {
                if (course.saveRelation(course.getInstructor())) {
                    resp.sendRedirect("/courses");
                } else {
                    failed = true;
                }
            } else {
                failed = true;
            }

            if (failed) {
                session.setAttribute("error", "Invalid Data. Please check and try again!!");
                resp.sendRedirect("/error");
            }

        } catch (Exception ex) {
            session.setAttribute("error", ex.getMessage());
            resp.sendRedirect("/error");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");
        System.out.println(user);

        try {
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM " + Constants.DATABASENAME + ".`courses`";


            String query2 = "SELECT netID, firstName, lastName FROM " + Constants.DATABASENAME + ".`users` WHERE `type`='1'";
            Statement smt2 = conn.createStatement();
            ResultSet rs2 = smt2.executeQuery(query2);
            Map<String, String> nameMap = new HashMap<>();
            while (rs2.next()) {
                nameMap.put(rs2.getString("netID"), rs2.getString("firstName") + " " + rs2.getString("lastName"));
            }

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);


            ArrayList<Course> courses = new ArrayList<>();
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

                String query3 = "SELECT netID FROM " + Constants.DATABASENAME + ".`course_user` WHERE `number`='"
                        + course.getNumber() + "' AND `term`='" + course.getTerm() + "' AND `year`='" + course.getYear() + "'";
                Statement smt3 = conn.createStatement();
                ResultSet rs3 = smt3.executeQuery(query3);
                while (rs3.next()) {
                    course.setInstructor(rs3.getString("netID"));
                    course.setInstructor_name(nameMap.getOrDefault(course.getInstructor(), ""));
                }
                if (user.getType() == User.UserType.STUDENT)
                    course.setStatus(getUserStatus(conn, user.getNetID(), course.getNumber(), course.getTerm(), course.getYear()));

                courses.add(course);
            }

            System.out.println(courses);
            req.setAttribute("courses", courses);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/view_courses");
            rd.forward(req, resp);
//            resp.sendRedirect("/view_courses");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }

    private Course.CourseStatus getUserStatus(Connection conn, String netID, String number, String term, int year) throws SQLException {
        String query = "SELECT COUNT(*) AS num FROM " + Constants.DATABASENAME + ".`course_user` WHERE `netID`='" + netID + "' AND `number`='"
                + number + "' AND `term`='" + term + "' AND `year`='" + year + "'";
        Statement smt = conn.createStatement();
        ResultSet rs = smt.executeQuery(query);
        boolean enrolled = false;
        int termStatus = TermUtil.compareCurrentTerm(TermUtil.Term.getTerm(term), year);
        while (rs.next()) {
            if (rs.getInt("num") > 0) {
                enrolled = true;
            }
        }

        if (enrolled)
            return termStatus <= 0 ? Course.CourseStatus.ENROLLED : Course.CourseStatus.COMPLETED;
        else
            return (termStatus <= 0) ? Course.CourseStatus.UNENROLLED : Course.CourseStatus.GONE;
    }
}
