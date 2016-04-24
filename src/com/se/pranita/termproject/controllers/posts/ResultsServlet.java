package com.se.pranita.termproject.controllers.posts;

import com.se.pranita.termproject.model.Result;
import com.se.pranita.termproject.model.common.ConnectionHandler;
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
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
@WebServlet("/results")
public class ResultsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");
        System.out.println(user);

        try {
            ArrayList<Result> results = new ArrayList<>();
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT `resultID`, r.`netID`, `exam_name`, `result_details`, `create_time`, u.`firstName`, u.`lastName` FROM " +
                    Constants.DATABASENAME + ".`results` r left join " +
                    Constants.DATABASENAME + ".`users` u on r.`netID`=u.`netID` ORDER BY r.`create_time` DESC";

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);

            while (rs.next()) {
                Result result = new Result();
                result.setExamName(rs.getString("exam_name"));
                result.setNetID(rs.getString("netID"));
                result.setResultID(rs.getInt("resultID"));
                result.setResultDetails(rs.getString("result_details"));
                result.setCreateTime(rs.getTimestamp("create_time"));
                result.setOwnerName(rs.getString("firstName") + " " + rs.getString("lastName"));
                result.setOwner(user.getNetID().equalsIgnoreCase(result.getNetID()));
                results.add(result);
            }

            System.out.println(results);
            req.setAttribute("results", results);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/results.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
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
                        ".`results` (`netID`, `exam_name`, `result_details`) " +
                        "VALUES(?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, user.getNetID());
                ps.setString(2, request.getParameter("exam_name"));
                ps.setString(3, request.getParameter("result_details"));

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            } else if (request.getParameter("action").equalsIgnoreCase("update")) {
                query = "UPDATE " + Constants.DATABASENAME + ".`results` SET `exam_name`=?, `result_details`=? " +
                        "WHERE `resultID`=?";
                ps = conn.prepareStatement(query);

                ps.setString(1, request.getParameter("exam_name"));
                ps.setString(2, request.getParameter("result_details"));
                ps.setString(3, request.getParameter("resultID"));

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            } else if (request.getParameter("action").equalsIgnoreCase("delete")) {
                query = "DELETE FROM " + Constants.DATABASENAME + ".`results` WHERE resultID=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(request.getParameter("resultID")));

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
