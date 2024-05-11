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
import java.util.Date;

/**
 *
 * @author Neil Patrick
 */
public class AttendanceRecordService {

    public static final String ATTENDANCE_RECORDS_TABLE = "attendance_records";
    private static final String ATTENDANCE_RECORD_ID_COLUMN = "attendance_record_id";
    private static final String TIME_IN_COLUMN = "time_in";
    private static final String TIME_OUT_COLUMN = "time_out";
    private static final String EMPLOYEE_ID_COLUMN = "employee_id";

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static final SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void createAttendanceRecord(Date timeIn, int employee_id) {
        Connection conn = AccessDatabaseConnector.connect();
        try {

            // Execute an INSERT query
            try (Statement statement = conn.createStatement()) {
                // Execute an INSERT query

                String insertQuery = "INSERT INTO " + ATTENDANCE_RECORDS_TABLE + " (" + TIME_IN_COLUMN + ", " + EMPLOYEE_ID_COLUMN + " ) VALUES ('" + dateFormatter.format(timeIn) + "', '" + employee_id + "');";
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

    public static AttendanceRecord getCurrentAttendanceRecordByEmployeeId(int employee_id, Date date) {
        String selectQuery = "SELECT * FROM " + ATTENDANCE_RECORDS_TABLE + " WHERE " + EMPLOYEE_ID_COLUMN + " = '" + employee_id + "' AND (time_out is null OR ( time_out is not null and DATE(time_in) = '" + dayFormatter.format(date) + "' )) LIMIT 1;";
        System.out.println(selectQuery);
        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(selectQuery);

            AttendanceRecord attendanceRecord = null;

            // Process the results
            while (resultSet.next()) {
                int recordId = resultSet.getInt(ATTENDANCE_RECORD_ID_COLUMN);
                Date timein = resultSet.getDate(TIME_IN_COLUMN);
                Date timeOut = resultSet.getDate(TIME_OUT_COLUMN);

                attendanceRecord = new AttendanceRecord(recordId, timein, timeOut, employee_id);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return attendanceRecord;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return null;
    }

    public static void updateAttendanceRecord(int record_id, Date timeout) {
        Connection conn = AccessDatabaseConnector.connect();
        try (Statement statement = conn.createStatement()) {
            String updateQuery = "Update " + ATTENDANCE_RECORDS_TABLE + " SET " + TIME_OUT_COLUMN + " = '" + dateFormatter.format(timeout) + "' WHERE " + ATTENDANCE_RECORD_ID_COLUMN + " = " + record_id + ";";
            System.out.println(updateQuery);
            statement.executeUpdate(updateQuery);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions 
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }

}
