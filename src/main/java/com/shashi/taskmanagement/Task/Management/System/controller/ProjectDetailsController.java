package com.shashi.taskmanagement.Task.Management.System.controller;

import com.shashi.taskmanagement.Task.Management.System.dto.ProjectDetailsRecord;
import com.shashi.taskmanagement.Task.Management.System.dto.ProjectDetailsResponse;
import com.shashi.taskmanagement.Task.Management.System.services.ProjectDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/projectDetails")
public class ProjectDetailsController {
        private ProjectDetailsService projectDetailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addProject(@RequestBody ProjectDetailsRecord projectDetailsRecord){
    projectDetailsService.createProject(projectDetailsRecord);
        return "Project added sucessfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDetailsResponse>getAllprojects(){
            return projectDetailsService.getAllProjectsData();
    }


}
