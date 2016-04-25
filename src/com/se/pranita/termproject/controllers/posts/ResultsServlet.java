package com.se.pranita.termproject.controllers.posts;

import com.se.pranita.termproject.model.Result;
import com.se.pranita.termproject.model.dao.ResultDAO;
import com.se.pranita.termproject.model.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Pranita on 24/4/16.
 */
@WebServlet("/results")
public class ResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("currentSessionUser");

        try {
            ArrayList<Result> results = new ResultDAO().get(user.getNetID());

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
            if (request.getParameter("action").equalsIgnoreCase("create")) {

                HttpSession session = request.getSession(false);
                User user = (User) session.getAttribute("currentSessionUser");

                new ResultDAO().save(user.getNetID(), request.getParameter("exam_name"), request.getParameter("result_details"));

            } else if (request.getParameter("action").equalsIgnoreCase("update")) {

                new ResultDAO().put(request.getParameter("exam_name"), request.getParameter("result_details"), request.getParameter("resultID"));

            } else if (request.getParameter("action").equalsIgnoreCase("delete")) {
                new ResultDAO().delete(Integer.parseInt(request.getParameter("resultID")));
            }


            out.print("success");
        } catch (SQLException ex) {
            ex.printStackTrace();
            out.print("error");
        }
    }
}
