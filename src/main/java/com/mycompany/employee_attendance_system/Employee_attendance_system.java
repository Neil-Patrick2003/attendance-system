/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.employee_attendance_system;

import java.util.Date;

/**
 *
 * @author Neil Patrick
 */
public class Employee_attendance_system {

    public static void main(String[] args) {

//        HolidayService.createHoliday(new Date(124, 0, 1), "New year");
//        List<Holiday> holidays = HolidayService.getHolidays();
//
//        for (int i = 0; i < holidays.size(); i++) {
//            Holiday holiday = holidays.get(i);
//            HolidayService.updateHolidayById(holiday.holiday_id, new Date(), "Updated");
//            HolidayService.deleteHolidayById(holiday.holiday_id);
//        }
        
//        AttendanceRecordService.getCurrentAttendanceRecordByEmployeeId(1, new Date());
        
//        List<AttendanceRecordSummary> records= AttendanceRecordService.getRecords(new Date(2024, 5, 1), new Date(2024, 5, 30));
//        
//        for (int i = 0; i < records.size(); i++) {
//            AttendanceRecordSummary record = records.get(i);
//            System.out.println("Date: " + record.getFormattedDate());
//            System.out.println("Employee ID: " + record.employee.id);
//            System.out.println("Employee Name: " + record.employee.getFullName());
//            System.out.println("Remarks: " + record.remarks);
//            System.out.println("Logged Hours: " + record.totalLoggedHours);
//            System.out.println("Paid Hours: " + record.getTotalWorkingHours());
//            System.out.println("Approved OT Hours: " + record.approvedOvertimeHours);
//            System.out.println("Rendered OT Hours: " + record.getTotalRenderedOvertime());
//        }
//
//        for (int i = 0; i < employees.size(); i++) {
//            Employee employee = employees.get(i);
//            
////            AttendanceRecordService.createAttendanceRecord(new Date(), employee.id);
//
//            System.out.println(employee.first_name);
//
////            List<LeaveType> leaveTypes = LeaveTypeService.getLeaveTypesWithEmployeeBalance(employee.id);
////
////            for (int j = 0; j < leaveTypes.size(); j++) {
////                LeaveType leaveType = leaveTypes.get(j);
////
////                System.out.println(leaveType.name + " balance: " + leaveType.employeeBalance);
////            }
//        }

//        LeaveTypeService.addLeaveType("Paid PTO Leave", 10);
//        LeaveTypeService.addLeaveType("Sick Leave", 10);
//        LeaveTypeService.addLeaveType("Unpaid Leave", 10);
//        List<LeaveType> leaveTypes = LeaveTypeService.getAllLeaveTypes();
//
//        for (int i = 0; i < leaveTypes.size(); i++) {
//            LeaveType leaveType = leaveTypes.get(i);
//
//            System.out.println(leaveType.name + " " + leaveType.limit);
//        }
        Date start_date = new Date();
        Date end_date = new Date();

//         LeaveRequestService.addLeaveReq(start_date, end_date, "For Approval", "test", 1, 1);
//        List<LeaveRequest> leaveRequests = LeaveRequestService.getAllLeaveRequests();
//
//        for (int i = 0; i < leaveRequests.size(); i++) {
//            LeaveRequest leaveRequest = leaveRequests.get(i);
//
//            System.out.println(leaveRequest.employee.first_name);
//            System.out.println(leaveRequest.leaveType.name);
//            System.out.println(leaveRequest.startDate + " " + leaveRequest.endDate);
//        }

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
