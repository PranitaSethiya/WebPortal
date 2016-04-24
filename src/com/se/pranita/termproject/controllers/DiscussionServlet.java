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
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Pranita on 23/4/16.
 */
@WebServlet("/discussions")
public class DiscussionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(req.getParameter("discussion_id") == null) {
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("currentSessionUser");
            System.out.println(user);

            try {
                ArrayList<Discussion> discussions = new ArrayList<>();
                Connection conn = ConnectionHandler.getConnection();
                String query = "SELECT * FROM " + Constants.DATABASENAME + ".`discussion_post` JOIN " + Constants.DATABASENAME + ".`users` ON " +
                        Constants.DATABASENAME + ".`users`.`netID` = " +
                        Constants.DATABASENAME + ".`discussion_post`.`netID` " +
                        "WHERE " + Constants.DATABASENAME + ".`discussion_post`.`type`=" + 0 + " " +
                        "ORDER BY " + Constants.DATABASENAME + ".`discussion_post`.`create_time`" + " DESC";

                Statement smt = conn.createStatement();
                ResultSet rs = smt.executeQuery(query);

                while (rs.next()) {
                    discussions.add(getDiscussion(rs));
                }


                System.out.println(discussions);
                req.setAttribute("discussions", discussions);
                RequestDispatcher rd = getServletContext()
                        .getRequestDispatcher("/discussion.jsp");
                rd.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
                session.setAttribute("error", e.getMessage());
                resp.sendRedirect("/error");

            }
        } else {
            doGetDetailed(req, resp);
        }
    }

    private void doGetDetailed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        try {
            ArrayList<Discussion> discussions = new ArrayList<>();
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM " + Constants.DATABASENAME + ".`discussion_post` JOIN " + Constants.DATABASENAME + ".`users` ON " +
                    Constants.DATABASENAME + ".`users`.`netID` = " +
                    Constants.DATABASENAME + ".`discussion_post`.`netID` " +
                    "WHERE " + Constants.DATABASENAME + ".`discussion_post`.`discussion_id`='" + req.getParameter("discussion_id") + "' " +
                    "OR " + Constants.DATABASENAME + ".`discussion_post`.`id`='" + req.getParameter("discussion_id") + "' " +
                    "ORDER BY " + Constants.DATABASENAME + ".`discussion_post`.`create_time`" + " ASC";

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);

            while (rs.next()) {
                discussions.add(getDiscussion(rs));
            }


            System.out.println(discussions);
            req.setAttribute("discussions", discussions);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/discussion_thread.jsp");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }

    private Discussion getDiscussion(ResultSet rs) throws SQLException {
        Discussion discussion = new Discussion();
        discussion.setId(rs.getInt("id"));
        discussion.setDiscussion_id(rs.getInt("discussion_id"));
        discussion.setNetID(rs.getString("netID"));
        discussion.setTitle(rs.getString("title"));
        discussion.setDetails(rs.getString("details"));
        discussion.setCreate_time(rs.getTimestamp("create_time"));
        discussion.setUpdated_time(rs.getTimestamp("updated_time"));
        discussion.setType(Discussion.DiscussionType.getDiscussionType(rs.getInt("type")));
        discussion.setOwnerName(rs.getString("firstName") + " " + rs.getString("lastName"));
        return  discussion;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PrintWriter out = response.getWriter();
        try {
            Connection conn = ConnectionHandler.getConnection();
            PreparedStatement ps;

            String query;
            if(request.getParameter("action").equalsIgnoreCase("save")) {
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("currentSessionUser");
                query = "INSERT INTO " + Constants.DATABASENAME +
                        ".`discussion_post` (`netID`, `title`, `details`, `type`, `discussion_id`) " +
                        "VALUES(?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);

                ps.setString(1, user.getNetID());
                ps.setString(2, request.getParameter("title"));
                ps.setString(3, request.getParameter("details"));
                ps.setInt(4, Integer.parseInt(request.getParameter("type")));
                if(request.getParameter("discussion_id") != null)
                    ps.setInt(5, Integer.parseInt(request.getParameter("discussion_id")));
                else
                    ps.setString(5, null);

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            }else if(request.getParameter("action").equalsIgnoreCase("update")) {
                query = "UPDATE " + Constants.DATABASENAME + ".`discussion_post` SET `title`=?, `details`=? " +
                        "WHERE `id`=?";
                ps = conn.prepareStatement(query);

                ps.setString(1, request.getParameter("title"));
                ps.setString(2, request.getParameter("details"));

                ps.setInt(3, Integer.parseInt(request.getParameter("id")));

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            } else if(request.getParameter("action").equalsIgnoreCase("delete")) {
                query = "DELETE FROM " + Constants.DATABASENAME + ".`discussion_post` WHERE id=? OR discussion_id=?";
                ps = conn.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(request.getParameter("id")));
                ps.setInt(2, Integer.parseInt(request.getParameter("id")));

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
