package com.pluralsight;

public class Employee {

    private static final double standardHoursWeekly = 40.0;
    private static final double overtimeRate = 1.5;

    private long employeeId;
    private String name;
    private String department;
    private double payRate;
    private double hoursWorked;

    // Constructor to create a new employee object
    // Throws illegal argument exception in case payRate is negative.
    // Requires the params (employeeId, name, department and payRate) to create an employee object.
    public Employee(long employeeId, String name, String department, double payRate){
        if (payRate < 0) {
            throw new IllegalArgumentException("Pay rate can't be negative.");
        }
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.payRate = payRate;
        this.hoursWorked = 0.0;
    }

    //   === Getters ===
    public long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getPayRate() {
        return payRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    //   === Setters ===
    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    // Sets the total hours worked by the employee for the pay period.
    // Assures the hours worked being negative doesn't crash the program.
    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked <= 0) {
            throw new IllegalArgumentException("The employee won't be paid as they haven't worked any hours this week");
        }
        this.hoursWorked = hoursWorked;
    }

    // Calculates the employees individual worked hours outside the standard
    public double getRegularHours() {
        if (this.hoursWorked <= standardHoursWeekly) {
            return this.hoursWorked;
        }else {
            return standardHoursWeekly;
        }
    }

    // Calculates an employees overtime hours (if there are any)
    public double getOvertimeHours() {
        if (this.hoursWorked > standardHoursWeekly) {
            return this.hoursWorked - standardHoursWeekly;
        }else {
            return 0.0;
        }
    }

    public double getTotalPay() {
        double regularHours = getRegularHours();
        double overtimeHours = getOvertimeHours();

        double regularPay = regularHours * this.payRate;
        double overtimePay = overtimeHours * this.payRate * overtimeRate;

        return regularPay + overtimePay;
    }
}
