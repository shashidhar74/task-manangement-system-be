package com.shashi.taskmanagement.Task.Management.System.repository;

import com.shashi.taskmanagement.Task.Management.System.model.Employee;
import com.shashi.taskmanagement.Task.Management.System.model.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksRepository extends JpaRepository<TaskDetails,Long> {
    List<TaskDetails> findByProjectNameStartingWith(String projectPrefix);

    @Query(value ="SELECT * from tasks where project_name = :project_name",nativeQuery = true)
    List<TaskDetails> findProjectsByProjectName(@Param("project_name") String project_name);
}
