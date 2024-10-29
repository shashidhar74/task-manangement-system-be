package com.shashi.taskmanagement.Task.Management.System.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record EmployeeRequest(
         Long id,
         String name,
         String designation,
         String department,
          String email,
         String created_by,
         String modified_by,
         LocalDateTime created_on,
         LocalDateTime updatedOn

) {
}
