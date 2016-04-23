package com.se.pranita.termproject.model;

import com.google.gson.GsonBuilder;

/**
 * Created by Pranita on 14/4/16.
 */
public class Student extends User {

    private String startTerm;
    private int startYear;
    private String program;
    private String department;

    public Student() {
        super(UserType.STUDENT);
    }

    public Student(String netID, String password, String firstName, String lastName) {
        super(UserType.STUDENT, netID, password, firstName, lastName);
    }

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "startTerm='" + startTerm + '\'' +
                "startYear='" + startYear + '\'' +
                ", program='" + program + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public Student(String netID, String password, String firstName, String lastName, String startTerm, int startYear, String program, String department) {
        super(UserType.STUDENT, netID, password, firstName, lastName);
        this.startTerm = startTerm;
        this.startYear = startYear;
        this.program = program;
        this.department = department;
    }

    public String getStartTerm() {
        return startTerm;
    }

    public void setStartTerm(String startTerm) {
        this.startTerm = startTerm;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Override
    public String toJSON(){
        return new GsonBuilder().create().toJson(Student.this);
    }
}
