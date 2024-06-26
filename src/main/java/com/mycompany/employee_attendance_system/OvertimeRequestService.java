/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

import static com.mycompany.employee_attendance_system.LeaveRequestService.LEAVE_REQUESTS_TABLE;
import static com.mycompany.employee_attendance_system.LeaveRequestService.REQUEST_ID_COLUMN;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Neil Patrick
 */
public class OvertimeRequestService {

    public static final String OVERTIME_REQUESTS_TABLE = "overtime_requests";
    public static final String REQUEST_ID_COLUMN = "request_id";
    public static final String DATE_COLUMN = "date";
    public static final String NO_OF_HOURS_COLUMN = "no_of_hours";
    public static final String STATUS_COLUMN = "status";
    public static final String NOTES_COLUMN = "notes";
    public static final String EMPLOYEE_ID_COLUMN = "employee_id";

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void createOvertimeRequest(Date date, int noOfHours, String status, String notes, int employee_id) {
        Connection conn = AccessDatabaseConnector.connect();
        try {

            // Execute an INSERT query
            try (Statement statement = conn.createStatement()) {
                // Execute an INSERT query
                String insertQuery = "INSERT INTO " + OVERTIME_REQUESTS_TABLE + " (" + DATE_COLUMN + ", " + NO_OF_HOURS_COLUMN + ", " + STATUS_COLUMN + ", " + NOTES_COLUMN + ", " + EMPLOYEE_ID_COLUMN + " ) VALUES ('" + dateFormatter.format(date) + "', '" + noOfHours + "', '" + status + "', '" + notes + "', '" + employee_id + "');";
               
                System.out.println(insertQuery);
                int rowsAffected = statement.executeUpdate(insertQuery);
                // Check the number of rows affected
                if (rowsAffected > 0) {
                    System.out.println("Insertion successful. Rows affected: " + rowsAffected);
                } else {
                    System.out.println("Insertion failed.");
                }
            }
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }

    public static List getOvertimeRequests() {
        List<OvertimeRequest> overtimeRequests = new ArrayList<>();

        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            // Execute a SELECT query
            String selectQuery = "SELECT overtime_requests.*, employees.* "
                    + "FROM overtime_requests "
                    + "JOIN employees ON overtime_requests.employee_id = employees.employee_id";

            System.out.println(selectQuery);

            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the results
            while (resultSet.next()) {
                // Retrieve data from the result set
                int request_id = resultSet.getInt(REQUEST_ID_COLUMN);
                String status = resultSet.getString(STATUS_COLUMN);
                String notes = resultSet.getString(NOTES_COLUMN);
                int employee_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);
                Date date = resultSet.getDate(DATE_COLUMN);
                int noOfHours = resultSet.getInt(NO_OF_HOURS_COLUMN);

                OvertimeRequest overtimeRequest = new OvertimeRequest(request_id, date, noOfHours, notes, status, employee_id);
                overtimeRequests.add(overtimeRequest);

                String last_name = resultSet.getString("last_name");
                String first_name = resultSet.getString("first_name");
                String email = resultSet.getString("email");
                String phone_number = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Boolean is_admin = resultSet.getBoolean("is_admin");
                int department_id = resultSet.getInt("department_id");
                String position = resultSet.getString("position");
                Date hiring_date = hiring_date = resultSet.getDate("hiring_date");

                Employee employee = new Employee(employee_id, last_name, first_name, email, phone_number, address, username, password, is_admin, hiring_date, department_id, position);
                overtimeRequest.setEmployee(employee);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return overtimeRequests;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return overtimeRequests;
    }

    public static OvertimeRequest getOvertimeRequestById(int requestId) {   
        Connection conn = AccessDatabaseConnector.connect();
        OvertimeRequest overtimeRequest = null;
        try {
            Statement statement = conn.createStatement();

            // Execute a SELECT query
            String selectQuery = "SELECT overtime_requests.*, employees.* "
                    + "FROM overtime_requests "
                    + "JOIN employees ON overtime_requests.employee_id = employees.employee_id "
                    + "WHERE overtime_requests.request_id = " + requestId + " LIMIT 1;";

            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the results
            while (resultSet.next()) {
                // Retrieve data from the result set
                int request_id = resultSet.getInt(REQUEST_ID_COLUMN);
                String status = resultSet.getString(STATUS_COLUMN);
                String notes = resultSet.getString(NOTES_COLUMN);
                int employee_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);
                Date date = resultSet.getDate(DATE_COLUMN);
                int noOfHours = resultSet.getInt(NO_OF_HOURS_COLUMN);

                overtimeRequest = new OvertimeRequest(request_id, date, noOfHours, notes, status, employee_id);

                String last_name = resultSet.getString("last_name");
                String first_name = resultSet.getString("first_name");
                String email = resultSet.getString("email");
                String phone_number = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Boolean is_admin = resultSet.getBoolean("is_admin");
                int department_id = resultSet.getInt("department_id");
                String position = resultSet.getString("position");
                Date hiring_date = hiring_date = resultSet.getDate("hiring_date");

                Employee employee = new Employee(employee_id, last_name, first_name, email, phone_number, address, username, password, is_admin, hiring_date, department_id, position);
                overtimeRequest.setEmployee(employee);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return overtimeRequest;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return overtimeRequest;
    }

    public static List getOvertimeRequestsByEmployeeId(int employeeId) {
        List<OvertimeRequest> overtimeRequests = new ArrayList<>();

        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            // Execute a SELECT query
            String selectQuery = "SELECT overtime_requests.*, employees.* "
                    + "FROM overtime_requests "
                    + "JOIN employees ON overtime_requests.employee_id = employees.employee_id "
                    + "WHERE overtime_requests.employee_id = " + employeeId + ";";

            System.out.println(selectQuery);

            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the results
            while (resultSet.next()) {
                // Retrieve data from the result set
                int request_id = resultSet.getInt(REQUEST_ID_COLUMN);
                String status = resultSet.getString(STATUS_COLUMN);
                String notes = resultSet.getString(NOTES_COLUMN);
                int employee_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);

                Date date = resultSet.getDate(DATE_COLUMN);
                int noOfHours = resultSet.getInt(NO_OF_HOURS_COLUMN);


                OvertimeRequest overtimeRequest = new OvertimeRequest(request_id, date, noOfHours, notes, status, employee_id);
                overtimeRequests.add(overtimeRequest);

                String last_name = resultSet.getString("last_name");
                String first_name = resultSet.getString("first_name");
                String email = resultSet.getString("email");
                String phone_number = resultSet.getString("phone_number");
                String address = resultSet.getString("address");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Boolean is_admin = resultSet.getBoolean("is_admin");
                int department_id = resultSet.getInt("department_id");
                String position = resultSet.getString("position");
                Date hiring_date = hiring_date = resultSet.getDate("hiring_date");

                Employee employee = new Employee(employee_id, last_name, first_name, email, phone_number, address, username, password, is_admin, hiring_date, department_id, position);
                overtimeRequest.setEmployee(employee);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return overtimeRequests;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return overtimeRequests;
    }

    public static void updateOvertimeRequest(int request_id, Date date, int noOfHours, String status, String notes, int employee_id) {
        Connection conn = AccessDatabaseConnector.connect();
        try (Statement statement = conn.createStatement()) {
            String updateQuery = "Update " + OVERTIME_REQUESTS_TABLE + " SET " + DATE_COLUMN + " = '" + dateFormatter.format(date) + "', " + NO_OF_HOURS_COLUMN + " = '" + noOfHours + "', " + STATUS_COLUMN + " = '" + status + "', " + NOTES_COLUMN + " = '" + notes + "', " + EMPLOYEE_ID_COLUMN + " = '" + employee_id + "' WHERE " + REQUEST_ID_COLUMN + " = " + request_id + ";";
            System.out.println(updateQuery);
            statement.executeUpdate(updateQuery);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }
    
    public static void deleteOvertimeRequestById(int request_id) {
        Connection conn = AccessDatabaseConnector.connect();
        try (Statement statement = conn.createStatement()) {
            String deleteQuery = "DELETE FROM " + OVERTIME_REQUESTS_TABLE + " WHERE " + REQUEST_ID_COLUMN + " = " + request_id;
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }

}
