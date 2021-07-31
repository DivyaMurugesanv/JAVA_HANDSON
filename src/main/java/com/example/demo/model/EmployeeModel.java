package com.example.demo.model;

import lombok.Data;

@Data
public class EmployeeModel {
    private Long id;
    private String name;
    private Long salary;
    private Long departmentId;
    private Long managerId;
    private String designation;
}
