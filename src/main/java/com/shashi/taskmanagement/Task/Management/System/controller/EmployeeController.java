package com.shashi.taskmanagement.Task.Management.System.controller;


import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeRequest;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeResponse;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeResponseForQuery;
import com.shashi.taskmanagement.Task.Management.System.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addEmployee(@RequestBody List<EmployeeRequest> employeeRequest){
        employeeService.createEmployee(employeeRequest);
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
}
