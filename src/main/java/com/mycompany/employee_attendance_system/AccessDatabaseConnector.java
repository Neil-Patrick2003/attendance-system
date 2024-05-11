/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompany.employee_attendance_system;
//
///**
// *
// * @author Neil Patrick
// */
//public class AccessDatabseConnector {
//    
//}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Neil Patrick
 */
public class AccessDatabaseConnector {

    // JDBC URL, username, and password of the database to connect
    private static final String DATABASE = "attendance_system";
    private static final String PORT = "3306";
    private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DATABASE + "?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Method to establish a connection to the Access database
    public static Connection connect() {
        Connection connection = null;
        try {
            // Establish a connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            // Handle any errors
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }

    // Method to close the database connection
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            // Handle any errors
            System.err.println("Error closing the connection: " + e.getMessage());
        }
    }
}
