package com.se.pranita.termproject.controllers;

import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.model.Resource;
import com.se.pranita.termproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Pranita on 21/4/16.
 */
@WebServlet("/CreateCourseServlet")
public class CreateCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        course_num=a&course_name=a&sem=Fall&year=a&course_syllabus=&ins_office=&ins_office_hour=&ta_name=&ta_email=&ta_office=&ta_office_hour=
        HttpSession session = req.getSession(false);
        try {
            Course course = new Course();
            course.setNumber(req.getParameter("course_num"));
            course.setName(req.getParameter("course_name"));
            course.setDepartment(req.getParameter("department"));
            course.setTerm(req.getParameter("sem"));
            course.setYear(Integer.parseInt(req.getParameter("year")));
            course.setCourse_syllabus(req.getParameter("course_syllabus"));
            course.setIns_office(req.getParameter("ins_office"));
            course.setInstructor(((User)session.getAttribute("currentSessionUser")).getNetID());
            course.setIns_office_hour(req.getParameter("ins_office_hour"));
            course.setTa_name(req.getParameter("ta_name"));
            course.setTa_email(req.getParameter("ta_email"));
            course.setTa_office(req.getParameter("ta_office"));
            course.setTa_office_hour(req.getParameter("ta_office_hour"));
            if(course.save()) {
                resp.sendRedirect("/view_courses");
            } else {
                session.setAttribute("error", "Invalid Data. Please check and try again!!");
                resp.sendRedirect("/error");
            }

        } catch(Exception ex) {
            session.setAttribute("error", ex.getMessage());
            resp.sendRedirect("/error");
        }



    }
}
