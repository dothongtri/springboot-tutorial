package com.example.hello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEmployeeDTO {
    private String name;
    private int age;
}
