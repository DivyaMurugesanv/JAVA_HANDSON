package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.model.*;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    public DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Optional<Department> findById(Long id) {
        return departmentRepository.findById(id);
    }

}
