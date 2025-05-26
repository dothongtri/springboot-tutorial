package com.example.hello.service;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.hello.dto.CreateEmployeeDTO;
import com.example.hello.entity.Employee;
import com.example.hello.repository.EmployeeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    // Implement the methods defined in EmployeeService interface
    @Override
    public Employee addEmployee(CreateEmployeeDTO createEmployeeDTO) {
        Employee employee = new Employee();
        employee.setName(createEmployeeDTO.getName());
        employee.setAge(createEmployeeDTO.getAge());
        employeeRepository.save(employee);
        return employee; // Return the saved employee
    }

    @Override
    public CreateEmployeeDTO getEmployee(Long id) {
        // Logic to get employee by ID
        return null; // Placeholder return statement
    }

    @Override
    public void updateEmployee(Long id, String name, int age) {
        // Logic to update employee
    }

    @Override
    public void deleteEmployee(Long id) {
        // Logic to delete employee by ID
    }

    @Override
    public List<CreateEmployeeDTO> listAllEmployees() {
        // Logic to list all employees
        return null; // Placeholder return statement
    }
    
}
