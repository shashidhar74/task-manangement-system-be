package com.shashi.taskmanagement.Task.Management.System.repository;

import com.shashi.taskmanagement.Task.Management.System.model.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<TaskDetails,Long> {
    List<TaskDetails> findByProjectNameStartingWith(String projectPrefix);
}
