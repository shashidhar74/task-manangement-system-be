package com.shashi.taskmanagement.Task.Management.System.dto;

import java.time.LocalDateTime;

public record TasksRequest(
        Long id,
        String taskId,
        String projectName,
        String taskName,
        String owner,
        String status,
        LocalDateTime startDate,
        LocalDateTime dueDate,
        Integer priority,
         LocalDateTime createdOn,
        String createdBy,
        LocalDateTime modifiedOn,
        String modifiedBy
) {
}
