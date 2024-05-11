/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author bryanmulingbayan
 */
public class AttendanceRecordSummary {

    Date date;
    Date timeIn;
    Date timeOut;
    String remarks;
    float totalLoggedHours;
    int approvedOvertimeHours;
    Employee employee;

    public static final int WORKING_HOURS = 9;

    public static final SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public AttendanceRecordSummary(Date date, Date timeIn, Date timeOut, String remarks, float totalLoggedHours, int approvedOvertimeHours) {
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.remarks = remarks;
        this.totalLoggedHours = totalLoggedHours;
        this.approvedOvertimeHours = approvedOvertimeHours;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getFormattedDate() {
        return dayFormatter.format(this.date);
    }

    public float getTotalWorkingHours() {
        int totalRequiredHours = WORKING_HOURS + this.approvedOvertimeHours;
        
        return this.totalLoggedHours > totalRequiredHours ? totalRequiredHours : this.totalLoggedHours;
    }

    public float getTotalRenderedOvertime() {
        if (this.approvedOvertimeHours > 0) {
            return this.getExceededWorkingHours() > this.approvedOvertimeHours ? this.approvedOvertimeHours : this.getExceededWorkingHours();
        }

        return 0;
    }

    public float getExceededWorkingHours() {
        return this.totalLoggedHours > WORKING_HOURS ? this.totalLoggedHours - WORKING_HOURS : 0;
    }
}
