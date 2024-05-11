/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author Neil Patrick
 */
public class AttendanceRecord {

    int attendance_record_id;
    Date timeIn;
    Date timeOut;
    int employee_id;
    Employee employee;

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AttendanceRecord(int attendance_record_id, Date timeIn, Date timeOut, int employee_id) {
        this.attendance_record_id = attendance_record_id;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.employee_id = employee_id;
    }

    public String getFormattedTimeIn() {
        return dateFormatter.format(this.timeIn);
    }

    public String getFormattedTimeOut() {
        return dateFormatter.format(this.timeOut);
    }
}
