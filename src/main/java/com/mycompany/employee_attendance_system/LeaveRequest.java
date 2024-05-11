/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Neil Patrick
 */
public class LeaveRequest {

    int request_id;
    Date startDate;
    Date endDate;
    String status;
    String notes;
    int leave_type_id;
    int employee_id;
    Employee employee;
    LeaveType leaveType;
    
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");


    public LeaveRequest(int request_id, Date startDate, Date endDate, String notes, String status, int leave_type_id, int employee_id) {
        this.request_id = request_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.notes = notes;
        this.leave_type_id = leave_type_id;
        this.employee_id = employee_id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    
    public String getFormattedStartDate() {
        return dateFormatter.format(this.startDate);
    }
    
    public String getFormattedEndDate() {
        return dateFormatter.format(this.endDate);
    }
    
    public int getDuration () {
        long diff = (long) this.endDate.getTime() - this.startDate.getTime();
        
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
    }
    
}
