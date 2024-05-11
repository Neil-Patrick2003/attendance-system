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
public class AttendanceRecordService {

    public static final String ATTENDANCE_RECORDS_TABLE = "attendance_records";
    private static final String ATTENDANCE_RECORD_ID_COLUMN = "attendance_record_id";
    private static final String TIME_IN_COLUMN = "time_in";
    private static final String TIME_OUT_COLUMN = "time_out";
    private static final String EMPLOYEE_ID_COLUMN = "employee_id";

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                Date timein = null;
                Date timeOut = null;

                if (resultSet.getString(TIME_IN_COLUMN) != null) {
                    try {
                        timein = dateFormatter.parse(resultSet.getString(TIME_IN_COLUMN));
                    } catch (Exception e) {
                    }
                }

                if (resultSet.getString(TIME_OUT_COLUMN) != null) {
                    try {
                        timeOut = dateFormatter.parse(resultSet.getString(TIME_OUT_COLUMN));
                    } catch (Exception e) {
                    }
                }

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

    public static List getRecords(Date start_date, Date end_date) {
        String sqlScript
                = "WITH RECURSIVE DateRange AS ("
                + "  SELECT "
                + "    DATE '2024-05-01' AS dateRef "
                + "  UNION ALL "
                + "  SELECT "
                + "    dateRef + INTERVAL '1' DAY "
                + "  FROM "
                + "    DateRange "
                + "  WHERE "
                + "    dateRef < DATE '2024-05-15'"
                + ") "
                + "SELECT "
                + "  employees.*, "
                + "  dateRef as date, "
                + "  ( "
                + "    select "
                + "      attendance_records.time_in "
                + "    from "
                + "      attendance_records "
                + "    where "
                + "      attendance_records.employee_id = employees.employee_id "
                + "      and DATE(attendance_records.time_in) = dateRef is not null "
                + "    LIMIT "
                + "      1 "
                + "  ) as time_in, "
                + "  ( "
                + "    select "
                + "      attendance_records.time_out "
                + "    from "
                + "      attendance_records "
                + "    where "
                + "      attendance_records.employee_id = employees.employee_id "
                + "      and DATE(attendance_records.time_in) = dateRef is not null "
                + "    LIMIT "
                + "      1 "
                + "  ) as time_out, "
                + "  ( "
                + "    Case when ( "
                + "      select "
                + "        exists ( "
                + "          select "
                + "            * "
                + "          from "
                + "            leave_requests "
                + "          where "
                + "            leave_requests.employee_id = employees.employee_id "
                + "            and dateRef <= leave_requests.end_date "
                + "            and dateRef >= leave_requests.start_date "
                + "        ) "
                + "    ) THEN 'On leave' when ( "
                + "      dateRef > CURDATE() "
                + "    ) THEN null when ( "
                + "      select "
                + "        exists ( "
                + "          select "
                + "            * "
                + "          from "
                + "            attendance_records "
                + "          where "
                + "            attendance_records.employee_id = employees.employee_id "
                + "            and DATE(attendance_records.time_in) = dateRef "
                + "        ) "
                + "    ) THEN 'Present' when ( "
                + "      select "
                + "        not exists ( "
                + "          select "
                + "            * "
                + "          from "
                + "            attendance_records "
                + "          where "
                + "            attendance_records.employee_id = employees.employee_id "
                + "            and DATE(attendance_records.time_in) = dateRef "
                + "        ) "
                + "    ) THEN 'Absent' ELSE null END "
                + "  ) as remarks, "
                + "  case when ( "
                + "    select "
                + "      exists ( "
                + "        select "
                + "          * "
                + "        from "
                + "          attendance_records "
                + "        where "
                + "          attendance_records.employee_id = employees.employee_id "
                + "          and DATE(attendance_records.time_in) = dateRef "
                + "          and attendance_records.time_out is not null "
                + "      ) "
                + "  ) THEN ( "
                + "    select "
                + "      ROUND( "
                + "        TIMESTAMPDIFF( "
                + "          SECOND, attendance_records.time_in, "
                + "          attendance_records.time_out "
                + "        ) / 3600.0, "
                + "        2 "
                + "      ) "
                + "    from "
                + "      attendance_records "
                + "    where "
                + "      attendance_records.employee_id = employees.employee_id "
                + "      and DATE(attendance_records.time_in) = dateRef "
                + "      and attendance_records.time_out is not null "
                + "    LIMIT "
                + "      1 "
                + "  ) else null end as total_logged_hours, "
                + "  ( "
                + "    Select "
                + "      no_of_hours "
                + "    from "
                + "      overtime_requests "
                + "    where "
                + "      overtime_requests.employee_id = employees.employee_id "
                + "      and overtime_requests.`date` = dateRef "
                + "      and overtime_requests.status = 'Approved' "
                + "    LIMIT "
                + "      1 "
                + "  ) as approved_overtime_hours "
                + "FROM "
                + "  employees "
                + "  JOIN DateRange d ON 1 = 1 "
                + "order by "
                + "  dateRef";

        List<AttendanceRecordSummary> records = new ArrayList<>();

        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlScript);

            // Process the results
            while (resultSet.next()) {
                // Retrieve data from the result set
                Date date = resultSet.getDate("date");
                Date timeIn = resultSet.getDate("time_in");
                Date timeOut = resultSet.getDate("time_out");
                String remarks = resultSet.getString("remarks");
                float totalLoggedHours = resultSet.getFloat("total_logged_hours");
                int approvedOvertimeHours = resultSet.getInt("approved_overtime_hours");

                int employeeId = resultSet.getInt("approved_overtime_hours");
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

                Employee employee = new Employee(employeeId, last_name, first_name, email, phone_number, address, username, password, is_admin, hiring_date, department_id, position);
                AttendanceRecordSummary summary = new AttendanceRecordSummary(date, timeIn, timeOut, remarks, totalLoggedHours, approvedOvertimeHours);

                summary.setEmployee(employee);

                records.add(summary);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return records;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }

        return records;
    }
}
