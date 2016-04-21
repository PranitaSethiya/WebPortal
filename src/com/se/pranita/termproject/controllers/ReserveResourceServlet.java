package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.ConnectionHandler;
import com.se.pranita.termproject.model.Resource;

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

/**
 * Created by Pranita on 20/4/16.
 */
@WebServlet("/reserve_resource")
public class ReserveResourceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        HttpSession session = req.getSession(false);
        try {
            Connection conn = ConnectionHandler.getConnection();
            String query = "SELECT * FROM `WebPortal`.`resources` ORDER BY 1";
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(query);

            ArrayList<Resource> resources = new ArrayList<>();
            while (rs.next()) {
                Resource resource = new Resource();
                resource.setName(rs.getString("name"));
                resource.setType(rs.getString("type"));
                resource.setInfo(rs.getString("info"));
                resources.add(resource);
            }
            session.setAttribute("resources", resources);
            resp.sendRedirect("/ReserveResource");
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", e.getMessage());
            resp.sendRedirect("/error");

        }


    }
}
