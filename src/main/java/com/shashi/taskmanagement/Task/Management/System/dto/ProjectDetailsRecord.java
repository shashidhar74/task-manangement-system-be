package com.shashi.taskmanagement.Task.Management.System.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record ProjectDetailsRecord(
        Long project_id,
        String project_name,
        String project_head,
        Date start_date,
        Date end_date,
        LocalDateTime created_on,
        String created_by,
        LocalDateTime modified_on,
        String modified_by
) {
}
