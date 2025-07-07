package com.dvl.smartleave;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private List<String> roles;
    private int leaveBalance;
    
    public Employee(int id, String name, String role, int leaveBalance) {
        this.id = id;
        this.name = name;
        this.roles = new ArrayList<>();
        this.roles.add(role);
        this.leaveBalance = leaveBalance;
    }
    
    public Employee(int id, String name, String role) {
        this(id, name, role, 20); // Default 20 days leave balance
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getLeaveBalance() { return leaveBalance; }
    public List<String> getRoles() { return roles; }
    
    // Role management
    public boolean hasRole(String role) {
        return roles.contains(role);
    }
    
    public void addRole(String role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }
    
    public void removeRole(String role) {
        roles.remove(role);
    }
    
    // Role-check methods (CRITICAL ADDITION BACK)
    public boolean isApprover() {
        return hasRole("Approver");
    }
    
    public boolean isProcessor() {
        return hasRole("Processor");
    }
    
    // SoD Check (Workshop #4 Requirement)
    public boolean hasSoDConflict() {
        return isApprover() && isProcessor(); // Uses the role-check methods
    }
    
    // Leave management
    public boolean hasSufficientLeave(int days) {
        return leaveBalance >= days;
    }
    
    public void deductLeave(int days) {
        if (days > 0 && hasSufficientLeave(days)) {
            leaveBalance -= days;
        }
    }
    
    @Override
    public String toString() {
        return String.format("Employee[ID=%d, Name=%s, Roles=%s, Balance=%d]", 
                           id, name, roles, leaveBalance);
    }
}