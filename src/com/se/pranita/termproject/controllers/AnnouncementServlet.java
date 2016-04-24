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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

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
                    Constants.DATABASENAME + ".`announcements`.`netID`";

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);

            while (rs.next()) {
                Announcement announcement = new AnnouncementFactory().getAnnouncement(rs.getInt("announcement_type"));
                announcement.setNetID(rs.getString("netID"));
                announcement.setLink(rs.getString("link"));
                announcement.setTitle(rs.getString("title"));
                announcement.setDetails(rs.getString("details"));

                if(announcement.getType() == Announcement.AnnouncementType.EVENT) {
                    ((Event)announcement).setEventDatetime(rs.getTimestamp("event_datetime"));
                    ((Event)announcement).setEventVenue(rs.getString("event_venue"));
                }
                announcement.setOwnerName(user.getFirstName() + " " + user.getLastName());

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> var = req.getParameterNames();
        while(var.hasMoreElements())
            System.out.println(var.nextElement());
    }
}
