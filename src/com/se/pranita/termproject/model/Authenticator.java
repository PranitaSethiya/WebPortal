package com.se.pranita.termproject.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Pranita on 19/4/16.
 */
public class Authenticator {

    static Connection con = null;
    static ResultSet rs = null;

    public static User login(String netId, String password) {

        System.out.println("NetID: " + netId + " Password: " + password);

        Statement stmt = null;
        User user = null;
        String searchQuery = "select * from users where netId='" + netId + "' AND password='" + password + "'";
        try {

            con = ConnectionHandler.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean present = rs.next();
            if (!present)
                return null;
            else if (present) {
                user = new UserFactory().getUser(rs.getInt("type"));
                user.setNetID(netId);
                user.setPassword(password);
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ignored) {
                }
                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception ignored) {
                }
                stmt = null;
            }

            if (con != null) {
                try {
                    con.close();
                } catch (Exception ignored) {
                }

                con = null;
            }
        }

        return user;
    }
}
