package com.se.pranita.termproject.controllers;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.se.pranita.termproject.model.ConnectionHandler;
import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.model.User;
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
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pranita on 21/4/16.
 */
@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("/courses doPost");

        if(req.getParameter("action") != null){
            if(req.getParameter("action").equalsIgnoreCase("drop") || req.getParameter("action").equalsIgnoreCase("enroll"))
                enroll(req, resp);
            else if(req.getParameter("action").equalsIgnoreCase("update"))
                update(req, resp);
            return;
        }

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

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        System.out.println("Here in update");
//        Enumeration<String> vars = req.getParameterNames();
//        while(vars.hasMoreElements())
//            System.out.println(vars.nextElement());
        PrintWriter out = resp.getWriter();
        try {
            Connection conn = ConnectionHandler.getConnection();
            System.out.println("number: " + req.getParameter("number"));
            System.out.println("term: " + req.getParameter("term"));
            System.out.println("year: " + req.getParameter("year"));

            String number = (req.getParameter("number"));
            String term = req.getParameter("term");
            int year = Integer.parseInt(req.getParameter("year"));

            String query = "UPDATE " + Constants.DATABASENAME +
                        ".`courses` SET department=?, course_syllabus=?, ins_office_hour=?, ins_office=?, ta_name=?, ta_office_hour=?, ta_office=?, ta_email=? " +
                        "WHERE number=? AND term=? AND year=?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, req.getParameter("department"));
            ps.setString(2, req.getParameter("course_syllabus"));
            ps.setString(3, req.getParameter("ins_office_hour"));
            ps.setString(4, req.getParameter("ins_office"));
            ps.setString(5, req.getParameter("ta_name"));
            ps.setString(6, req.getParameter("ta_office_hour"));
            ps.setString(7, req.getParameter("ta_office"));
            ps.setString(8, req.getParameter("ta_email"));

            ps.setString(9, number);
            ps.setString(10, term);
            ps.setInt(11, year);

//            System.out.println("Update Course: " + ps.toString());
            ps.executeUpdate();
            conn.commit();
            ps.close();
            conn.close();

            out.print("success");
        }catch (SQLException ex){
            ex.printStackTrace();
            out.print("error");
        }
    }

    private void enroll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        System.out.println("Here in enroll");
        PrintWriter out = resp.getWriter();
        try {
            Connection conn = ConnectionHandler.getConnection();

            String query;
            if(req.getParameter("action").equalsIgnoreCase("enroll"))
                query = "INSERT INTO " + Constants.DATABASENAME + ".`course_user` VALUES (?, ?, ?, ?)";
            else
                query = "DELETE FROM " + Constants.DATABASENAME + ".`course_user` WHERE `netID`=? AND `number`=? AND `term`=? AND `year`=?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, req.getParameter("netId"));
            ps.setString(2, req.getParameter("num"));
            ps.setString(3, req.getParameter("tsem"));
            ps.setInt(4, Integer.parseInt(req.getParameter("tyear")));

            ps.executeUpdate();
            conn.commit();
            ps.close();
            conn.close();

            out.print("success");
        }catch (SQLException ex){
            ex.printStackTrace();
            out.print("error");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");
        System.out.println(user);

        try {
            Connection conn = ConnectionHandler.getConnection();

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
                    Constants.DATABASENAME + ".`users`.`type`='1'";

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
                course.setInstructor(rs.getString("netID"));
                course.setInstructor_name(rs.getString("firstName") + " " + rs.getString("lastName"));
                if (user.getType() == User.UserType.STUDENT)
                    course.setStatus(getUserStatus(conn, user.getNetID(), course.getNumber(), course.getTerm(), course.getYear()));

                courses.add(course);
            }

            System.out.println(courses);
            req.setAttribute("courses", courses);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/view_courses");
            rd.forward(req, resp);
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
