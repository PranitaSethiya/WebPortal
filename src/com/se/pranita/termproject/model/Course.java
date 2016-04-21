package com.se.pranita.termproject.model;

import com.se.pranita.termproject.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Pranita on 21/4/16.
 */
public class Course {
    private String number;
    private String name;
    private String department;
    private String course_syllabus;
    private String instructor;
    private String ins_office_hour;
    private String ins_office;
    private String ta_name;
    private String ta_office_hour;
    private String ta_office;
    private String ta_email;
    private String term;
    private int year;

    public Course() {
    }

    public Course(String number, String name, String department, String course_syllabus, String instructor, String ins_office_hour, String ins_office, String ta_name, String ta_office_hour, String ta_office, String ta_email, String term, int year) {
        this.number = number;
        this.name = name;
        this.department = department;
        this.course_syllabus = course_syllabus;
        this.instructor = instructor;
        this.ins_office_hour = ins_office_hour;
        this.ins_office = ins_office;
        this.ta_name = ta_name;
        this.ta_office_hour = ta_office_hour;
        this.ta_office = ta_office;
        this.ta_email = ta_email;
        this.term = term;
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse_syllabus() {
        return course_syllabus;
    }

    public void setCourse_syllabus(String course_syllabus) {
        this.course_syllabus = course_syllabus;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getIns_office_hour() {
        return ins_office_hour;
    }

    public void setIns_office_hour(String ins_office_hour) {
        this.ins_office_hour = ins_office_hour;
    }

    public String getIns_office() {
        return ins_office;
    }

    public void setIns_office(String ins_office) {
        this.ins_office = ins_office;
    }

    public String getTa_name() {
        return ta_name;
    }

    public void setTa_name(String ta_name) {
        this.ta_name = ta_name;
    }

    public String getTa_office_hour() {
        return ta_office_hour;
    }

    public void setTa_office_hour(String ta_office_hour) {
        this.ta_office_hour = ta_office_hour;
    }

    public String getTa_office() {
        return ta_office;
    }

    public void setTa_office(String ta_office) {
        this.ta_office = ta_office;
    }

    public String getTa_email() {
        return ta_email;
    }

    public void setTa_email(String ta_email) {
        this.ta_email = ta_email;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Course{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", course_syllabus='" + course_syllabus + '\'' +
                ", instructor='" + instructor + '\'' +
                ", ins_office_hour='" + ins_office_hour + '\'' +
                ", ins_office='" + ins_office + '\'' +
                ", ta_name='" + ta_name + '\'' +
                ", ta_office_hour='" + ta_office_hour + '\'' +
                ", ta_office='" + ta_office + '\'' +
                ", ta_email='" + ta_email + '\'' +
                ", term='" + term + '\'' +
                ", year=" + year +
                '}';
    }

    public boolean save() throws SQLException {
        int status;
        Connection conn = ConnectionHandler.getConnection();
        String query = "INSERT INTO " + Constants.DATABASENAME + ".`courses` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, this.getNumber());
        ps.setString(2, this.getName());
        ps.setString(3, this.getDepartment());
        ps.setString(4, this.getCourse_syllabus());
        ps.setString(5, this.getInstructor());
        ps.setString(6, this.getIns_office_hour());
        ps.setString(7, this.getIns_office());
        ps.setString(8, this.getTa_name());
        ps.setString(9, this.getTa_office_hour());
        ps.setString(10, this.getTa_office());
        ps.setString(11, this.getTa_email());
        ps.setString(12, this.getTerm());
        ps.setInt(13, this.getYear());

        status = ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
        return status > 0;
    }
}
