package com.shashi.taskmanagement.Task.Management.System.services;

import com.shashi.taskmanagement.Task.Management.System.dto.TaskResponse;
import com.shashi.taskmanagement.Task.Management.System.dto.TasksRequest;
import com.shashi.taskmanagement.Task.Management.System.model.TaskDetails;
import com.shashi.taskmanagement.Task.Management.System.repository.TasksRepository;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TasksService {

    @Autowired
    private final TasksRepository tasksRepository;

    public void createTasks(List<TasksRequest> tasksRequest){
        for(TasksRequest request:tasksRequest){
            String projectName = request.projectName();
            String projectPrefix = projectName.substring(0, 1).toUpperCase();
            int nextId = getNextTaskId(projectPrefix);
            String generatedId = projectPrefix + "-" + String.format("%03d", nextId);

            TaskDetails taskDetails=new TaskDetails();
            taskDetails.setTaskId(generatedId);
            taskDetails.setProjectName(request.projectName());
            taskDetails.setTaskName(request.taskName());
            taskDetails.setOwner(request.owner());
            taskDetails.setStatus(request.status());
            taskDetails.setPriority(request.priority());
            taskDetails.setDueDate(request.dueDate());
            taskDetails.setStartDate(request.startDate());
            taskDetails.setCreatedOn(LocalDateTime.now());
            taskDetails.setCreatedBy(request.createdBy());
            taskDetails.setModifiedBy(request.modifiedBy());
            taskDetails.setModifiedOn(request.modifiedOn());
            tasksRepository.save(taskDetails);
        }
    }

    private int getNextTaskId(String projectPrefix) {
        List<TaskDetails> existingTasks = tasksRepository.findByProjectNameStartingWith(projectPrefix);
        int maxId = 0;
        for (TaskDetails task : existingTasks) {
            String id = task.getTaskId();
            if (id.startsWith(projectPrefix + "-")) {
                String numericPart = id.substring(id.indexOf('-') + 1);
                maxId = Math.max(maxId, Integer.parseInt(numericPart));
            }
        }
        return maxId + 1;
    }

    public void updateTask(List<Long> taskIds, List<TasksRequest> tasksRequests) {
        if (taskIds.size() != tasksRequests.size()) {
            throw new IllegalArgumentException("Task IDs and request list sizes must match.");
        }
        for (int i = 0; i < taskIds.size(); i++) {
            Long taskId = taskIds.get(i);
            TasksRequest tasksRequest = tasksRequests.get(i);
            Optional<TaskDetails> optionalTask = tasksRepository.findById(taskId);
            if (optionalTask.isPresent()) {
                TaskDetails taskDetails = optionalTask.get();
                taskDetails.setProjectName(tasksRequest.projectName());
                taskDetails.setTaskName(tasksRequest.taskName());
                taskDetails.setOwner(tasksRequest.owner());
                taskDetails.setStatus(tasksRequest.status());
                taskDetails.setPriority(tasksRequest.priority());
                taskDetails.setDueDate(tasksRequest.dueDate());
                taskDetails.setStartDate(tasksRequest.startDate());
                taskDetails.setModifiedBy(tasksRequest.modifiedBy());
                taskDetails.setModifiedOn(LocalDateTime.now());
                tasksRepository.save(taskDetails);
            } else {
                throw new ResourceNotFoundException("Task not found with ID: " + taskId);
            }
        }
    }

    public List<TaskResponse> getAlltasks(){
        return tasksRepository.findAll()
                .stream()
                .map(tasks -> new TaskResponse(
                        tasks.getId(),
                        tasks.getTaskId(),
                        tasks.getTaskName(),
                        tasks.getProjectName(),
                        tasks.getOwner(),
                        tasks.getStatus(),
                        tasks.getStartDate(),
                        tasks.getDueDate(),
                        tasks.getPriority(),
                        tasks.getCreatedOn(),
                        tasks.getCreatedBy(),
                        tasks.getModifiedOn(),
                        tasks.getModifiedBy()
                ))
                .collect(Collectors.toList());
    }

    public List<TaskResponse> findProjectsByProjectName(String project_namae){
        return tasksRepository.findProjectsByProjectName(project_namae)
                .stream()
                .map(taskDetails -> new TaskResponse(
                        taskDetails.getId(),
                        taskDetails.getTaskId(),
                        taskDetails.getTaskName(),
                        taskDetails.getProjectName(),
                        taskDetails.getOwner(),
                        taskDetails.getStatus(),
                        taskDetails.getStartDate(),
                        taskDetails.getDueDate(),
                        taskDetails.getPriority(),
                        taskDetails.getCreatedOn(),
                        taskDetails.getCreatedBy(),
                        taskDetails.getModifiedOn(),
                        taskDetails.getModifiedBy()
                )).toList();
    }
}
