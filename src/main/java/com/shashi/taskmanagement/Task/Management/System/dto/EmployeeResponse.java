package com.shashi.taskmanagement.Task.Management.System.dto;

import java.time.LocalDateTime;

public record EmployeeResponse(
        Long id,
        String name,
        String designation,
        String department,
        String email,
        String image,
        String created_by,
        String modified_by,
        LocalDateTime createdOn,
        LocalDateTime updatedOn
) {

}
