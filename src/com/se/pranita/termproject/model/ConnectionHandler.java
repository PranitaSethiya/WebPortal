package com.se.pranita.termproject.model;

import com.se.pranita.termproject.utils.Constants;

import java.io.IOException;
import java.sql.Connection;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Pranita on 16/4/16.
 */
public class ConnectionHandler {

    public static Connection connection;
    private static String address;
    private static String username;
    private static String password;
    private static String dbName;

    private void DbConnection() {
        address = Constants.DBHOSTADDRESS;
        username = Constants.USERNAME;
        password = Constants.PASSWORD;
        dbName = Constants.DATABASENAME;
    }

    public static Connection getConnection() {
        ConnectionHandler cHandler = new ConnectionHandler();
        cHandler.DbConnection();
        String connectionString = "jdbc:mysql://" + address + "/" + dbName;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionString, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
