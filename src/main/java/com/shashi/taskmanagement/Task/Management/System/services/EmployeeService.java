package com.shashi.taskmanagement.Task.Management.System.services;

import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeRequest;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeResponse;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeResponseForQuery;
import com.shashi.taskmanagement.Task.Management.System.model.Employee;
import com.shashi.taskmanagement.Task.Management.System.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public void createEmployee(List<EmployeeRequest> employeeRequest){
        for (EmployeeRequest request : employeeRequest) {
            Employee employee=new Employee();
            employee.setName(request.name());
            employee.setDesignation(request.designation());
            employee.setDepartment(request.department());
            employee.setEmail(request.email());
            employee.setCreated_by(request.created_by());
            employee.setCreatedOn(request.created_on());
            employee.setModified_by(request.modified_by());
            employee.setUpdatedOn(request.updatedOn());
            employeeRepository.save(employee);
        }

    }

    public List<EmployeeResponse>getAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(),
                        employee.getName(),
                        employee.getDesignation(),
                        employee.getDepartment(),
                        employee.getEmail(),
                        employee.getCreated_by(),
                        employee.getModified_by(),
                        employee.getCreatedOn(),
                        employee.getUpdatedOn()
                )).toList();
    }

    public  List<EmployeeResponseForQuery> getEmployeesByDesignation(String designation){
        return employeeRepository.findEmployeesByDesignation(designation)
                .stream()
                .map(employee -> new EmployeeResponseForQuery(
                        employee.getId(),
                        employee.getName(),
                        employee.getDesignation(),
                        employee.getDepartment(),
                        employee.getEmail()
                )).toList();
    }
}
