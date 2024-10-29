package com.shashi.taskmanagement.Task.Management.System.controller;

import com.shashi.taskmanagement.Task.Management.System.dto.TaskResponse;
import com.shashi.taskmanagement.Task.Management.System.dto.TasksRequest;
import com.shashi.taskmanagement.Task.Management.System.services.TasksService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private TasksService tasksService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addTasks(@RequestBody List<TasksRequest> tasksRequest){
        tasksService.createTasks(tasksRequest);
        return new ResponseEntity<>("Task Added sucessfully", HttpStatus.CREATED);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponse>getAllTasks(){
        return tasksService.getAlltasks();
    }
}
