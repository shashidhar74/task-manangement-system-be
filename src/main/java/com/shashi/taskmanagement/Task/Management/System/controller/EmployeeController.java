package com.shashi.taskmanagement.Task.Management.System.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeRequest;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeResponse;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeResponseForQuery;
import com.shashi.taskmanagement.Task.Management.System.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addEmployee(
            @RequestPart("employee") String employeeJson,
            @RequestPart("image") MultipartFile imageFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        EmployeeRequest employeeRequest = objectMapper.readValue(employeeJson, EmployeeRequest.class);
        String imageUrl = employeeService.saveImage(imageFile);
        employeeRequest = employeeRequest.withImage(imageUrl);
        employeeService.createEmployee(List.of(employeeRequest));
        return new ResponseEntity<>("Employees added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse>getAllEmployee(){
        return  employeeService.getAllEmployees();
    }

    @GetMapping("/byDesignation")
    public ResponseEntity<List<EmployeeResponseForQuery>>getEmployeesByDesignation(@RequestParam String designation){
        List<EmployeeResponseForQuery> employees= employeeService.getEmployeesByDesignation(designation);
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteEmployee(@PathVariable Long id){
        boolean isDeleted = employeeService.deleteEmployee(id);
        if(isDeleted){
            return  new ResponseEntity<>("Employee Deleted Sucessfully.",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }
}
