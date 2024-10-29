package com.shashi.taskmanagement.Task.Management.System.dto;

public record EmployeeResponseForQuery(
        Long id,
        String name,
        String designation,
        String department,
        String email
) {
}
