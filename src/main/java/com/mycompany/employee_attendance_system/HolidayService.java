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
public class HolidayService {

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static void createHoliday(Date holidate_date, String holiday_name) {
        Connection conn = AccessDatabaseConnector.connect();
        try {

            // Execute an INSERT query
            try (Statement statement = conn.createStatement()) {
                // Execute an INSERT query
                String insertQuery = "INSERT INTO holidays (holiday_date, holiday_name) VALUES ('" + dateFormatter.format(holidate_date) + "', '" + holiday_name + "');";
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

    public static List getHolidays() {
        List<Holiday> holidays = new ArrayList<>();

        Connection conn = AccessDatabaseConnector.connect();
        try {
            Statement statement = conn.createStatement();

            // Execute a SELECT query
            String selectQuery = "SELECT * FROM holidays";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Process the results
            while (resultSet.next()) {
                // Retrieve data from the result set
                int id = resultSet.getInt("holiday_id");
                String name = resultSet.getString("holiday_name");
                Date date = resultSet.getDate("holiday_date");
                Holiday holiday = new Holiday(id, name, date);
                holidays.add(holiday);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            return holidays;
        } catch (SQLException e) {
            System.out.print(e);
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
        return holidays;

    }

    public static void updateHolidayById(int holiday_id, Date holiday_date, String holiday_name) {
        Connection conn = AccessDatabaseConnector.connect();
        try (Statement statement = conn.createStatement()) {
            String updateQuery = "Update holidays SET holiday_date = '" + dateFormatter.format(holiday_date) + "', holiday_name = '" + holiday_name + "' WHERE holiday_id = " + holiday_id + ";";
            System.out.println(updateQuery);
            statement.executeUpdate(updateQuery);

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }

    public static void deleteHolidayById(int holidayId) {
        Connection conn = AccessDatabaseConnector.connect();
        try (Statement statement = conn.createStatement()) {
            String deleteQuery = "DELETE FROM holidays WHERE holiday_id = " + holidayId;
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        } finally {
            AccessDatabaseConnector.closeConnection(conn);
        }
    }

}
