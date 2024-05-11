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
public class OvertimeRequest {

    int request_id;
    Date startDate;
    Date endDate;
    String status;
    String notes;
    int employee_id;
    Employee employee;

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public OvertimeRequest(int request_id, Date startDate, Date endDate, String notes, String status, int employee_id) {
        this.request_id = request_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.notes = notes;
        this.employee_id = employee_id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
