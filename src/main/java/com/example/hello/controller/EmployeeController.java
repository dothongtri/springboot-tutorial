package com.example.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.dto.CreateEmployeeDTO;
import com.example.hello.entity.Employee;
import com.example.hello.service.EmployeeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

        @Autowired
    private EmployeeService employeeService;
    
    @PostMapping("create")
    public ResponseEntity<Employee> insertEmployee(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        Employee savedEmployee = employeeService.addEmployee(createEmployeeDTO);
        return ResponseEntity.status(201).body(savedEmployee);
    }
    
}
