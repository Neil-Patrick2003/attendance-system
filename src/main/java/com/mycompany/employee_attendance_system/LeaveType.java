/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

/**
 *
 * @author Neil Patrick
 */
public class LeaveType {

    int leave_type_id;
    String name;
    int limit;
    int employeeBalance = 0;

    public LeaveType(int leave_type_id, String name, int limit) {
        this.leave_type_id = leave_type_id;
        this.name = name;
        this.limit = limit;
    }

    public void setEmployeeBalance(int employeeBalance) {
        this.employeeBalance = employeeBalance;
    }

}
