/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

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
    
//    public static void addAttendanceRecord(LocalDate date, Timestamp timeIn, Timestamp timeOut, int employee_id, String status) {
//        Connection conn = AccessDatabaseConnector.connect();
//        try {
//
//            // Execute an INSERT query
//            try (Statement statement = conn.createStatement()) {
//                // Execute an INSERT query
//
//                String insertQuery = "INSERT INTO " + ATTENDANCE_RECORDS_TABLE + " (" + DATE_COLUMN + ", " + TIME_IN_COLUMN + ", " + TIME_OUT_COLUMN + ", " + EMPLOYEE_ID_COLUMN + ", " + STATUS_COLUMN  +  " ) VALUES ('" + date + "', '" + timeIn + "', '" + timeOut + "', '" + employee_id + "', '" + status  + "');";
//                System.out.println(insertQuery);
//                int rowsAffected = statement.executeUpdate(insertQuery);
//                // Check the number of rows affected
//                if (rowsAffected > 0) {
//                    System.out.println("Insertion successful. Rows affected: " + rowsAffected);
//                } else {
//                    System.out.println("Insertion failed.");
//                }
//                // Close the statement
//            }
//        } catch (SQLException e) {
//            System.out.print(e);
//        } finally {
//            AccessDatabaseConnector.closeConnection(conn);
//        }
//    }
    
}
