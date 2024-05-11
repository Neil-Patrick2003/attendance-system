/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.employee_attendance_system;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Neil Patrick
 */
public class Employee_attendance_system {

    public static void main(String[] args) {

        List<Employee> employees = EmployeeService.getAllEmployees();

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
//             EmployeeService.updateEmployee(employee.id, "mulingbayan", "bryan", "test", "-0-", "e", "dsadasdsa", "dasdasdsa", false, 1, "tes");

            System.out.println(employee.first_name);

            List<LeaveType> leaveTypes = LeaveTypeService.getLeaveTypesWithEmployeeBalance(employee.id);

            for (int j = 0; j < leaveTypes.size(); j++) {
                LeaveType leaveType = leaveTypes.get(j);

                System.out.println(leaveType.name + " balance: " + leaveType.employeeBalance);
            }

        }

//        LeaveTypeService.addLeaveType("Paid PTO Leave", 10);
//        LeaveTypeService.addLeaveType("Sick Leave", 10);
//        LeaveTypeService.addLeaveType("Unpaid Leave", 10);
        List<LeaveType> leaveTypes = LeaveTypeService.getAllLeaveTypes();

        for (int i = 0; i < leaveTypes.size(); i++) {
            LeaveType leaveType = leaveTypes.get(i);

            System.out.println(leaveType.name + " " + leaveType.limit);
        }

        Date start_date = new Date();
        Date end_date = new Date();

//         LeaveRequestService.addLeaveReq(start_date, end_date, "For Approval", "test", 1, 1);
        List<LeaveRequest> leaveRequests = LeaveRequestService.getAllLeaveRequests();

        for (int i = 0; i < leaveRequests.size(); i++) {
            LeaveRequest leaveRequest = leaveRequests.get(i);

            System.out.println(leaveRequest.employee.first_name);
            System.out.println(leaveRequest.leaveType.name);
            System.out.println(leaveRequest.startDate + " " + leaveRequest.endDate);
        }

//        Timestamp timeIn = new Timestamp(System.currentTimeMillis());
//        System.out.println("Current Timestamp: " + timeIn);
//        
//        Timestamp timeOut = new Timestamp(System.currentTimeMillis());
//        System.out.println("Current Timestamp: " + timeOut);
//        
//        LocalDate currentDate = LocalDate.now();
//        System.out.println("Current Date: " + currentDate);
//        
//        AttendanceRecordService.addAttendanceRecord(currentDate, timeIn, timeOut, 0, "Present");
//        JFrame frame = new JFrame("Film Equipment Rental Service");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // EmployeeService.addEmployee("mulingbayan", "neil", "neil@gmail.com", "09215046150", "magahis", "neil123456", "neil123456", true, 1, 1000.00, "hr");
        //EmployeeService.addEmployee("mulingbayan", "bryan", "bryan.mulingbayan@gmail.com", "09051396541", "magahis", "neil", "neil", true, 1, "Boss");
        LoginFrame LoginFrame = new LoginFrame();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setVisible(true);
        LoginFrame.setLocationRelativeTo(null);
    }
}
