package com.shashi.taskmanagement.Task.Management.System.dto;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record EmployeeRequest(
         Long id,
         String name,
         String designation,
         String department,
          String email,
         String image,
         String created_by,
         String modified_by,
         LocalDateTime created_on,
         LocalDateTime updatedOn

) {
    public EmployeeRequest withImage(String image) {
        return new EmployeeRequest(id, name, designation, department, email, image, created_by, modified_by, created_on, updatedOn);
    }
}
