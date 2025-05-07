package com.pluralsight;

import java.time.LocalDateTime;

public class Employee {

    private static final double standardHoursWeekly = 40.0;
    private static final double overtimeRate = 1.5;
    private static final double notPunchedIn = -1.0;

    private long employeeId;
    private String name;
    private String department;
    private double payRate;
    private double hoursWorked;
    private double punchInTime;

    // Constructor to create a new employee object
    // Throws illegal argument exception in case payRate is negative
    // Requires the params (employeeId, name, department and payRate) to create an employee object
    public Employee(long employeeId, String name, String department, double payRate){
        if (payRate < 0) {
            throw new IllegalArgumentException("Pay rate can't be negative.");
        }
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.payRate = payRate;
        this.punchInTime = notPunchedIn;
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

    // Checks if employee is currently clocked in
    public boolean punchedIn() {
        return this.punchInTime != notPunchedIn;
    }

    //   === Setters ===
    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    // Sets the total hours worked by the employee for the pay period
    // Assures the hours worked being negative doesn't crash the program
    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked <= 0) {
            throw new IllegalArgumentException("The employee won't be paid as they haven't worked any hours this week");
        }
        this.hoursWorked = hoursWorked;
    }


    // Records a time card punch using the current time. (Based on time zone)
    // Same rules as below works as a punch in if they're punched out and vice versa
    public void punchTimeCard() {
        LocalDateTime now = LocalDateTime.now();
        double currentTime = now.getHour() + (now.getMinute() / 60.0);
        System.out.println("Automatic time punch. Current time: " + now.toLocalTime().withNano(0));
        punchTimeCard(currentTime);
    }
    // Creating a time punch card where if the employee is punched in it acts as a punch out and vice versa
    // Calculates the duration of the shift, adds to hoursWorked and changes employee to punched out
    public void punchTimeCard(double time) {
        if (time < 0) {
            throw new IllegalArgumentException("Punch time can't be negative.");
        }

        if (!punchedIn()) {
            this.punchInTime = time;
            System.out.println("Employee (" + name + ") punched in at " + time);
        }else {
            if (time < this.punchInTime) {
                throw new IllegalArgumentException("Punch out time (" + time + ") cannot be earlier than punch in time (" +
                        ").");
            }

            double shift = time - this.punchInTime;
            this.hoursWorked += shift;

            System.out.println("Employee (" + name + ") punched out at " + time +
                    ".\nShift duration: " + String.format("%.2f", shift) + " hours.");
            System.out.println("Total hours worked: " + String.format("%.2f", this.hoursWorked));

            this.punchInTime = notPunchedIn;
        }
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

    @Override
    public String toString() {
        String status = punchedIn() ? "Punched in since: " + punchInTime : "Punched out";

        return "\nEmployee Payroll Info:\n" +
                "ID: " + employeeId +
                "\nName: " + name +
                "\nDepartment: " + department +
                "\nPay Rate: $" + String.format("%.2f", payRate) + "/hour" +
                "\nHours worked this pay period: " + String.format("%.2f", hoursWorked) +
                "\nCurrent status: " + status + "\n" +
                "\n   === Calculated Pay (Based on hours worked this period) ===" +
                "\nRegular hours: " + String.format("%.2f", getRegularHours()) +
                "\nOvertime hours: " + String.format("%.2f", getOvertimeHours()) +
                "\n\nTotal Pay: $" + String.format("%.2f",getTotalPay());
    }
}
