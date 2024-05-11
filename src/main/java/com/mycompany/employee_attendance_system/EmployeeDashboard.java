/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.employee_attendance_system;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Neil Patrick
 */
public class EmployeeDashboard extends javax.swing.JFrame {
    
    Employee authenticatedEmployee;
    Employee selectedEmployee;
    Holiday selectedHoliday;
    AttendanceRecord currentAttendanceRecord;
    List<Employee> employees = new ArrayList<>();

    /**
     * Creates new form EmployeeDashboard
     */
    public EmployeeDashboard() {
        initComponents();
        summaryRecordStartdate.setDate(new Date(new Date().getYear(), new Date().getMonth(), 1));
        summaryRecordEndDate.setDate(new Date());
        
        refreshEmployeeList();
        refreshAuthEmployee();
        refreshAdminOvertimeRequestList();
        
        if (this.authenticatedEmployee != null) {
            employeeRefreshLeaveRequestList();
        }
        
        List<Department> departments = DepartmentService.getAllDepartments();
        
        for (int i = 0; i < departments.size(); i++) {
            updateProfileDeptCombobox.addItem(departments.get(i).department_name);
            adminUpdateDeptBox.addItem(departments.get(i).department_name);
        }

        //refreshLeaveRequestList();
    }
    
    public void setAuthenticatedEmployee(Employee employee) {
        this.authenticatedEmployee = employee;
        employeeRefreshLeaveRequestList();
        NameLabel.setText(authenticatedEmployee.last_name + ", " + authenticatedEmployee.first_name + ".");
        Position_Label.setText(authenticatedEmployee.position);
        LastNameText.setText(authenticatedEmployee.last_name);
        FirstNameText.setText(authenticatedEmployee.first_name);
        EmailText.setText(authenticatedEmployee.email);
        PhoneNumberText.setText(authenticatedEmployee.phone_number);
        AddressText.setText(authenticatedEmployee.address);
        PositionText.setText(authenticatedEmployee.position);
        updateProfileDeptCombobox.setSelectedItem(this.authenticatedEmployee.department.department_name);
        adminUpdateDeptBox.setSelectedItem(this.authenticatedEmployee.department.department_name);
        updateProfileSubmitBtn.setVisible(false);
        
        this.refreshCurrentAttendanceRecord();
        DefaultListModel leaveBalanceListModel = new DefaultListModel();
        
        String Title = "  Leave Balances";
        
        leaveBalanceListModel.addElement(Title);
        
        List<LeaveType> leaveTypes = LeaveTypeService.getLeaveTypesWithEmployeeBalance(this.authenticatedEmployee.id);
        System.out.println("size  " + leaveTypes.size());
        for (int i = 0; i < leaveTypes.size(); i++) {
            LeaveType type = leaveTypes.get(i);
            leaveBalanceListModel.addElement("  " + type.name + "  :     " + type.employeeBalance + " balance");
        }
        leaveBalanceList.setModel(leaveBalanceListModel);
        
        DefaultListModel profileInformationListModel = new DefaultListModel();
        
        profileInformationListModel.addElement("  " + authenticatedEmployee.getFullName());
        
        profileInformationListModel.addElement("  " + authenticatedEmployee.email);
        profileInformationListModel.addElement("  " + authenticatedEmployee.phone_number);
        profileInformationListModel.addElement("  " + authenticatedEmployee.department.department_name);
        profileInformationListModel.addElement("  " + authenticatedEmployee.hiring_date);
        profileInformationListModel.addElement("  " + authenticatedEmployee.position);
        profileInformationListModel.addElement("  " + authenticatedEmployee.username);
        profileInformationListModel.addElement("  ");
        profileInformationListModel.addElement("  " + authenticatedEmployee.hiring_date);
        
        MyInformationList.setModel(profileInformationListModel);
        
    }
    
    public void refreshCurrentAttendanceRecord() {
        this.currentAttendanceRecord = AttendanceRecordService.getCurrentAttendanceRecordByEmployeeId(this.authenticatedEmployee.id, new Date());
        
        if (this.currentAttendanceRecord == null) {
            attendanceButton.setText("Time In");
            attendanceButton.setEnabled(true);
            attendanceSummaryLabel.setText("You dont have attendance record today. Dont forget to time in.");
        } else {
            if (this.currentAttendanceRecord.timeOut == null) {
                attendanceButton.setText("Time Out");
                attendanceButton.setEnabled(true);
                attendanceSummaryLabel.setText("Dont forget to time out today. Time IN: " + this.currentAttendanceRecord.getFormattedTimeIn());
            } else {
                attendanceButton.setText("Time In");
                attendanceButton.setEnabled(false);
                attendanceSummaryLabel.setText("Time IN: " + this.currentAttendanceRecord.getFormattedTimeIn() + " Time Out: " + this.currentAttendanceRecord.getFormattedTimeOut());
            }
        }
        
        this.validate();
    }
    
