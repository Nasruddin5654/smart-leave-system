package com.dvl.smartleave;

import java.time.LocalDate;
import java.util.List;

public class LeaveValidator {
    private static final List<String> VALID_LEAVE_TYPES = 
        List.of("Vacation", "Sick", "Personal", "Maternity", "Paternity");
    
    public static boolean isValid(LeaveRequest request) {
        if (request == null) return false;
        if (request.getStartDate() == null || request.getEndDate() == null) return false;
        if (request.getEndDate().isBefore(request.getStartDate())) return false;
        if (request.getStartDate().isBefore(LocalDate.now())) return false;
        if (!VALID_LEAVE_TYPES.contains(request.getType())) return false;
        return true;
    }
}