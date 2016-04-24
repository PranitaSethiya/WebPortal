package com.se.pranita.termproject.controllers.posts;

import com.se.pranita.termproject.model.announcement.Announcement;
import com.se.pranita.termproject.model.announcement.AnnouncementFactory;
import com.se.pranita.termproject.model.announcement.Event;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Pranita on 23/4/16.
 */
@WebServlet("/announcements")
public class AnnouncementServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");
        System.out.println(user);

        try {
            ArrayList<Announcement> announcements = new ArrayList<>();
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM " + Constants.DATABASENAME + ".`announcements` JOIN " + Constants.DATABASENAME + ".`users` ON " +
                    Constants.DATABASENAME + ".`users`.`netID` = " +
                    Constants.DATABASENAME + ".`announcements`.`netID` ORDER BY " + Constants.DATABASENAME + ".`announcements`.`create_time`" + " DESC";

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);

            while (rs.next()) {
                Announcement announcement = new AnnouncementFactory().getAnnouncement(rs.getInt("announcement_type"));
                announcement.setNetID(rs.getString("netID"));
                announcement.setLink(rs.getString("link"));
                announcement.setTitle(rs.getString("title"));
                announcement.setDetails(rs.getString("details"));
                announcement.setCreateTime(rs.getTimestamp("create_time"));
                announcement.setId(rs.getInt("id"));

                if(announcement.getType() == Announcement.AnnouncementType.EVENT) {
                    ((Event)announcement).setEventDatetime(rs.getTimestamp("event_datetime"));
                    ((Event)announcement).setEventVenue(rs.getString("event_venue"));
                }
                announcement.setOwnerName(rs.getString("firstName") + " " + rs.getString("lastName"));

                announcements.add(announcement);

                System.out.println("JSON: " + announcement.toString());
            }


            System.out.println(announcements);
            req.setAttribute("announcements", announcements);
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/announcements.jsp");
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
                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("currentSessionUser");
                query = "INSERT INTO " + Constants.DATABASENAME +
                        ".`announcements` (`netID`, `title`, `details`, `link`, `announcement_type`, `event_datetime`, `event_venue`) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);

                ps.setString(1, user.getNetID());
                ps.setString(2, request.getParameter("title"));
                ps.setString(3, request.getParameter("details"));
                ps.setString(4, request.getParameter("link"));
                ps.setInt(5, Announcement.AnnouncementType.getAnnouncementType(request.getParameter("announcement_type")).getValue());

                if(Announcement.AnnouncementType.getAnnouncementType(request.getParameter("announcement_type")) == Announcement.AnnouncementType.EVENT) {

                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
                    java.util.Date startDate;
                    try {
                        startDate = df.parse(request.getParameter("event_datetime").trim());
                        ps.setTimestamp(6, new Timestamp(startDate.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        ps.setTimestamp(6, null);
                    }

                    ps.setString(7, request.getParameter("event_venue"));
                } else {
                    ps.setTimestamp(6, null);
                    ps.setString(7, null);
                }

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            }else if(request.getParameter("action").equalsIgnoreCase("update")) {
                query = "UPDATE " + Constants.DATABASENAME + ".`announcements` SET `title`=?, `details`=?, `link`=?, `announcement_type`=?, " +
                        "`event_datetime`=?, `event_venue`=? WHERE `id`=?";
                ps = conn.prepareStatement(query);

                ps.setString(1, request.getParameter("title"));
                ps.setString(2, request.getParameter("details"));
                ps.setString(3, request.getParameter("link"));
                ps.setInt(4, Announcement.AnnouncementType.getAnnouncementType(request.getParameter("announcement_type")).getValue());

                if(Announcement.AnnouncementType.getAnnouncementType(request.getParameter("announcement_type")) == Announcement.AnnouncementType.EVENT) {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm a");
                    java.util.Date startDate;
                    try {
                        startDate = df.parse(request.getParameter("event_datetime").trim());
                        ps.setTimestamp(5, new Timestamp(startDate.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        ps.setTimestamp(5, null);
                    }
                    ps.setString(6, request.getParameter("event_venue"));
                } else {
                    ps.setTimestamp(5, null);
                    ps.setString(6, null);
                }
                ps.setInt(7, Integer.parseInt(request.getParameter("id")));

                ps.executeUpdate();
                conn.commit();
                ps.close();
                conn.close();
            } else if(request.getParameter("action").equalsIgnoreCase("delete")) {
                query = "DELETE FROM " + Constants.DATABASENAME + ".`announcements` WHERE id=?";
                ps = conn.prepareStatement(query);

                ps.setInt(1, Integer.parseInt(request.getParameter("id")));

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
