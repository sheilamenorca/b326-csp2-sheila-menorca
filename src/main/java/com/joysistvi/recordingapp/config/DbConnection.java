/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joysistvi.recordingapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC Standard Practice
public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/music_db?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }



    public Connection connect() throws SQLException {
        // return connection object
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
// ducking exception