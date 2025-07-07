package com.dvl.smartleave;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create employees with different roles
        Employee emp1 = new Employee(101, "Alice", "Approver", 30);
        Employee emp2 = new Employee(102, "Bob", "Employee", 15);
        Employee emp3 = new Employee(103, "Charlie", "Processor", 20);
        
        // Deliberate SoD Violation (NEW FOR WORKSHOP #4)
        Employee riskyEmp = new Employee(104, "Dave", "Employee", 10);
        riskyEmp.addRole("Approver");
        riskyEmp.addRole("Processor"); // Conflict!
        
        // Test SoD Conflicts
        System.out.println("=== Segregation of Duties (SoD) Check ===");
        List<Employee> allEmployees = new ArrayList<>();
        allEmployees.add(emp1);
        allEmployees.add(emp2);
        allEmployees.add(emp3);
        allEmployees.add(riskyEmp);
        
        checkSoDConflicts(allEmployees);
    }
    
    /**
     * Checks for SoD conflicts in employee roles (NEW FOR WORKSHOP #4)
     */
    private static void checkSoDConflicts(List<Employee> employees) {
        for (Employee emp : employees) {
            if (emp.hasSoDConflict()) {
                System.out.println("⛔ SoD VIOLATION: " + emp.getName() + 
                                  " has conflicting roles: " + emp.getRoles());
            } else {
                System.out.println("✅ " + emp.getName() + 
                                 " has valid roles: " + emp.getRoles());
            }
        }
    }
}