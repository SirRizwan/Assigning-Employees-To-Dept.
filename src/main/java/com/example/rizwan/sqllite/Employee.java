package com.example.rizwan.sqllite;

/**
 * Created by Rizwan on 3/30/2018.
 */

public class Employee {
    private int employeeId;
    private String employeeName;
    private String employeeGender;
    private int departmentId;

    public Employee(int employeeId, String employeeName, String employeeGender, int departmentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeGender='" + employeeGender + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
