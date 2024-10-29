package com.shashi.taskmanagement.Task.Management.System.repository;

import com.shashi.taskmanagement.Task.Management.System.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value ="SELECT * from employees where designation = :designation",nativeQuery = true)
    List<Employee> findEmployeesByDesignation(@Param("designation") String designation);

    @Query(value = "SELECT email FROM employees WHERE name = :name", nativeQuery = true)
    String findEmailByName(@Param("name") String name);

}
