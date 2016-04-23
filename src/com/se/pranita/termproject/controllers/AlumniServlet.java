package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.Alumni;
import com.se.pranita.termproject.model.ConnectionHandler;
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
import java.util.Enumeration;

/**
 * Created by Pranita on 22/4/16.
 */
@WebServlet("/alumni")
public class AlumniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
//        User user = (User) session.getAttribute("currentSessionUser");
//        System.out.println(user);

        try {
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM " + Constants.DATABASENAME + ".`alumni`";

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);


            ArrayList<Alumni> alumnis = new ArrayList<>();
            while (rs.next()) {
                Alumni alumni = new Alumni();
                alumni.setName(rs.getString("name"));
                alumni.setDescription(rs.getString("description"));
                alumni.setHomepage(rs.getString("homepage"));
                alumni.setImage(rs.getString("image"));
                alumnis.add(alumni);
            }

            System.out.println(alumnis);
            req.setAttribute("alumnis", alumnis);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/alumni.jsp");
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
            if(request.getParameter("action").equalsIgnoreCase("save")) {
                query = "INSERT INTO " + Constants.DATABASENAME + ".`alumni` VALUES(?, ?, ?, ?)";
                ps = conn.prepareStatement(query);

                ps.setString(1, request.getParameter("name"));
                ps.setString(2, request.getParameter("homepage"));
                ps.setString(3, request.getParameter("description"));
                ps.setString(4, request.getParameter("image"));

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            }else if(request.getParameter("action").equalsIgnoreCase("update")) {
                query = "UPDATE " + Constants.DATABASENAME + ".`alumni` SET homepage=?, description=?, image=? WHERE name=?";
                ps = conn.prepareStatement(query);

                ps.setString(1, request.getParameter("homepage"));
                ps.setString(2, request.getParameter("description"));
                ps.setString(3, request.getParameter("image"));
                ps.setString(4, request.getParameter("name"));

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            }


            out.print("success");
        }catch (SQLException ex){
            ex.printStackTrace();
            out.print("error");
        }
    }
}
