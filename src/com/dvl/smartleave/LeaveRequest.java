package com.dvl.smartleave;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LeaveRequest {
    public enum Status { PENDING, APPROVED, REJECTED }
    
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private Status status;
    
    public LeaveRequest(Employee employee, LocalDate startDate, 
                       LocalDate endDate, String type) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = Status.PENDING;
    }
    
    public int getNumberOfDays() {
        return (int)ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
    
    public boolean approve(Employee approver) {
        if (!canBeApprovedBy(approver)) {
            return false;
        }
        
        if (!employee.hasSufficientLeave(getNumberOfDays())) {
            status = Status.REJECTED;
            return false;
        }
        
        employee.deductLeave(getNumberOfDays());
        status = Status.APPROVED;
        return true;
    }
    
    public boolean canBeApprovedBy(Employee approver) {
        if (approver == null) return false;
        if (approver.getId() == employee.getId()) return false;
        if (!approver.isApprover()) return false;
        return true;
    }
    
    public void process(Employee processor) {
        if (processor != null && processor.isProcessor() && status == Status.APPROVED) {
            System.out.println("Leave processed successfully for " + employee.getName());
        }
    }
    
    // Getters
    public Employee getEmployee() { return employee; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getType() { return type; }
    public Status getStatus() { return status; }
    
    @Override
    public String toString() {
        return String.format(
            "LeaveRequest[Employee=%s, Dates=%s to %s (%d days), Type=%s, Status=%s]",
            employee.getName(), startDate, endDate, getNumberOfDays(), type, status
        );
    }
}