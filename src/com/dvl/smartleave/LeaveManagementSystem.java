package com.dvl.smartleave;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeaveManagementSystem {
    private List<Employee> employees;
    private List<LeaveRequest> leaveRequests;
    
    public LeaveManagementSystem() {
        this.employees = new ArrayList<>();
        this.leaveRequests = new ArrayList<>();
    }
    
    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        }
    }
    
    public boolean submitLeaveRequest(LeaveRequest request) {
        if (LeaveValidator.isValid(request)) {
            leaveRequests.add(request);
            return true;
        }
        return false;
    }
    
    public void processPendingRequests() {
        leaveRequests.stream()
            .filter(req -> req.getStatus() == LeaveRequest.Status.PENDING)
            .forEach(req -> {
                Employee approver = findApproverFor(req);
                if (approver != null) {
                    req.approve(approver);
                }
            });
    }
    
    private Employee findApproverFor(LeaveRequest request) {
        return employees.stream()
            .filter(emp -> emp.isApprover() && emp.getId() != request.getEmployee().getId())
            .findFirst()
            .orElse(null);
    }
    
    public List<LeaveRequest> getApprovedLeaves() {
        return leaveRequests.stream()
            .filter(req -> req.getStatus() == LeaveRequest.Status.APPROVED)
            .collect(Collectors.toList());
    }
    
    public List<Employee> getApprovers() {
        return employees.stream()
            .filter(Employee::isApprover)
            .collect(Collectors.toList());
    }
}