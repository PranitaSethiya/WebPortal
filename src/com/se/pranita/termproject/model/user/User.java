package com.se.pranita.termproject.model.user;

import com.google.gson.GsonBuilder;
import com.se.pranita.termproject.model.common.ConnectionHandler;
import com.se.pranita.termproject.model.Course;
import com.se.pranita.termproject.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Pranita on 14/4/16.
 */
public abstract class User {

    private String netID;
    private String password;
    private String firstName;
    private String lastName;
    private UserType type;
    private ArrayList<Course> courses;

    public User(UserType type) {
        this.type = type;
    }

    public User(UserType type, String netID, String password, String firstName, String lastName) {
        this.type = type;
        this.netID = netID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toJSON() {
        return new GsonBuilder().create().toJson(this);
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public enum UserType {
        STUDENT(0), FACULTY(1), STAFF(2);

        int value;

        UserType(int value) {
            this.value = value;
        }

        public static UserType getUserType(String type) {
            if (type.equalsIgnoreCase("student"))
                return STUDENT;
            else if (type.equalsIgnoreCase("faculty"))
                return FACULTY;
            else if (type.equalsIgnoreCase("staff"))
                return STAFF;
            else
                return null;
        }

        public int getValue() {
            return this.value;
        }
    }

    @Override
    public String toString() {
        return toJSON();
    }
}
