package com.se.pranita.termproject.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Pranita on 20/4/16.
 */
@WebServlet("/getTimeSlots")
public class GetTimeSlotServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String group = request.getParameter("group");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonSerializer someJsonSerializer = new JsonSerializer();
        String json = someJsonSerializer.serialize(userService.findUsers(group));
        out.print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
