package com.shashi.taskmanagement.Task.Management.System.services;

import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeRequest;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeResponse;
import com.shashi.taskmanagement.Task.Management.System.dto.EmployeeResponseForQuery;
import com.shashi.taskmanagement.Task.Management.System.model.Employee;
import com.shashi.taskmanagement.Task.Management.System.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private static final String IMAGE_DIR = "src/main/resources/static/images/";

    public String saveImage(MultipartFile imageFile) throws IOException {
        String imageName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();  // Unique image name
        Path imagePath = Path.of(IMAGE_DIR, imageName);
        Files.createDirectories(imagePath.getParent());
        Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        //return imagePath.toString();
        return "/images/" + imageName;
    }

    public void createEmployee(List<EmployeeRequest> employeeRequest){
        for (EmployeeRequest request : employeeRequest) {
            Employee employee=new Employee();
            employee.setName(request.name());
            employee.setDesignation(request.designation());
            employee.setDepartment(request.department());
            employee.setEmail(request.email());
            employee.setImage(request.image());
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
                        employee.getImage(),
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

    public boolean deleteEmployee(Long id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }else{
            return  false;
        }
    }
}
