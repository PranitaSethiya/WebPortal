package com.se.pranita.termproject.model;

import com.google.gson.GsonBuilder;
import com.se.pranita.termproject.utils.Constants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Pranita on 14/4/16.
 */
public abstract class User {

    private String netID;
    private String password;
    private String firstName;
    private String lastName;
    private UserType type;

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

    public boolean save() throws SQLException {
        int status;
        Connection conn = ConnectionHandler.getConnection();
        String query = "INSERT INTO " + Constants.DATABASENAME + ".`users` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, this.netID);
        ps.setString(2, this.password);
        ps.setString(3, this.firstName);
        ps.setString(4, this.lastName);
        ps.setInt(5, this.type.getValue());
        if(type == UserType.STUDENT) {
            ps.setString(6, ((Student) this).getStartTerm());
            ps.setInt(7, ((Student) this).getStartYear());
            ps.setString(8, ((Student) this).getProgram());
            ps.setString(9, ((Student) this).getDepartment());
        } else {
            ps.setString(6, null);
            ps.setInt(7, -1);
            ps.setString(8, null);
            ps.setString(9, null);
        }

        status = ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();


        return status > 0;
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
        return "User{" +
                "netID='" + netID + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type=" + type +
                '}';
    }
}
