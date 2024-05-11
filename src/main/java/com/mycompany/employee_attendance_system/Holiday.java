/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_attendance_system;

import java.util.Date;

/**
 *
 * @author bryanmulingbayan
 */
public class Holiday {

    int holiday_id;
    String holiday_name;
    Date holiday_date;

    public Holiday(int holiday_id, String holiday_name, Date holiday_date) {
        this.holiday_id = holiday_id;
        this.holiday_name = holiday_name;
        this.holiday_date = holiday_date;
    }

}
