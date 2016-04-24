package com.se.pranita.termproject.controllers.resources;

import com.se.pranita.termproject.model.*;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Pranita on 20/4/16.
 */
@WebServlet("/view_resources")
public class ResourcesServlet extends HttpServlet {

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
            req.setAttribute("reservations", reservations);
//            resp.sendRedirect("/ViewResources");
            RequestDispatcher rd = getServletContext()
                    .getRequestDispatcher("/ViewResources");
            rd.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        try {
            Resource resource = new Resource();
            resource.setName(req.getParameter("resource_name"));
            resource.setType(req.getParameter("resource_type"));
            resource.setInfo(req.getParameter("resource_additional"));
            if(resource.save()) {
                response.sendRedirect("/reserve_resource");
            } else {
                session.setAttribute("error", "Invalid Resource Data. Please check and try again!!");
                response.sendRedirect("/error");
            }

        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect("/error");
        }
    }
}
