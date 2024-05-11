/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Neil Patrick
 */
public class DepartmentService {

    public static final String DEPARTMENTS_TABLE = "departments";
    public static final String DEPARTMENT_ID_COLUMN = "department_id";
    public static final String DEPARTMENT_NAME_COLUMN = "department_name";

    public static void addDepartment(String department_name) {
        Connection conn = AccessDatabaseConnector.connect();
        try {

            // Execute an INSERT query
            try (Statement statement = conn.createStatement()) {
                // Execute an INSERT query
                String insertQuery = "INSERT INTO " + DEPARTMENTS_TABLE + " (" + DEPARTMENT_NAME_COLUMN + ") VALUES ('" + department_name + "');";

                int rowsAffected = statement.executeUpdate(insertQuery);
                // Check the number of rows affected
                if (rowsAffected > 0) {
                    System.out.println("Insertion successful. Rows affected: " + rowsAffected);
                } else {
                    System.out.println("Insertion failed.");
                }
                // Close the statement
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }     
    
    public static Department getDepartmentByName(String department_name) {
        String selectQuery = "SELECT * FROM " + DEPARTMENTS_TABLE + " WHERE " + DEPARTMENT_NAME_COLUMN + " = '" + department_name + "' LIMIT 1;";
        
        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(selectQuery);

            Department department = null;

            // Process the results
            while (resultSet.next()) {
                int department_id = resultSet.getInt(DEPARTMENT_ID_COLUMN);
                department = new Department(department_id, department_name);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return department;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return null;
    }

    public static List getAllDepartments() {
        List<Department> departments = new ArrayList<>();

        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            // Execute a SELECT query
            String selectQuery = "SELECT * FROM " + DEPARTMENTS_TABLE;
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the results
            while (resultSet.next()) {
                // Retrieve data from the result set
                int id = resultSet.getInt(DEPARTMENT_ID_COLUMN);
                String name = resultSet.getString(DEPARTMENT_NAME_COLUMN);
                Department department = new Department(id, name);
                departments.add(department);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return departments;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return null;
    }
    
    public static void updateDepartment(int department_id, String deparment_name) {
        Connection conn = AccessDatabaseConnector.connect();
        try (Statement statement = conn.createStatement()) {
            String updateQuery = "Update " + DEPARTMENTS_TABLE + " SET " + DEPARTMENT_NAME_COLUMN + " = '" + deparment_name +  "' WHERE " + DEPARTMENT_ID_COLUMN + " = " + department_id + ";";
            System.out.println(updateQuery);
            statement.executeUpdate(updateQuery);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }

}
