package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeModel;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get employee list
    @GetMapping("/list")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    //create employee
    @PostMapping("/add")
    public boolean addEmployee(@RequestBody EmployeeModel employeeModel) {
        employeeService.createEmployee(employeeModel);
        return true;
    }

    //update employee
    @PostMapping("/update")
    public boolean updateEmployee(@RequestBody EmployeeModel employee) {
        employeeService.updateEmployee(employee);
        return true;
    }

    //get employee by id
    @GetMapping("/get/employee/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id,
                                          @RequestParam (value = "withSpecification", required = false) boolean withSpecification) {
       if(withSpecification) {
           return employeeService.findEmployeeByIdBySpecification(id);
       } else {
           return employeeService.findById(id);
       }
    }

    //get employees using designation
    @GetMapping("/get/employeesByDesignation/{designation}")
    public List<Employee> getEmployeeBySpecification(@PathVariable String  designation) {
        return employeeService.findByDesignation(designation);
    }

    //get employees greater than given salary
    @GetMapping("/get/employeesBySalary/{salary}")
    public List<Employee> getEmployeesUsingSalary(@PathVariable Long salary) {
        return employeeService.findEmployeesGreaterThanGivenSalary(salary);
    }

    //get employees with in department id and greater than given salary
    @GetMapping("/get/employeeDetails/{salary}/{department}")
    public List<Employee> getEmployeesUsingSalaryAndId(@PathVariable Long salary, @PathVariable Long department) {
        return employeeService.findEmployeesWithSalaryAndDepartment(salary, department);
    }

    // find all employees using department ID
    @GetMapping("/getEmployees/{id}")
    public List<Employee> getEmployeesByDepartmentId(@PathVariable Long id) {
        return employeeService.findEmployeeByDepartmentId(id);
    }
}
