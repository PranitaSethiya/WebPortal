package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.ConnectionHandler;
import com.se.pranita.termproject.model.Discussion;
import com.se.pranita.termproject.model.Exam;
import com.se.pranita.termproject.model.User;
import com.se.pranita.termproject.utils.Constants;

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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pranita on 23/4/16.
 */
@WebServlet("/exams")
public class ExamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");
        System.out.println(user);

        try {
            ArrayList<Exam> exams = new ArrayList<>();
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT e.`examID`, e.`netID`, `name`, `date_of_exam`, `additional_details`, b.`netID` AS studentID, u.`firstName`, u.`lastName` FROM " +
                    Constants.DATABASENAME + ".`exams` e left join " +
                    Constants.DATABASENAME + ".`exam_user` b on e.`examID`=b.`examID` left join " +
                    Constants.DATABASENAME + ".`users` u on e.`netID`=u.`netID` ORDER BY e.`date_of_exam` DESC";

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);

            Map<Integer, Integer> added = new HashMap<>();

            while (rs.next()) {

                int examID = rs.getInt("examID");

                if(!added.keySet().contains(examID)) {
                    exams.add(getExam(examID, rs, user));
                    added.put(examID, exams.size() - 1);
                } else if(user.getNetID().equalsIgnoreCase(rs.getString("studentID"))) {
                    exams.remove((int)added.get(examID));
                    exams.add(getExam(examID, rs, user));
                    added.put(examID, exams.size() - 1);
                }
            }


            System.out.println(exams);
            req.setAttribute("exams", exams);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/exams.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }

    private Exam getExam(int examID, ResultSet rs, User user) throws SQLException {
        Exam exam = new Exam();
        exam.setExamID(examID);
        exam.setNetID(rs.getString("netID"));
        exam.setName(rs.getString("name"));
        exam.setDateOfExam(rs.getDate("date_of_exam").toString());
        exam.setAdditionalDetails(rs.getString("additional_details"));
        exam.setOwnerName(rs.getString("firstName") + " " + rs.getString("lastName"));
        exam.setEnrolled(user.getNetID().equalsIgnoreCase(rs.getString("studentID")));

        exam.setExpired(Date.valueOf(exam.getDateOfExam()).compareTo(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()))) < 0);
        return exam;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PrintWriter out = response.getWriter();
        try {
            Connection conn = ConnectionHandler.getConnection();
            PreparedStatement ps;

            String query;
            if (request.getParameter("action").equalsIgnoreCase("create")) {
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("currentSessionUser");
                query = "INSERT INTO " + Constants.DATABASENAME +
                        ".`exams` (`netID`, `name`, `date_of_exam`, `additional_details`) " +
                        "VALUES(?, ?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, user.getNetID());
                ps.setString(2, request.getParameter("name"));
                ps.setDate(3, java.sql.Date.valueOf(request.getParameter("date_of_exam")));
                ps.setString(4, request.getParameter("additional_details"));

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            } else if (request.getParameter("action").equalsIgnoreCase("update")) {
                query = "UPDATE " + Constants.DATABASENAME + ".`exams` SET `name`=?, `date_of_exam`=?, `additional_details`=? " +
                        "WHERE `examID`=?";
                ps = conn.prepareStatement(query);

                ps.setString(1, request.getParameter("name"));
                ps.setDate(2, java.sql.Date.valueOf(request.getParameter("date_of_exam")));
                ps.setString(3, request.getParameter("additional_details"));

                ps.setInt(4, Integer.parseInt(request.getParameter("examID")));

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            } else if (request.getParameter("action").equalsIgnoreCase("delete")) {
                query = "DELETE FROM " + Constants.DATABASENAME + ".`exams` WHERE examID=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(request.getParameter("examID")));
                ps.executeUpdate();

                query = "DELETE FROM " + Constants.DATABASENAME + ".`exam_user` WHERE examID=?";
                PreparedStatement ps2 = conn.prepareStatement(query);
                ps2.setInt(1, Integer.parseInt(request.getParameter("examID")));
                ps2.executeUpdate();

                conn.commit();
                ps.close();
                ps2.close();
                conn.close();
            } else if(request.getParameter("action").equalsIgnoreCase("enroll")) {
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("currentSessionUser");
                query = "INSERT INTO " + Constants.DATABASENAME +
                        ".`exam_user` " + "VALUES(?, ?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(request.getParameter("examID")));
                ps.setString(2, user.getNetID());

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();

            } else if(request.getParameter("action").equalsIgnoreCase("drop")) {
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("currentSessionUser");
                query = "DELETE FROM " + Constants.DATABASENAME + ".`exam_user` WHERE examID=? AND netID=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(request.getParameter("examID")));
                ps.setString(2, user.getNetID());

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            }


            out.print("success");
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.print("error");
        }
    }
}