    private void refreshAdminOvertimeRequestList() {
        DefaultTableModel adminOvertimeRequesttTableModel = (DefaultTableModel) adminOvertimeListTable.getModel();
        List<OvertimeRequest> overtimeRequests = OvertimeRequestService.getOvertimeRequests();
        adminOvertimeRequesttTableModel.setRowCount(0);
        
        for (int i = 0; i < overtimeRequests.size(); i++) {
            OvertimeRequest overtimeRequest = overtimeRequests.get(i);
            Object[] rowData = {overtimeRequest.request_id, overtimeRequest.date, overtimeRequest.noOfHours, overtimeRequest.status, overtimeRequest.notes, overtimeRequest.employee.getFullName()};
            adminOvertimeRequesttTableModel.addRow(rowData);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LeftPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DashboardButton = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LeaveButton = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        MyProfileButton = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AdminButton = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        OverTimeButton = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        RIghtPanelTabbed = new javax.swing.JTabbedPane();
        DashboradTab = new javax.swing.JPanel();
        attendancePanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        attendanceButton = new javax.swing.JButton();
        attendanceSummaryLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        Position_Label = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        leaveBalanceList = new javax.swing.JList<>();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        MyInformationList = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        ProfileTab = new javax.swing.JPanel();
        FirstNameLabel = new javax.swing.JPanel();
        LastNameLabel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        EmailLabel = new javax.swing.JLabel();
        NumberLabel = new javax.swing.JLabel();
        AddressLabel = new javax.swing.JLabel();
        PositionLabel = new javax.swing.JLabel();
        DepartmentLabel = new javax.swing.JLabel();
        FirstNameText = new javax.swing.JTextField();
        LastNameText = new javax.swing.JTextField();
        EmailText = new javax.swing.JTextField();
        PhoneNumberText = new javax.swing.JTextField();
        AddressText = new javax.swing.JTextField();
        PositionText = new javax.swing.JTextField();
        updateProfileDeptCombobox = new javax.swing.JComboBox<>();
        TItleLabel = new javax.swing.JLabel();
        updateProfileEditBtn = new javax.swing.JButton();
        updateProfileSubmitBtn = new javax.swing.JButton();
        LeaveTabbed = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeRequestLeaveTable = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        AddLeaveReqButton = new javax.swing.JButton();
        AdminDashboradTab = new javax.swing.JTabbedPane();
        EmployeeTab = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        AdminEmployeesTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        LeaveRequestTab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        LeaveRequestAdminTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        OverTimeListTab = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        adminOvertimeListTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        adminReportTab = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        AttendanceSummaryTable = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        summaryRecordStartdate = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        summaryRecordEndDate = new com.toedter.calendar.JDateChooser();
        EmployeeFilterComboBox = new javax.swing.JComboBox<>();
        HolidayTab = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        holidayInputName = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        holidayDatePicker = new com.toedter.calendar.JDateChooser();
        editHolidayButton = new javax.swing.JButton();
        deleteHolidayButton = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        HolidayTable = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        DepartmentTab = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        DepartmentTable = new javax.swing.JTable();
        departmentFormLabel = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        departmentNameTex = new javax.swing.JTextField();
        departmentDeleteBtn = new javax.swing.JButton();
        departmentSubmitBtn = new javax.swing.JButton();
        adminManageEmployee = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        HiringdateChooser = new com.toedter.calendar.JDateChooser();
        jLabel35 = new javax.swing.JLabel();
        adminUpdateDeptBox = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        adminUpdatePositionText = new javax.swing.JTextField();
        isAdminTextBox = new javax.swing.JCheckBox();
        jLabel33 = new javax.swing.JLabel();
        adminEdtEmpBtn1 = new javax.swing.JButton();
        adminEdtEmpBtn = new javax.swing.JButton();
        viewEmployeeLastNameLabel = new javax.swing.JLabel();
        viewEmployeeFirstNameLabel = new javax.swing.JLabel();
        viewEmployeeEmailLabel = new javax.swing.JLabel();
        viewEmployeePhoneNumberLabel = new javax.swing.JLabel();
        viewEmployeeAddressLabel = new javax.swing.JLabel();
        OvertimeTab = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        employeeOvertimeTable = new javax.swing.JTable();
        addOvertimeRequestBtn = new javax.swing.JButton();
        OvertimeFormTab = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        OvertimeNotesText = new javax.swing.JTextField();
        OvertimeDateChooser = new com.toedter.calendar.JDateChooser();
        OvertimeHourCmboBx = new javax.swing.JComboBox<>();
        SubmitOvertime = new javax.swing.JButton();
        Departmenttab = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(220, 220, 220));
        setMinimumSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LeftPanel.setBackground(new java.awt.Color(30, 144, 255));
        LeftPanel.setMaximumSize(new java.awt.Dimension(200, 600));
        LeftPanel.setMinimumSize(new java.awt.Dimension(200, 600));
        LeftPanel.setPreferredSize(new java.awt.Dimension(200, 600));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-user-90.png"))); // NOI18N

        DashboardButton.setBackground(new java.awt.Color(100, 206, 250));
        DashboardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardButtonMouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dashboard");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-dashboard-50 (1).png"))); // NOI18N

        javax.swing.GroupLayout DashboardButtonLayout = new javax.swing.GroupLayout(DashboardButton);
        DashboardButton.setLayout(DashboardButtonLayout);
        DashboardButtonLayout.setHorizontalGroup(
            DashboardButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DashboardButtonLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        DashboardButtonLayout.setVerticalGroup(
            DashboardButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboardButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LeaveButton.setBackground(new java.awt.Color(0, 200, 255));
        LeaveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LeaveButtonMouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-airplane-take-off-30 (2).png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Leave");

        javax.swing.GroupLayout LeaveButtonLayout = new javax.swing.GroupLayout(LeaveButton);
        LeaveButton.setLayout(LeaveButtonLayout);
        LeaveButtonLayout.setHorizontalGroup(
            LeaveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeaveButtonLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        LeaveButtonLayout.setVerticalGroup(
            LeaveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeaveButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LeaveButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LeaveButtonLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(LeaveButtonLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))))
        );

        MyProfileButton.setBackground(new java.awt.Color(0, 200, 255));
        MyProfileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MyProfileButtonMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("My Profile");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-test-account-30 (1).png"))); // NOI18N

        javax.swing.GroupLayout MyProfileButtonLayout = new javax.swing.GroupLayout(MyProfileButton);
        MyProfileButton.setLayout(MyProfileButtonLayout);
        MyProfileButtonLayout.setHorizontalGroup(
            MyProfileButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MyProfileButtonLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MyProfileButtonLayout.setVerticalGroup(
            MyProfileButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MyProfileButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MyProfileButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        AdminButton.setBackground(new java.awt.Color(0, 200, 255));
        AdminButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminButtonMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Admin");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-cog-20.png"))); // NOI18N

        javax.swing.GroupLayout AdminButtonLayout = new javax.swing.GroupLayout(AdminButton);
        AdminButton.setLayout(AdminButtonLayout);
        AdminButtonLayout.setHorizontalGroup(
            AdminButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminButtonLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdminButtonLayout.setVerticalGroup(
            AdminButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AdminButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addContainerGap())
        );

        OverTimeButton.setBackground(new java.awt.Color(0, 200, 255));
        OverTimeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OverTimeButtonMouseClicked(evt);
            }
        });

        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-timesheet-20.png"))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Overtime");

        javax.swing.GroupLayout OverTimeButtonLayout = new javax.swing.GroupLayout(OverTimeButton);
        OverTimeButton.setLayout(OverTimeButtonLayout);
        OverTimeButtonLayout.setHorizontalGroup(
            OverTimeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OverTimeButtonLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        OverTimeButtonLayout.setVerticalGroup(
            OverTimeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OverTimeButtonLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(OverTimeButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(OverTimeButtonLayout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout LeftPanelLayout = new javax.swing.GroupLayout(LeftPanel);
        LeftPanel.setLayout(LeftPanelLayout);
        LeftPanelLayout.setHorizontalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DashboardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MyProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(LeaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(AdminButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(OverTimeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LeftPanelLayout.setVerticalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(DashboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(LeaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(MyProfileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(OverTimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(AdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        getContentPane().add(LeftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 179, 580));

        DashboradTab.setBackground(new java.awt.Color(220, 220, 220));

        attendancePanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(135, 206, 235));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-attendance-30.png"))); // NOI18N
        jLabel10.setText("jLabel10");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Attendance");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        attendanceButton.setBackground(new java.awt.Color(30, 144, 255));
        attendanceButton.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        attendanceButton.setForeground(new java.awt.Color(225, 225, 225));
        attendanceButton.setText("Time in");
        attendanceButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));
        attendanceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attendanceButtonMouseClicked(evt);
            }
        });

        attendanceSummaryLabel.setText("Attendance");

        javax.swing.GroupLayout attendancePanelLayout = new javax.swing.GroupLayout(attendancePanel);
        attendancePanel.setLayout(attendancePanelLayout);
        attendancePanelLayout.setHorizontalGroup(
            attendancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attendancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(attendanceSummaryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(attendanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        attendancePanelLayout.setVerticalGroup(
            attendancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attendancePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(attendancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(attendanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attendanceSummaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        NameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NameLabel.setForeground(new java.awt.Color(102, 102, 102));
        NameLabel.setText("Surname, Firstname MI.");

        Position_Label.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Position_Label.setForeground(new java.awt.Color(51, 51, 51));
        Position_Label.setText("Position");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(135, 206, 235));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-empty-hourglass-30.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Leave Balances");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        leaveBalanceList.setBackground(new java.awt.Color(255, 255, 255));
        leaveBalanceList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        leaveBalanceList.setForeground(new java.awt.Color(102, 102, 102));
        leaveBalanceList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(leaveBalanceList);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(135, 206, 235));

        jLabel14.setText("jLabel12");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("My Information");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MyInformationList.setBackground(new java.awt.Color(255, 255, 255));
        MyInformationList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MyInformationList.setForeground(new java.awt.Color(102, 102, 102));
        jScrollPane7.setViewportView(MyInformationList);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7)
                .addContainerGap())
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-test-account-60 (2).png"))); // NOI18N

        javax.swing.GroupLayout DashboradTabLayout = new javax.swing.GroupLayout(DashboradTab);
        DashboradTab.setLayout(DashboradTabLayout);
        DashboradTabLayout.setHorizontalGroup(
            DashboradTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboradTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboradTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DashboradTabLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DashboradTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NameLabel)
                            .addComponent(Position_Label))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(DashboradTabLayout.createSequentialGroup()
                        .addGroup(DashboradTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(DashboradTabLayout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addComponent(attendancePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        DashboradTabLayout.setVerticalGroup(
            DashboradTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboradTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashboradTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DashboradTabLayout.createSequentialGroup()
                        .addComponent(NameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Position_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(attendancePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(DashboradTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        RIghtPanelTabbed.addTab("dashboard", DashboradTab);

        ProfileTab.setBackground(new java.awt.Color(204, 204, 204));

        FirstNameLabel.setBackground(new java.awt.Color(255, 255, 255));

        LastNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LastNameLabel.setForeground(new java.awt.Color(102, 102, 102));
        LastNameLabel.setText("Last Name                   : ");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("First Name                  : ");

        EmailLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        EmailLabel.setForeground(new java.awt.Color(102, 102, 102));
        EmailLabel.setText("Email                           :     ");

        NumberLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NumberLabel.setForeground(new java.awt.Color(102, 102, 102));
        NumberLabel.setText("Phone Number          :");

        AddressLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        AddressLabel.setForeground(new java.awt.Color(102, 102, 102));
        AddressLabel.setText("Address                       :");

        PositionLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PositionLabel.setForeground(new java.awt.Color(102, 102, 102));
        PositionLabel.setText("Position                      :");

        DepartmentLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        DepartmentLabel.setForeground(new java.awt.Color(102, 102, 102));
        DepartmentLabel.setText("Department                :");

        FirstNameText.setBackground(new java.awt.Color(255, 255, 255));
        FirstNameText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FirstNameText.setForeground(new java.awt.Color(51, 51, 51));
        FirstNameText.setText("jTextField1");
        FirstNameText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        FirstNameText.setEnabled(false);

        LastNameText.setBackground(new java.awt.Color(255, 255, 255));
        LastNameText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LastNameText.setForeground(new java.awt.Color(51, 51, 51));
        LastNameText.setText("jTextField1");
        LastNameText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        LastNameText.setEnabled(false);

        EmailText.setBackground(new java.awt.Color(255, 255, 255));
        EmailText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EmailText.setForeground(new java.awt.Color(51, 51, 51));
        EmailText.setText("jTextField1");
        EmailText.setAutoscrolls(false);
        EmailText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        EmailText.setEnabled(false);

        PhoneNumberText.setBackground(new java.awt.Color(255, 255, 255));
        PhoneNumberText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PhoneNumberText.setForeground(new java.awt.Color(51, 51, 51));
        PhoneNumberText.setText("jTextField1");
        PhoneNumberText.setAutoscrolls(false);
        PhoneNumberText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        PhoneNumberText.setEnabled(false);

        AddressText.setBackground(new java.awt.Color(255, 255, 255));
        AddressText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AddressText.setForeground(new java.awt.Color(51, 51, 51));
        AddressText.setText("jTextField1");
        AddressText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        AddressText.setEnabled(false);

        PositionText.setBackground(new java.awt.Color(255, 255, 255));
        PositionText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PositionText.setForeground(new java.awt.Color(51, 51, 51));
        PositionText.setText("jTextField1");
        PositionText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        PositionText.setEnabled(false);

        updateProfileDeptCombobox.setBackground(new java.awt.Color(255, 255, 255));
        updateProfileDeptCombobox.setForeground(new java.awt.Color(102, 102, 102));
        updateProfileDeptCombobox.setEnabled(false);

        javax.swing.GroupLayout FirstNameLabelLayout = new javax.swing.GroupLayout(FirstNameLabel);
        FirstNameLabel.setLayout(FirstNameLabelLayout);
        FirstNameLabelLayout.setHorizontalGroup(
            FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FirstNameLabelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addComponent(LastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(LastNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(71, 71, 71)
                        .addComponent(FirstNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addComponent(EmailLabel)
                        .addGap(54, 54, 54)
                        .addComponent(EmailText, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addComponent(NumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(PhoneNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(AddressText, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PositionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DepartmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PositionText, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(updateProfileDeptCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        FirstNameLabelLayout.setVerticalGroup(
            FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FirstNameLabelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(LastNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(FirstNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(EmailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(PhoneNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FirstNameLabelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(AddressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PositionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PositionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(FirstNameLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DepartmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateProfileDeptCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        TItleLabel.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        TItleLabel.setForeground(new java.awt.Color(51, 51, 51));
        TItleLabel.setText("My Personal Information");

        updateProfileEditBtn.setBackground(new java.awt.Color(135, 206, 250));
        updateProfileEditBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        updateProfileEditBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateProfileEditBtn.setText("Edit");
        updateProfileEditBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 144, 255), 1, true));
        updateProfileEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfileEditBtnActionPerformed(evt);
            }
        });

        updateProfileSubmitBtn.setBackground(new java.awt.Color(30, 144, 255));
        updateProfileSubmitBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        updateProfileSubmitBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateProfileSubmitBtn.setText("Submit");
        updateProfileSubmitBtn.setBorder(null);
        updateProfileSubmitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateProfileSubmitBtnMouseClicked(evt);
            }
        });
        updateProfileSubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProfileSubmitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ProfileTabLayout = new javax.swing.GroupLayout(ProfileTab);
        ProfileTab.setLayout(ProfileTabLayout);
        ProfileTabLayout.setHorizontalGroup(
            ProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfileTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FirstNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ProfileTabLayout.createSequentialGroup()
                        .addGroup(ProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ProfileTabLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(TItleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ProfileTabLayout.createSequentialGroup()
                                .addComponent(updateProfileEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateProfileSubmitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ProfileTabLayout.setVerticalGroup(
            ProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfileTabLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(TItleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FirstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ProfileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateProfileEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateProfileSubmitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        RIghtPanelTabbed.addTab("tab4", ProfileTab);

        LeaveTabbed.setBackground(new java.awt.Color(220, 220, 220));

        jPanel6.setBackground(new java.awt.Color(135, 206, 235));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        employeeRequestLeaveTable.setBackground(new java.awt.Color(255, 255, 255));
        employeeRequestLeaveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Request Id", "Start Date", "End Date", "Status", "Notes", "Leave Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeRequestLeaveTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeRequestLeaveTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employeeRequestLeaveTable);
        if (employeeRequestLeaveTable.getColumnModel().getColumnCount() > 0) {
            employeeRequestLeaveTable.getColumnModel().getColumn(0).setResizable(false);
            employeeRequestLeaveTable.getColumnModel().getColumn(1).setResizable(false);
            employeeRequestLeaveTable.getColumnModel().getColumn(2).setResizable(false);
            employeeRequestLeaveTable.getColumnModel().getColumn(3).setResizable(false);
            employeeRequestLeaveTable.getColumnModel().getColumn(4).setResizable(false);
            employeeRequestLeaveTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("My Time Off/Leave Request");

        AddLeaveReqButton.setBackground(new java.awt.Color(50, 205, 50));
        AddLeaveReqButton.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        AddLeaveReqButton.setForeground(new java.awt.Color(255, 255, 255));
        AddLeaveReqButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-plus-30 (2).png"))); // NOI18N
        AddLeaveReqButton.setText("Add Time Off/Leave Request");
        AddLeaveReqButton.setBorder(null);
        AddLeaveReqButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddLeaveReqButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(AddLeaveReqButton, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AddLeaveReqButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout LeaveTabbedLayout = new javax.swing.GroupLayout(LeaveTabbed);
        LeaveTabbed.setLayout(LeaveTabbedLayout);
        LeaveTabbedLayout.setHorizontalGroup(
            LeaveTabbedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeaveTabbedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        LeaveTabbedLayout.setVerticalGroup(
            LeaveTabbedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeaveTabbedLayout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        RIghtPanelTabbed.addTab("tab2", LeaveTabbed);

        AdminDashboradTab.setBackground(new java.awt.Color(204, 204, 204));

        EmployeeTab.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        AdminEmployeesTable.setBackground(new java.awt.Color(255, 255, 255));
        AdminEmployeesTable.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        AdminEmployeesTable.setForeground(new java.awt.Color(51, 51, 51));
        AdminEmployeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Last Name", "First Name", "Email", "Phone number", "Address", "User", "Is_admin", "Hiring Date", "Department id", "Position"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AdminEmployeesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminEmployeesTableMouseClicked(evt);
            }
        });
        jScrollPane.setViewportView(AdminEmployeesTable);

        jPanel3.setBackground(new java.awt.Color(135, 206, 235));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-employee-30.png"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("EMPLOYEES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(439, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout EmployeeTabLayout = new javax.swing.GroupLayout(EmployeeTab);
        EmployeeTab.setLayout(EmployeeTabLayout);
        EmployeeTabLayout.setHorizontalGroup(
            EmployeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeTabLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(EmployeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane))
                .addGap(12, 12, 12))
        );
        EmployeeTabLayout.setVerticalGroup(
            EmployeeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeTabLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        AdminDashboradTab.addTab("Employees", EmployeeTab);

        LeaveRequestTab.setBackground(new java.awt.Color(204, 204, 204));

        LeaveRequestAdminTable.setBackground(new java.awt.Color(255, 255, 255));
        LeaveRequestAdminTable.setForeground(new java.awt.Color(51, 51, 51));
        LeaveRequestAdminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Leave Request ID", "Start Date", "End Date", "Status ", "Notes", "Employee Name", "Leave type"
            }
        ));
        LeaveRequestAdminTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LeaveRequestAdminTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(LeaveRequestAdminTable);

        jPanel1.setBackground(new java.awt.Color(135, 206, 235));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-leave-30.png"))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Leave Requests");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout LeaveRequestTabLayout = new javax.swing.GroupLayout(LeaveRequestTab);
        LeaveRequestTab.setLayout(LeaveRequestTabLayout);
        LeaveRequestTabLayout.setHorizontalGroup(
            LeaveRequestTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeaveRequestTabLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(LeaveRequestTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        LeaveRequestTabLayout.setVerticalGroup(
            LeaveRequestTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeaveRequestTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        AdminDashboradTab.addTab("Leave Requests", LeaveRequestTab);

        OverTimeListTab.setBackground(new java.awt.Color(204, 204, 204));

        adminOvertimeListTable.setBackground(new java.awt.Color(255, 255, 255));
        adminOvertimeListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Request id", "Date", "Overtime hour/s", "Status", "Notes", "Employee"
            }
        ));
        adminOvertimeListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminOvertimeListTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(adminOvertimeListTable);

        jPanel5.setBackground(new java.awt.Color(135, 206, 235));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-timesheet-30.png"))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Overtime");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout OverTimeListTabLayout = new javax.swing.GroupLayout(OverTimeListTab);
        OverTimeListTab.setLayout(OverTimeListTabLayout);
        OverTimeListTabLayout.setHorizontalGroup(
            OverTimeListTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OverTimeListTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OverTimeListTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        OverTimeListTabLayout.setVerticalGroup(
            OverTimeListTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OverTimeListTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        AdminDashboradTab.addTab("Overtime", OverTimeListTab);

        adminReportTab.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        AttendanceSummaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Employee ID", "Employee Name", "Remarks", "Total Logged Hours", "Total Total Working hours ", "Approved Overtime", "Rendered "
            }
        ));
        jScrollPane5.setViewportView(AttendanceSummaryTable);

        jPanel19.setBackground(new java.awt.Color(135, 206, 250));

        jLabel43.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("ATTENDANCE REPORT");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(462, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setText("Start Date");

        summaryRecordStartdate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                summaryRecordStartdatePropertyChange(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("End Date");

        summaryRecordEndDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                summaryRecordEndDatePropertyChange(evt);
            }
        });

        EmployeeFilterComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmployeeFilterComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout adminReportTabLayout = new javax.swing.GroupLayout(adminReportTab);
        adminReportTab.setLayout(adminReportTabLayout);
        adminReportTabLayout.setHorizontalGroup(
            adminReportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(adminReportTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(adminReportTabLayout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(EmployeeFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminReportTabLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(summaryRecordStartdate, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summaryRecordEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170))
        );
        adminReportTabLayout.setVerticalGroup(
            adminReportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminReportTabLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EmployeeFilterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(adminReportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(adminReportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(summaryRecordEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(summaryRecordStartdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44))
                    .addGroup(adminReportTabLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(1, 1, 1)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AdminDashboradTab.addTab("Attendance Report", adminReportTab);

        HolidayTab.setBackground(new java.awt.Color(204, 204, 204));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        holidayInputName.setBackground(new java.awt.Color(255, 255, 255));
        holidayInputName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        holidayInputName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Holiday Name   :");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 102, 102));
        jLabel45.setText("Date");

        editHolidayButton.setBackground(new java.awt.Color(0, 153, 255));
        editHolidayButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        editHolidayButton.setForeground(new java.awt.Color(0, 0, 0));
        editHolidayButton.setText("Add");
        editHolidayButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        editHolidayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editHolidayButtonMouseClicked(evt);
            }
        });

        deleteHolidayButton.setBackground(new java.awt.Color(51, 153, 255));
        deleteHolidayButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        deleteHolidayButton.setForeground(new java.awt.Color(0, 0, 0));
        deleteHolidayButton.setText("Delete");
        deleteHolidayButton.setBorder(null);
        deleteHolidayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteHolidayButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(holidayInputName)
                    .addComponent(holidayDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editHolidayButton, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(deleteHolidayButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel17))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(holidayInputName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(deleteHolidayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(holidayDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editHolidayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        HolidayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Holiday Id", "Holiday Name", "Date"
            }
        ));
        HolidayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HolidayTableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(HolidayTable);
        if (HolidayTable.getColumnModel().getColumnCount() > 0) {
            HolidayTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel46.setBackground(new java.awt.Color(255, 255, 255));
        jLabel46.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Holiday");

        javax.swing.GroupLayout HolidayTabLayout = new javax.swing.GroupLayout(HolidayTab);
        HolidayTab.setLayout(HolidayTabLayout);
        HolidayTabLayout.setHorizontalGroup(
            HolidayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HolidayTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HolidayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HolidayTabLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        HolidayTabLayout.setVerticalGroup(
            HolidayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HolidayTabLayout.createSequentialGroup()
                .addGroup(HolidayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HolidayTabLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HolidayTabLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(HolidayTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );

        AdminDashboradTab.addTab("Holiday", HolidayTab);

        DepartmentTab.setBackground(new java.awt.Color(204, 204, 204));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setForeground(new java.awt.Color(102, 204, 255));

        jPanel20.setBackground(new java.awt.Color(135, 206, 250));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        DepartmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Department  Id", "Department Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(DepartmentTable);

        departmentFormLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        departmentFormLabel.setForeground(new java.awt.Color(51, 51, 51));
        departmentFormLabel.setText("Add Department");

        jLabel48.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 102, 102));
        jLabel48.setText("Department Name");

        departmentNameTex.setBackground(new java.awt.Color(255, 255, 255));
        departmentNameTex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        departmentDeleteBtn.setBackground(new java.awt.Color(51, 153, 255));
        departmentDeleteBtn.setForeground(new java.awt.Color(0, 0, 0));
        departmentDeleteBtn.setText("Delete");
        departmentDeleteBtn.setBorder(null);
        departmentDeleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                departmentDeleteBtnMouseClicked(evt);
            }
        });

        departmentSubmitBtn.setBackground(new java.awt.Color(51, 153, 255));
        departmentSubmitBtn.setForeground(new java.awt.Color(0, 0, 0));
        departmentSubmitBtn.setText("Create");
        departmentSubmitBtn.setBorder(null);
        departmentSubmitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                departmentSubmitBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane9)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(departmentFormLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(departmentNameTex, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(departmentSubmitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(departmentDeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(departmentFormLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(departmentNameTex, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departmentDeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departmentSubmitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DepartmentTabLayout = new javax.swing.GroupLayout(DepartmentTab);
        DepartmentTab.setLayout(DepartmentTabLayout);
        DepartmentTabLayout.setHorizontalGroup(
            DepartmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DepartmentTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        DepartmentTabLayout.setVerticalGroup(
            DepartmentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DepartmentTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        AdminDashboradTab.addTab("Departments", DepartmentTab);

        RIghtPanelTabbed.addTab("tab8", AdminDashboradTab);

        adminManageEmployee.setBackground(new java.awt.Color(204, 204, 204));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setForeground(new java.awt.Color(51, 51, 51));
        jPanel14.setFocusable(false);
        jPanel14.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        jPanel15.setBackground(new java.awt.Color(135, 206, 250));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Last Name :      ");

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("First Name :      ");

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("Email :      ");

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Phone Number :      ");

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Address :      ");

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Hiring Date :      ");

        HiringdateChooser.setBackground(new java.awt.Color(255, 255, 255));
        HiringdateChooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setText("Department  :");

        adminUpdateDeptBox.setBackground(new java.awt.Color(255, 255, 255));
        adminUpdateDeptBox.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        adminUpdateDeptBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        adminUpdateDeptBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        adminUpdateDeptBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminUpdateDeptBoxActionPerformed(evt);
            }
        });

        jLabel37.setBackground(new java.awt.Color(255, 255, 255));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setText("Position :");

        adminUpdatePositionText.setBackground(new java.awt.Color(255, 255, 255));
        adminUpdatePositionText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        adminUpdatePositionText.setText("jTextField7");
        adminUpdatePositionText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        isAdminTextBox.setBackground(new java.awt.Color(255, 255, 255));
        isAdminTextBox.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        isAdminTextBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        isAdminTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isAdminTextBoxActionPerformed(evt);
            }
        });

        jLabel33.setBackground(new java.awt.Color(255, 255, 255));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setText("Is Admin :      ");

        adminEdtEmpBtn1.setText("Cancel");
        adminEdtEmpBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminEdtEmpBtn1MouseClicked(evt);
            }
        });

        adminEdtEmpBtn.setText("Update");
        adminEdtEmpBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminEdtEmpBtnMouseClicked(evt);
            }
        });

        viewEmployeeLastNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        viewEmployeeLastNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        viewEmployeeLastNameLabel.setForeground(new java.awt.Color(102, 102, 102));
        viewEmployeeLastNameLabel.setText("viewEmployeeLastNameLabel");

        viewEmployeeFirstNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        viewEmployeeFirstNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        viewEmployeeFirstNameLabel.setForeground(new java.awt.Color(102, 102, 102));
        viewEmployeeFirstNameLabel.setText("viewEmployeeFirstNameLabel");

        viewEmployeeEmailLabel.setBackground(new java.awt.Color(255, 255, 255));
        viewEmployeeEmailLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        viewEmployeeEmailLabel.setForeground(new java.awt.Color(102, 102, 102));
        viewEmployeeEmailLabel.setText("viewEmployeeEmailLabel");

        viewEmployeePhoneNumberLabel.setBackground(new java.awt.Color(255, 255, 255));
        viewEmployeePhoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        viewEmployeePhoneNumberLabel.setForeground(new java.awt.Color(102, 102, 102));
        viewEmployeePhoneNumberLabel.setText("viewEmployeePhoneNumberLabel");

        viewEmployeeAddressLabel.setBackground(new java.awt.Color(255, 255, 255));
        viewEmployeeAddressLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        viewEmployeeAddressLabel.setForeground(new java.awt.Color(102, 102, 102));
        viewEmployeeAddressLabel.setText("viewEmployeeAddressLabel");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HiringdateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminUpdatePositionText, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminUpdateDeptBox, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                            .addComponent(adminEdtEmpBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(adminEdtEmpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel33)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(isAdminTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewEmployeeFirstNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewEmployeeLastNameLabel))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewEmployeeEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewEmployeePhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewEmployeeAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel15Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel29, jLabel30, jLabel31, jLabel32, jLabel33, jLabel34, jLabel35, jLabel37, jLabel9});

        jPanel15Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {HiringdateChooser, adminUpdateDeptBox, adminUpdatePositionText, isAdminTextBox, viewEmployeeLastNameLabel});

        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(viewEmployeeLastNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(viewEmployeeFirstNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewEmployeeEmailLabel)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewEmployeePhoneNumberLabel)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewEmployeeAddressLabel)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HiringdateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adminUpdateDeptBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminUpdatePositionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addComponent(isAdminTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminEdtEmpBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminEdtEmpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jPanel15Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel29, jLabel30, jLabel31, jLabel32, jLabel33, jLabel34, jLabel35, jLabel37, jLabel9});

        jPanel15Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {HiringdateChooser, adminUpdateDeptBox, adminUpdatePositionText, isAdminTextBox});

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout adminManageEmployeeLayout = new javax.swing.GroupLayout(adminManageEmployee);
        adminManageEmployee.setLayout(adminManageEmployeeLayout);
        adminManageEmployeeLayout.setHorizontalGroup(
            adminManageEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminManageEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        adminManageEmployeeLayout.setVerticalGroup(
            adminManageEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminManageEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        RIghtPanelTabbed.addTab("tab5", adminManageEmployee);

        OvertimeTab.setBackground(new java.awt.Color(204, 204, 204));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(0, 191, 255));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        employeeOvertimeTable.setBackground(new java.awt.Color(255, 255, 255));
        employeeOvertimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Request Id", "Date", "No. of hours", "Status", "Notes"
            }
        ));
        employeeOvertimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeOvertimeTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(employeeOvertimeTable);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
        );

        addOvertimeRequestBtn.setBackground(new java.awt.Color(0, 255, 0));
        addOvertimeRequestBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addOvertimeRequestBtn.setForeground(new java.awt.Color(255, 255, 255));
        addOvertimeRequestBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/employee_attendance_system/icons/icons8-plus-30 (2).png"))); // NOI18N
        addOvertimeRequestBtn.setText("Add Overtime Request");
        addOvertimeRequestBtn.setBorder(null);
        addOvertimeRequestBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addOvertimeRequestBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout OvertimeTabLayout = new javax.swing.GroupLayout(OvertimeTab);
        OvertimeTab.setLayout(OvertimeTabLayout);
        OvertimeTabLayout.setHorizontalGroup(
            OvertimeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OvertimeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OvertimeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(OvertimeTabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addOvertimeRequestBtn)))
                .addContainerGap())
        );
        OvertimeTabLayout.setVerticalGroup(
            OvertimeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OvertimeTabLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(addOvertimeRequestBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        RIghtPanelTabbed.addTab("tab7", OvertimeTab);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(135, 206, 250));

        jLabel39.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Overtime Request Form");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jLabel40.setBackground(new java.awt.Color(255, 255, 255));
        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Date                        :");

        jLabel41.setBackground(new java.awt.Color(255, 255, 255));
        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Number of hours   :");

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Notes                      ");

        OvertimeNotesText.setBackground(new java.awt.Color(255, 255, 255));
        OvertimeNotesText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        OvertimeHourCmboBx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OvertimeNotesText, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OvertimeHourCmboBx, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OvertimeDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(OvertimeDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OvertimeHourCmboBx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OvertimeNotesText, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(244, 244, 244))
        );

        SubmitOvertime.setBackground(new java.awt.Color(0, 191, 255));
        SubmitOvertime.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        SubmitOvertime.setText("Submit");
        SubmitOvertime.setActionCommand("OvertimeSubmitBtn");
        SubmitOvertime.setBorder(null);
        SubmitOvertime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SubmitOvertimeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(SubmitOvertime, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SubmitOvertime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout OvertimeFormTabLayout = new javax.swing.GroupLayout(OvertimeFormTab);
        OvertimeFormTab.setLayout(OvertimeFormTabLayout);
        OvertimeFormTabLayout.setHorizontalGroup(
            OvertimeFormTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        OvertimeFormTabLayout.setVerticalGroup(
            OvertimeFormTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        RIghtPanelTabbed.addTab("tab6", OvertimeFormTab);

        Departmenttab.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout DepartmenttabLayout = new javax.swing.GroupLayout(Departmenttab);
        Departmenttab.setLayout(DepartmenttabLayout);
        DepartmenttabLayout.setHorizontalGroup(
            DepartmenttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
        );
        DepartmenttabLayout.setVerticalGroup(
            DepartmenttabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );

        RIghtPanelTabbed.addTab("tab8", Departmenttab);

        getContentPane().add(RIghtPanelTabbed, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, -24, 730, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshHolidayTable() {
        DefaultTableModel holidayTableModel = (DefaultTableModel) HolidayTable.getModel();
        List<Holiday> holidays = HolidayService.getHolidays();
        holidayTableModel.setRowCount(0);

        for (int i = 0; i < holidays.size(); i++) {
            Holiday holiday = holidays.get(i);
            Object[] rowData = {holiday.holiday_id, holiday.holiday_name, holiday.holiday_date};
            holidayTableModel.addRow(rowData);
        }
    }
    
    private void refreshDepartmentTable(){
        DefaultTableModel departmentDefaultTableModel  =(DefaultTableModel) DepartmentTable.getModel();
        List<Department> departments = DepartmentService.getAllDepartments();
        departmentDefaultTableModel.setRowCount(0);
        
        for (int i = 0; i <  departments.size(); i++){
            Department department = departments.get(i);
            Object[] rowdata = {department.department_id, department.department_name};
            departmentDefaultTableModel.addRow(rowdata);
            
        }
    }

    private void refreshAttendanceRecord() {
        DefaultTableModel summaryDefaultTableModel = (DefaultTableModel) AttendanceSummaryTable.getModel();
        int employeeIdFilter = 0;
        int index = EmployeeFilterComboBox.getSelectedIndex();

        if (index > 0 && this.employees.size() >= index) {
            Employee employee = this.employees.get(index - 1);
            employeeIdFilter = employee.id;
        }

        Date start_date = summaryRecordStartdate.getDate();
        Date end_date = summaryRecordEndDate.getDate();

        if (start_date != null && end_date != null) {
            List<AttendanceRecordSummary> attendanceRecordSummarys = AttendanceRecordService.getRecords(start_date, end_date, employeeIdFilter);
            summaryDefaultTableModel.setRowCount(0);

            for (int i = 0; i < attendanceRecordSummarys.size(); i++) {
                AttendanceRecordSummary attendanceRecordSummary = attendanceRecordSummarys.get(i);
                Object[] rowData = {attendanceRecordSummary.getFormattedDate(), attendanceRecordSummary.employee.id, attendanceRecordSummary.employee.getFullName(), attendanceRecordSummary.remarks, attendanceRecordSummary.totalLoggedHours, attendanceRecordSummary.getTotalWorkingHours(), attendanceRecordSummary.approvedOvertimeHours, attendanceRecordSummary.getTotalRenderedOvertime()};
                summaryDefaultTableModel.addRow(rowData);
            }
        }

    }

    private void refreshEmployeeList() {
        DefaultTableModel employeesTableModel = (DefaultTableModel) AdminEmployeesTable.getModel();
        this.employees = EmployeeService.getAllEmployees();
        employeesTableModel.setRowCount(0);

        EmployeeFilterComboBox.removeAllItems();
        EmployeeFilterComboBox.addItem("All Employees");

        for (int i = 0; i < this.employees.size(); i++) {
            Employee employee = this.employees.get(i);
            Object[] rowData = {employee.id, employee.last_name, employee.first_name, employee.email, employee.phone_number, employee.address, employee.username, employee.is_admin, employee.hiring_date, employee.department.department_name, employee.position};
            employeesTableModel.addRow(rowData);

            // Populate filters on attendance records.
            EmployeeFilterComboBox.addItem(employee.getFullName());
        }
    }

    //refresh leave request list in admin 
    private void refreshLeaveRequestList() {
        DefaultTableModel leaveRequesttTableModel = (DefaultTableModel) LeaveRequestAdminTable.getModel();
        List<LeaveRequest> leaveRequests = LeaveRequestService.getAllLeaveRequests();
        leaveRequesttTableModel.setRowCount(0);
        
        System.out.println(leaveRequests.size());
        
        for (int i = 0; i < leaveRequests.size(); i++) {
            LeaveRequest leaveRequest = leaveRequests.get(i);
            Object[] rowData = {leaveRequest.request_id, leaveRequest.getFormattedStartDate(), leaveRequest.getFormattedEndDate(), leaveRequest.status, leaveRequest.notes, leaveRequest.employee.getFullName(), leaveRequest.leaveType.name};
            leaveRequesttTableModel.addRow(rowData);
        }
    }
    
    private void refreshOvertimeRequestList() {
        DefaultTableModel overtimeRequesttTableModel = (DefaultTableModel) employeeOvertimeTable.getModel();
        List<OvertimeRequest> overtimeRequests = OvertimeRequestService.getOvertimeRequestsByEmployeeId(this.authenticatedEmployee.id);
        overtimeRequesttTableModel.setRowCount(0);
        
        for (int i = 0; i < overtimeRequests.size(); i++) {
            OvertimeRequest overtimeRequest = overtimeRequests.get(i);
            Object[] rowData = {overtimeRequest.request_id, overtimeRequest.date, overtimeRequest.noOfHours, overtimeRequest.status, overtimeRequest.notes};
            overtimeRequesttTableModel.addRow(rowData);
        }
    }
    
    public void employeeRefreshLeaveRequestList() {
        DefaultTableModel employeeLeaveRequestDefaultTableModel = (DefaultTableModel) employeeRequestLeaveTable.getModel();
        List<LeaveRequest> leaveRequests = LeaveRequestService.getAllLeaveRequestsByEmployeeId(authenticatedEmployee.id);
        employeeLeaveRequestDefaultTableModel.setRowCount(0);
        
        System.out.println(leaveRequests.size());
        
        for (int i = 0; i < leaveRequests.size(); i++) {
            LeaveRequest leaveRequest = leaveRequests.get(i);
            Object[] rowData = {leaveRequest.request_id, leaveRequest.getFormattedStartDate(), leaveRequest.getFormattedEndDate(), leaveRequest.status, leaveRequest.notes, leaveRequest.leaveType.name};
            employeeLeaveRequestDefaultTableModel.addRow(rowData);
        }
    }
    
    void setColor(JPanel panel) {
        panel.setBackground(new Color(100, 206, 250));
    }
    
    void resetColor(JPanel panel) {
        panel.setBackground(new Color(0, 200, 255));
    }
    
    private void refreshAuthEmployee() {
        String last_name = (String) LastNameText.getText();
        String first_name = (String) FirstNameText.getText();
        String email = (String) EmailText.getText();
        String phone_number = (String) PhoneNumberText.getText();
        String address = (String) AddressText.getText();
        String position = (String) PositionText.getText();
        
    }
    

    private void DashboardButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardButtonMouseClicked
        // TODO add your handling code here:
        RIghtPanelTabbed.setSelectedIndex(0);
        setColor(DashboardButton);
        resetColor(LeaveButton);
        resetColor(MyProfileButton);
        resetColor(OverTimeButton);
        resetColor(AdminButton);

    }//GEN-LAST:event_DashboardButtonMouseClicked

    private void LeaveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LeaveButtonMouseClicked
        // TODO add your handling code here:
        RIghtPanelTabbed.setSelectedIndex(2);
        
        resetColor(DashboardButton);
        setColor(LeaveButton);
        resetColor(MyProfileButton);
        resetColor(AdminButton);
        resetColor(AdminButton);
        

    }//GEN-LAST:event_LeaveButtonMouseClicked

    private void MyProfileButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MyProfileButtonMouseClicked
        // TODO add your handling code here:
        RIghtPanelTabbed.setSelectedIndex(1);
        setColor(MyProfileButton);
        resetColor(LeaveButton);
        resetColor(DashboardButton);
        resetColor(OverTimeButton);
        resetColor(AdminButton);
    }//GEN-LAST:event_MyProfileButtonMouseClicked

    private void AdminButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminButtonMouseClicked
        // TODO add your handling code here:
        RIghtPanelTabbed.setSelectedIndex(3);
        setColor(AdminButton);
        resetColor(LeaveButton);
        resetColor(MyProfileButton);
        resetColor(OverTimeButton);
        resetColor(DashboardButton);
        refreshLeaveRequestList();
        this.refreshAttendanceRecord();
        this.refreshHolidayTable();

    }//GEN-LAST:event_AdminButtonMouseClicked

    private void AddLeaveReqButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddLeaveReqButtonMouseClicked
        // TODO add your handling code here:
        AddLeaveReqForm leaveForm = new AddLeaveReqForm();
        leaveForm.setDashboardFrame(this);
        leaveForm.setVisible(true);
        leaveForm.setLocationRelativeTo(null);
        leaveForm.setAuthenticatedEmployee(authenticatedEmployee);
    }//GEN-LAST:event_AddLeaveReqButtonMouseClicked

    private void updateProfileSubmitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateProfileSubmitBtnMouseClicked
        // TODO add your handling code here:
        String last_name = (String) LastNameText.getText();
        String first_name = (String) FirstNameText.getText();
        String email = (String) EmailText.getText();
        String phone_number = (String) PhoneNumberText.getText();
        String address = (String) AddressText.getText();
        String position = (String) PositionText.getText();
        String department_name = updateProfileDeptCombobox.getSelectedItem().toString();
        
        Department department = DepartmentService.getDepartmentByName(department_name);
        
        if (last_name.isEmpty() || first_name.isEmpty() || email.isEmpty() || phone_number.isEmpty() || address.isEmpty()
                || position.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please customer to update.");
        } else {
            EmployeeService.updateEmployee(authenticatedEmployee.id, last_name, first_name, email, phone_number, address, this.authenticatedEmployee.username, this.authenticatedEmployee.password, this.authenticatedEmployee.is_admin, HiringdateChooser.getDate(), department.department_id, position);
            updateProfileEditBtn.setText("Edit Profile");
            updateProfileSubmitBtn.setVisible(false);
            updateProfileSubmitBtn.setBackground(new Color(255, 255, 255));
            
            JOptionPane.showMessageDialog(rootPane, "Updated Successfully.");
            refreshAuthEmployee();
            this.setUpdateProfileFieldsEditableState(false);
            
        }
    }//GEN-LAST:event_updateProfileSubmitBtnMouseClicked
    
    public void setUpdateProfileFieldsEditableState(Boolean editable) {
        LastNameText.setEnabled(editable);
        FirstNameText.setEnabled(editable);
        EmailText.setEnabled(editable);
        PhoneNumberText.setEnabled(editable);
        AddressText.setEnabled(editable);
        PositionText.setEnabled(editable);
        updateProfileDeptCombobox.setEnabled(editable);
        
    }

    private void updateProfileEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProfileEditBtnActionPerformed
        String cancelText = "Cancel Editing";
        System.out.println(updateProfileEditBtn.getText());
        if (updateProfileEditBtn.getText().equals(cancelText)) {
            updateProfileSubmitBtn.setVisible(false);
            updateProfileEditBtn.setText("Edit");
            this.setUpdateProfileFieldsEditableState(false);
            
        } else {
            updateProfileSubmitBtn.setVisible(true);
            updateProfileEditBtn.setText(cancelText);
            this.setUpdateProfileFieldsEditableState(true);
        }
        ProfileTab.revalidate();
        ProfileTab.repaint();
    }//GEN-LAST:event_updateProfileEditBtnActionPerformed

    private void updateProfileSubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProfileSubmitBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateProfileSubmitBtnActionPerformed

    private void AdminEmployeesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminEmployeesTableMouseClicked
        // TODO add your handling code here:
        int i = AdminEmployeesTable.getSelectedRow();
        String email = AdminEmployeesTable.getValueAt(i, 3).toString();
        
        this.selectedEmployee = EmployeeService.getEmployeeByField("email", email);
        
        StringBuilder message = new StringBuilder();
        message.append("Email: ").append(email).append("\n\n");
        
        Object[] options = {"Update Employee.", "Close"};
        int choice = JOptionPane.showOptionDialog(null, message.toString(), "Updated Information", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        if (choice == 0) {
            RIghtPanelTabbed.setSelectedIndex(4);
            viewEmployeeLastNameLabel.setText(this.selectedEmployee.last_name);
            viewEmployeeFirstNameLabel.setText(this.selectedEmployee.first_name);
            viewEmployeeEmailLabel.setText(this.selectedEmployee.email);
            viewEmployeePhoneNumberLabel.setText(this.selectedEmployee.phone_number);
            viewEmployeeAddressLabel.setText(this.selectedEmployee.address);
            adminUpdateDeptBox.setSelectedItem(this.selectedEmployee.department.department_name);
            HiringdateChooser.setDate(this.selectedEmployee.hiring_date);
            adminUpdatePositionText.setText(this.selectedEmployee.position);
            isAdminTextBox.setSelected(this.selectedEmployee.is_admin);
        }

    }//GEN-LAST:event_AdminEmployeesTableMouseClicked

    private void LeaveRequestAdminTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LeaveRequestAdminTableMouseClicked
        int i = LeaveRequestAdminTable.getSelectedRow();
        int leaveRequestId = (int) LeaveRequestAdminTable.getValueAt(i, 0);
        
        LeaveRequest leaveRequest = LeaveRequestService.getLeaveRequestById(leaveRequestId);
        
        StringBuilder message = new StringBuilder();
        message.append("_______________________________________________ ").append("\n\n");
        message.append("Employee Name: ").append(leaveRequest.employee.getFullName()).append("\n");
        message.append("Leave Type: ").append(leaveRequest.leaveType.name).append("\n");
        message.append("Start Date: ").append(leaveRequest.getFormattedStartDate()).append("\n");
        message.append("End Date: ").append(leaveRequest.getFormattedEndDate()).append("\n");
        message.append("Duration: ").append(leaveRequest.getDuration()).append(" day/s.").append("\n\n");
        message.append("Notes: ").append(leaveRequest.notes).append("\n");
        
        if (leaveRequest.status.equals("For approval")) {
            System.out.println("ygtumtytytyyty");
            Object[] options = {"Approved", "Reject"};
            int choice = JOptionPane.showOptionDialog(null, message.toString(), "Leave Request Details", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            if (choice == 0) {
                LeaveRequestService.updateLeaveRequest(leaveRequestId, leaveRequest.startDate, leaveRequest.endDate, "Approved", leaveRequest.notes, leaveRequest.leave_type_id, leaveRequest.employee_id);
                refreshLeaveRequestList();
            } else {
                LeaveRequestService.updateLeaveRequest(leaveRequestId, leaveRequest.startDate, leaveRequest.endDate, "Rejected", leaveRequest.notes, leaveRequest.leave_type_id, leaveRequest.employee_id);
            }
        } else {
            Object[] options2 = {"Okay"};
            int choice = JOptionPane.showOptionDialog(null, message.toString(), "Leave Request Details", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        }
        

    }//GEN-LAST:event_LeaveRequestAdminTableMouseClicked

    private void adminEdtEmpBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminEdtEmpBtnMouseClicked
        Boolean isAdmin = isAdminTextBox.isSelected();
        Date hiring_date = HiringdateChooser.getDate();
        String position = (String) adminUpdatePositionText.getText();
        String department_name = adminUpdateDeptBox.getSelectedItem().toString();
        
        Department department = DepartmentService.getDepartmentByName(department_name);
        
        if (position.isEmpty() || hiring_date == null || department_name == null) {
            JOptionPane.showMessageDialog(null, "Please complete the form.");
        } else {
            EmployeeService.updateEmployee(this.selectedEmployee.id, this.selectedEmployee.last_name, this.selectedEmployee.first_name, this.selectedEmployee.email, this.selectedEmployee.phone_number, this.selectedEmployee.address, this.selectedEmployee.username, this.selectedEmployee.password, isAdmin, hiring_date, department.department_id, position);
            
            JOptionPane.showMessageDialog(rootPane, "Updated Successfully.");
            
            refreshEmployeeList();
            RIghtPanelTabbed.setSelectedIndex(3);
        }
    }//GEN-LAST:event_adminEdtEmpBtnMouseClicked

    private void adminUpdateDeptBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminUpdateDeptBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminUpdateDeptBoxActionPerformed

    private void isAdminTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isAdminTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_isAdminTextBoxActionPerformed

    private void adminEdtEmpBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminEdtEmpBtn1MouseClicked
        RIghtPanelTabbed.setSelectedIndex(3);
    }//GEN-LAST:event_adminEdtEmpBtn1MouseClicked

    private void employeeRequestLeaveTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeRequestLeaveTableMouseClicked
        // TODO add your handling code here:
        int i = employeeRequestLeaveTable.getSelectedRow();
        int leaveRequestId = (int) employeeRequestLeaveTable.getValueAt(i, 0);
        System.out.println("pepaaaa");
        
        LeaveRequest leaveRequest = LeaveRequestService.getLeaveRequestById(leaveRequestId);
        
        StringBuilder message = new StringBuilder();
        message.append("_______________________________________________ ").append("\n");
        message.append("").append("\n");
        message.append("Leave Type: ").append(leaveRequest.leaveType.name).append("\n");
        message.append("Status: ").append(leaveRequest.status).append("\n");
        message.append("").append("\n");
        message.append("Start Date: ").append(leaveRequest.getFormattedStartDate()).append("\n");
        message.append("End Date: ").append(leaveRequest.getFormattedEndDate()).append("\n");
        message.append("Duration: ").append(leaveRequest.getDuration()).append(" day/s.").append("\n\n");
        message.append("Notes: ").append(leaveRequest.notes).append("\n");
        
        boolean leaveNotStarted = leaveRequest.startDate.after(new Date());
        
        System.out.println(leaveNotStarted);
        
        if (leaveRequest.status.equals("Approved") && leaveNotStarted) {
            
            Object[] options = {"Cancel Leave", "Close"};
            int choice = JOptionPane.showOptionDialog(null, message.toString(), "leave Request Details", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            if (choice == 0) {
                LeaveRequestService.updateLeaveRequest(leaveRequestId, leaveRequest.startDate, leaveRequest.endDate, "Cancelled", leaveRequest.notes, leaveRequest.leave_type_id, leaveRequest.employee_id);
                refreshLeaveRequestList();
            }
        } else if (leaveRequest.status.toLowerCase().equals("for approval")) {
            LeaveRequestService.deleteLeaveRequestById(leaveRequestId);
            
            Object[] options = {"Delete Request", "Cancel"};
            int choice = JOptionPane.showOptionDialog(null, message.toString(), "Delete Request.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            if (choice == 0) {
                LeaveRequestService.deleteLeaveRequestById(leaveRequestId);
                refreshLeaveRequestList();
            }
            
        } else {
            Object[] options2 = {"Okay"};
            int choice = JOptionPane.showOptionDialog(null, message.toString(), "Leave Request Details", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        }

    }//GEN-LAST:event_employeeRequestLeaveTableMouseClicked

    private void timeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeButtonMouseClicked
//        timeButton.setEnabled(false);
//        
//        AttendanceRecordService.createAttendanceRecord(new Date(), this.authenticatedEmployee.id);
//        this.refreshCurrentAttendanceRecord();
//        timeButton.setEnabled(true);
    }//GEN-LAST:event_timeButtonMouseClicked

    private void OverTimeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OverTimeButtonMouseClicked
        RIghtPanelTabbed.setSelectedIndex(5);
        resetColor(DashboardButton);
        resetColor(LeaveButton);
        resetColor(MyProfileButton);
        setColor(OverTimeButton);
        refreshOvertimeRequestList();
    }//GEN-LAST:event_OverTimeButtonMouseClicked

    private void attendanceButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attendanceButtonMouseClicked
        if (attendanceButton.isEnabled()) {
            
            attendanceButton.setEnabled(false);
            
            if (this.currentAttendanceRecord != null) {
                AttendanceRecordService.updateAttendanceRecord(this.currentAttendanceRecord.attendance_record_id, new Date());
            } else {
                AttendanceRecordService.createAttendanceRecord(new Date(), this.authenticatedEmployee.id);
            }
            
            JOptionPane.showMessageDialog(this, "Attendance successfully recorded.");
            
            this.refreshCurrentAttendanceRecord();
        } else {
            JOptionPane.showMessageDialog(this, "Attendance for today already recorded. Time in again tomorow.");
        }

    }//GEN-LAST:event_attendanceButtonMouseClicked

    private void SubmitOvertimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SubmitOvertimeMouseClicked
        // TODO add your handling code here:

        Date date = OvertimeDateChooser.getDate();
        int day = OvertimeHourCmboBx.getSelectedIndex() + 1;
        
        String notes = OvertimeNotesText.getText();
        
        OvertimeRequestService.createOvertimeRequest(date, day, "For approval", notes, this.authenticatedEmployee.id);
        OvertimeNotesText.setText("");
        

    }//GEN-LAST:event_SubmitOvertimeMouseClicked

    private void addOvertimeRequestBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addOvertimeRequestBtnMouseClicked
        // TODO add your handling code here:
        RIghtPanelTabbed.setSelectedIndex(6);
        resetColor(DashboardButton);
        resetColor(LeaveButton);
        resetColor(MyProfileButton);
        setColor(AdminButton);
    }//GEN-LAST:event_addOvertimeRequestBtnMouseClicked

    private void adminOvertimeListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminOvertimeListTableMouseClicked
        // TODO add your handling code here:
        int i = adminOvertimeListTable.getSelectedRow();
        int overtimeRequestId = (int) adminOvertimeListTable.getValueAt(i, 0);
        
        OvertimeRequest overtimeRequest = OvertimeRequestService.getOvertimeRequestById(overtimeRequestId);//.getLeaveRequestById(leaveRequestId);
        System.out.println("ID:   " + overtimeRequestId);
        
        StringBuilder message = new StringBuilder();
        message.append("___________________________________").append("\n");
        message.append("Employee : " + overtimeRequest.employee.getFullName()).append("\n\n");
        message.append("  ").append("\n");
        message.append("Date : " + overtimeRequest.date).append("\n");
        message.append("No of hours: " + overtimeRequest.noOfHours).append("\n");
        message.append("Notes : " + overtimeRequest.notes).append("\n");
        
        if (overtimeRequest.status.equals("For approval")) {
            Object[] option = {"Approved", "Reject"};
            int choice = JOptionPane.showOptionDialog(null, message.toString(), "Overtime Request Details", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
            if (choice == 0) {
                OvertimeRequestService.updateOvertimeRequest(overtimeRequestId, overtimeRequest.date, overtimeRequestId, "Approved", overtimeRequest.notes, overtimeRequest.employee_id);
                refreshAdminOvertimeRequestList();
            } else {
                OvertimeRequestService.updateOvertimeRequest(overtimeRequestId, overtimeRequest.date, overtimeRequestId, "Rejected", overtimeRequest.notes, overtimeRequest.employee_id);
                refreshAdminOvertimeRequestList();
            }
        } else {
            Object[] option2 = {"Okay"};
            int choice2 = JOptionPane.showOptionDialog(null, message.toString(), "Overtime Request Details", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option2, option2[0]);
            if (choice2 == 0) {
                refreshAdminOvertimeRequestList();
            }
        }
        

    }//GEN-LAST:event_adminOvertimeListTableMouseClicked

    private void employeeOvertimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeOvertimeTableMouseClicked
        // TODO add your handling code here:
        int i = employeeOvertimeTable.getSelectedRow();
        int overtimeRequestId = (int) adminOvertimeListTable.getValueAt(i, 0);
        
        OvertimeRequest overtimeRequest = OvertimeRequestService.getOvertimeRequestById(overtimeRequestId);
        
        StringBuilder message = new StringBuilder();
        message.append("           MY OVERTIME REQUEST        ").append("\n");
        message.append("______________________________________").append("\n\n");
        message.append("Date : " + overtimeRequest.date).append("\n");
        message.append("No of hours: " + overtimeRequest.noOfHours).append("\n");
        message.append("Status: " + overtimeRequest.status).append("\n");
        message.append("Notes : " + overtimeRequest.notes).append("\n");
        
        if (overtimeRequest.status.equals("Approved")) {
            
            Object[] options = {"Cancel Leave", "Close"};
            int choice = JOptionPane.showOptionDialog(null, message.toString(), "leave Request Details", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
            if (choice == 0) {
                OvertimeRequestService.updateOvertimeRequest(overtimeRequestId, overtimeRequest.date, overtimeRequest.noOfHours, "Cancelled", overtimeRequest.notes, overtimeRequest.employee_id);
                refreshOvertimeRequestList();
            } else {
                refreshOvertimeRequestList();
            }
        } else if (overtimeRequest.status.toLowerCase().equals("for approval")) {
            
            Object[] options2 = {"Delete Request", "Cancel"};
            int choice2 = JOptionPane.showOptionDialog(null, message.toString(), "Delete Request.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
            
            if (choice2 == 0) {
                OvertimeRequestService.deleteOvertimeRequestById(overtimeRequestId);
                refreshOvertimeRequestList();
            }
            
        } else {
            Object[] options2 = {"Okay"};
            int choice = JOptionPane.showOptionDialog(null, message.toString(), "Overtime Request Details", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
            refreshOvertimeRequestList();
        }

    }//GEN-LAST:event_employeeOvertimeTableMouseClicked

    private void EmployeeFilterComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmployeeFilterComboBoxItemStateChanged
        refreshAttendanceRecord();
    }//GEN-LAST:event_EmployeeFilterComboBoxItemStateChanged

    private void summaryRecordStartdatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_summaryRecordStartdatePropertyChange
        refreshAttendanceRecord();
    }//GEN-LAST:event_summaryRecordStartdatePropertyChange

    private void summaryRecordEndDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_summaryRecordEndDatePropertyChange
        refreshAttendanceRecord();
    }//GEN-LAST:event_summaryRecordEndDatePropertyChange

    private void HolidayTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HolidayTableMouseClicked
        int index = HolidayTable.getSelectedRow();

        if (index >= 0) {
            int holidayId = (int) HolidayTable.getValueAt(index, 0);

            this.selectedHoliday = HolidayService.getHolidayById(holidayId);

            Object[] options = {"Cancel", "Edit", "Delete"};

            StringBuilder message = new StringBuilder();

            message.append("Manage Holiday");

            int choice = JOptionPane.showOptionDialog(null, message.toString(), "Holiday Details", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == 1) {
                editHolidayButton.setText("Update");
                holidayInputName.setText(this.selectedHoliday.holiday_name);
                holidayDatePicker.setDate(this.selectedHoliday.holiday_date);
            }

            if (choice == 2) {
                HolidayService.deleteHolidayById(holidayId);
                refreshHolidayTable();
            }
        }
    }//GEN-LAST:event_HolidayTableMouseClicked

    private void deleteHolidayButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteHolidayButtonMouseClicked
        this.selectedHoliday = null;
        editHolidayButton.setText("Create");
        holidayInputName.setText("");
        holidayDatePicker.setDate(null);
    }//GEN-LAST:event_deleteHolidayButtonMouseClicked

    private void editHolidayButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editHolidayButtonMouseClicked
        String name = holidayInputName.getText();
        Date date = holidayDatePicker.getDate();

        if (name.equals("") || name == null || date == null) {
            JOptionPane.showMessageDialog(this, "Incomplete form");

            return;
        }

        if (this.selectedEmployee != null) {
            HolidayService.updateHolidayById(this.selectedHoliday.holiday_id, date, name);
        } else {
            HolidayService.createHoliday(date, name);
        }

        this.selectedHoliday = null;
        editHolidayButton.setText("Create");
        holidayInputName.setText("");
        holidayDatePicker.setDate(null);
        refreshHolidayTable();
    }//GEN-LAST:event_editHolidayButtonMouseClicked

    private void departmentDeleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_departmentDeleteBtnMouseClicked
        // TODO add your handling code here:
        String dept_name = departmentNameTex.getText();
        
        DepartmentService.addDepartment(dept_name);
        refreshHolidayTable();
    }//GEN-LAST:event_departmentDeleteBtnMouseClicked

    private void departmentSubmitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_departmentSubmitBtnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_departmentSubmitBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddLeaveReqButton;
    private javax.swing.JLabel AddressLabel;
    private javax.swing.JTextField AddressText;
    private javax.swing.JPanel AdminButton;
    private javax.swing.JTabbedPane AdminDashboradTab;
    private javax.swing.JTable AdminEmployeesTable;
    private javax.swing.JTable AttendanceSummaryTable;
    private javax.swing.JPanel DashboardButton;
    private javax.swing.JPanel DashboradTab;
    private javax.swing.JLabel DepartmentLabel;
    private javax.swing.JPanel DepartmentTab;
    private javax.swing.JTable DepartmentTable;
    private javax.swing.JPanel Departmenttab;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField EmailText;
    private javax.swing.JComboBox<String> EmployeeFilterComboBox;
    private javax.swing.JPanel EmployeeTab;
    private javax.swing.JPanel FirstNameLabel;
    private javax.swing.JTextField FirstNameText;
    private com.toedter.calendar.JDateChooser HiringdateChooser;
    private javax.swing.JPanel HolidayTab;
    private javax.swing.JTable HolidayTable;
    private javax.swing.JLabel LastNameLabel;
    private javax.swing.JTextField LastNameText;
    private javax.swing.JPanel LeaveButton;
    private javax.swing.JTable LeaveRequestAdminTable;
    private javax.swing.JPanel LeaveRequestTab;
    private javax.swing.JPanel LeaveTabbed;
    private javax.swing.JPanel LeftPanel;
    private javax.swing.JList<String> MyInformationList;
    private javax.swing.JPanel MyProfileButton;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JLabel NumberLabel;
    private javax.swing.JPanel OverTimeButton;
    private javax.swing.JPanel OverTimeListTab;
    private com.toedter.calendar.JDateChooser OvertimeDateChooser;
    private javax.swing.JPanel OvertimeFormTab;
    private javax.swing.JComboBox<String> OvertimeHourCmboBx;
    private javax.swing.JTextField OvertimeNotesText;
    private javax.swing.JPanel OvertimeTab;
    private javax.swing.JTextField PhoneNumberText;
    private javax.swing.JLabel PositionLabel;
    private javax.swing.JTextField PositionText;
    private javax.swing.JLabel Position_Label;
    private javax.swing.JPanel ProfileTab;
    private javax.swing.JTabbedPane RIghtPanelTabbed;
    private javax.swing.JButton SubmitOvertime;
    private javax.swing.JLabel TItleLabel;
    private javax.swing.JButton addOvertimeRequestBtn;
    private javax.swing.JButton adminEdtEmpBtn;
    private javax.swing.JButton adminEdtEmpBtn1;
    private javax.swing.JPanel adminManageEmployee;
    private javax.swing.JTable adminOvertimeListTable;
    private javax.swing.JPanel adminReportTab;
    private javax.swing.JComboBox<String> adminUpdateDeptBox;
    private javax.swing.JTextField adminUpdatePositionText;
    private javax.swing.JButton attendanceButton;
    private javax.swing.JPanel attendancePanel;
    private javax.swing.JLabel attendanceSummaryLabel;
    private javax.swing.JButton deleteHolidayButton;
    private javax.swing.JButton departmentDeleteBtn;
    private javax.swing.JLabel departmentFormLabel;
    private javax.swing.JTextField departmentNameTex;
    private javax.swing.JButton departmentSubmitBtn;
    private javax.swing.JButton editHolidayButton;
    private javax.swing.JTable employeeOvertimeTable;
    private javax.swing.JTable employeeRequestLeaveTable;
    private com.toedter.calendar.JDateChooser holidayDatePicker;
    private javax.swing.JTextField holidayInputName;
    private javax.swing.JCheckBox isAdminTextBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList<String> leaveBalanceList;
    private com.toedter.calendar.JDateChooser summaryRecordEndDate;
    private com.toedter.calendar.JDateChooser summaryRecordStartdate;
    private javax.swing.JComboBox<String> updateProfileDeptCombobox;
    private javax.swing.JButton updateProfileEditBtn;
    private javax.swing.JButton updateProfileSubmitBtn;
    private javax.swing.JLabel viewEmployeeAddressLabel;
    private javax.swing.JLabel viewEmployeeEmailLabel;
    private javax.swing.JLabel viewEmployeeFirstNameLabel;
    private javax.swing.JLabel viewEmployeeLastNameLabel;
    private javax.swing.JLabel viewEmployeePhoneNumberLabel;
    // End of variables declaration//GEN-END:variables
}
