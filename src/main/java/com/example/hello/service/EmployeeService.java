package com.example.hello.service;

import java.util.List;

import com.example.hello.dto.CreateEmployeeDTO;
import com.example.hello.entity.Employee;

public interface EmployeeService {

    // Define methods for employee-related operations
    Employee addEmployee(CreateEmployeeDTO createEmployeeDTO);
    CreateEmployeeDTO getEmployee(Long id);
    void updateEmployee(Long id, String name, int age);
    void deleteEmployee(Long id);
    List<CreateEmployeeDTO> listAllEmployees();
} 
