package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.*;
import com.se.pranita.termproject.utils.Constants;

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

/**
 * Created by Pranita on 20/4/16.
 */
@WebServlet("/view_resources")
public class ViewResourcesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");
        System.out.println(user);

        try {
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM " + Constants.DATABASENAME + ".`reservations` WHERE `netID`='" + user.getNetID()
                    + "' AND `slot_date`>='" + new Date(Calendar.getInstance().getTime().getTime()) + "' ORDER BY 3";
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);


            ArrayList<Reservation> reservations = new ArrayList<>();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setResourceName(rs.getString("name"));
                reservation.setNetID(rs.getString("netID"));
                reservation.setSlot_time_range(rs.getString("slot_time_range"));
                reservation.setSlot_date(rs.getDate("slot_date"));
                reservations.add(reservation);
            }
            System.out.println(reservations);
            session.setAttribute("reservations", reservations);
            resp.sendRedirect("/ViewResources");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }
}
