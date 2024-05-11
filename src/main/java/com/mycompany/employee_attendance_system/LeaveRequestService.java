/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

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
public class LeaveRequestService {

    public static final String LEAVE_REQUESTS_TABLE = "leave_requests";
    public static final String REQUEST_ID_COLUMN = "request_id";
    public static final String START_DATE_COLUMN = "start_date";
    public static final String END_DATE_COLUMN = "end_date";
    public static final String STATUS_COLUMN = "status";
    public static final String NOTES_COLUMN = "notes";
    public static final String LEAVE_TYPE_ID_COLUMN = "leave_type_id";
    public static final String EMPLOYEE_ID_COLUMN = "employee_id";

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void addLeaveReq(Date startDate, Date endDate, String status, String notes, int leave_type_id, int employee_id) {
        Connection conn = AccessDatabaseConnector.connect();
        try {

            // Execute an INSERT query
            try (Statement statement = conn.createStatement()) {
                // Execute an INSERT query
                String insertQuery = "INSERT INTO " + LEAVE_REQUESTS_TABLE + " (" + START_DATE_COLUMN + ", " + END_DATE_COLUMN + ", " + STATUS_COLUMN + ", " + NOTES_COLUMN + ", " + LEAVE_TYPE_ID_COLUMN + ", " + EMPLOYEE_ID_COLUMN + " ) VALUES ('" + dateFormatter.format(startDate) + "', '" + dateFormatter.format(endDate) + "', '" + status + "', '" + notes + "', '" + leave_type_id + "', '" + employee_id + "');";
                System.out.println(insertQuery);
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

    public static List getAllLeaveRequests() {
        List<LeaveRequest> leaveRequests = new ArrayList<>();

        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            // Execute a SELECT query
            String selectQuery = "SELECT leave_requests.*, employees.*, leave_types.* "
                    + "FROM leave_requests "
                    + "JOIN employees ON leave_requests.employee_id = employees.employee_id "
                    + "JOIN leave_types ON leave_requests.leave_type_id = leave_types.leave_type_id;";

            System.out.println(selectQuery);

            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the results
            while (resultSet.next()) {

                Date startDate;
                Date endDate;

                try {
                    startDate = dateFormatter.parse(resultSet.getString(START_DATE_COLUMN));
                    endDate = dateFormatter.parse(resultSet.getString(END_DATE_COLUMN));
                } catch (Exception e) {
                    throw new Error("failed");
                }
//    
                // Retrieve data from the result set
                int request_id = resultSet.getInt(REQUEST_ID_COLUMN);
                String status = resultSet.getString(STATUS_COLUMN);
                String notes = resultSet.getString(NOTES_COLUMN);
                int leave_type_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);
                int employee_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);

                LeaveRequest leaveRequest = new LeaveRequest(request_id, startDate, endDate, notes, status, leave_type_id, employee_id);
                leaveRequests.add(leaveRequest);

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
                leaveRequest.setEmployee(employee);

                String leave_type_name = resultSet.getString("name");
                int leave_type_limit = resultSet.getInt("leave_limit");

                LeaveType leaveType = new LeaveType(leave_type_id, leave_type_name, leave_type_limit);
                leaveRequest.setLeaveType(leaveType);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return leaveRequests;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return leaveRequests;
    }

    public static LeaveRequest getLeaveRequestById(int leaveRequestId) {
        Connection conn = AccessDatabaseConnector.connect();
        LeaveRequest leaveRequest = null;
        try {
            Statement statement = conn.createStatement();

            // Execute a SELECT query
            String selectQuery = "SELECT leave_requests.*, employees.*, leave_types.* "
                    + "FROM leave_requests "
                    + "JOIN employees ON leave_requests.employee_id = employees.employee_id "
                    + "JOIN leave_types ON leave_requests.leave_type_id = leave_types.leave_type_id "
                    + "WHERE leave_requests.request_id = " + leaveRequestId + " LIMIT 1;";

            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the results
            while (resultSet.next()) {

                Date startDate;
                Date endDate;

                try {
                    startDate = dateFormatter.parse(resultSet.getString(START_DATE_COLUMN));
                    endDate = dateFormatter.parse(resultSet.getString(END_DATE_COLUMN));
                } catch (Exception e) {
                    throw new Error("failed");
                }
//    
                // Retrieve data from the result set
                int request_id = resultSet.getInt(REQUEST_ID_COLUMN);
                String status = resultSet.getString(STATUS_COLUMN);
                String notes = resultSet.getString(NOTES_COLUMN);
                int leave_type_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);
                int employee_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);

                leaveRequest = new LeaveRequest(request_id, startDate, endDate, notes, status, leave_type_id, employee_id);

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
                leaveRequest.setEmployee(employee);

                String leave_type_name = resultSet.getString("name");
                int leave_type_limit = resultSet.getInt("leave_limit");

                LeaveType leaveType = new LeaveType(leave_type_id, leave_type_name, leave_type_limit);
                leaveRequest.setLeaveType(leaveType);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return leaveRequest;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return leaveRequest;
    }

    public static List getAllLeaveRequestsByEmployeeId(int employeeId) {
        List<LeaveRequest> leaveRequests = new ArrayList<>();

        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            // Execute a SELECT query
            String selectQuery = "SELECT leave_requests.*, employees.*, leave_types.* "
                    + "FROM leave_requests "
                    + "JOIN employees ON leave_requests.employee_id = employees.employee_id "
                    + "JOIN leave_types ON leave_requests.leave_type_id = leave_types.leave_type_id "
                    + "WHERE leave_requests.employee_id = " + employeeId + ";";

            System.out.println(selectQuery);

            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the results
            while (resultSet.next()) {

                Date startDate;
                Date endDate;

                try {
                    startDate = dateFormatter.parse(resultSet.getString(START_DATE_COLUMN));
                    endDate = dateFormatter.parse(resultSet.getString(END_DATE_COLUMN));
                } catch (Exception e) {
                    throw new Error("failed");
                }
//    
                // Retrieve data from the result set
                int request_id = resultSet.getInt(REQUEST_ID_COLUMN);
                String status = resultSet.getString(STATUS_COLUMN);
                String notes = resultSet.getString(NOTES_COLUMN);
                int leave_type_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);
                int employee_id = resultSet.getInt(EMPLOYEE_ID_COLUMN);

                LeaveRequest leaveRequest = new LeaveRequest(request_id, startDate, endDate, notes, status, leave_type_id, employee_id);
                leaveRequests.add(leaveRequest);

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
                leaveRequest.setEmployee(employee);

                String leave_type_name = resultSet.getString("name");
                int leave_type_limit = resultSet.getInt("leave_limit");

                LeaveType leaveType = new LeaveType(leave_type_id, leave_type_name, leave_type_limit);
                leaveRequest.setLeaveType(leaveType);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return leaveRequests;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return leaveRequests;
    }

    public static void updateLeaveRequest(int request_id, Date startDate, Date endDate, String status, String notes, int leave_type_id, int employee_id) {
        Connection conn = AccessDatabaseConnector.connect();
        try (Statement statement = conn.createStatement()) {
            String updateQuery = "Update " + LEAVE_REQUESTS_TABLE + " SET " + START_DATE_COLUMN + " = '" + dateFormatter.format(startDate) + "', " + END_DATE_COLUMN + " = '" + dateFormatter.format(endDate) + "', " + STATUS_COLUMN + " = '" + status + "', " + NOTES_COLUMN + " = '" + notes + "', " + LEAVE_TYPE_ID_COLUMN + " = '" + leave_type_id + "', " + EMPLOYEE_ID_COLUMN + " = '" + employee_id + "' WHERE " + REQUEST_ID_COLUMN + " = " + request_id + ";";
            System.out.println(updateQuery);
            statement.executeUpdate(updateQuery);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }

}
