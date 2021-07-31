package com.example.demo.service;

import com.example.demo.constant.EmployeeDesignation;
import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeModel;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.specifications.EmployeeSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeSpecification employeeSpecification;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createEmployee(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        employee.setName(employeeModel.getName());
        employee.setSalary(employeeModel.getSalary());
        employee.setDepartmentId(employeeModel.getDepartmentId());
        employee.setManagerId(employeeModel.getManagerId());
        employee.setDesignation(EmployeeDesignation.valueOf(employeeModel.getDesignation()));
        employeeRepository.save(employee);
    }

    public void updateEmployee(EmployeeModel employeeModel) {
        /*Employee e = employeeRepository.getById(employeeModel.getId());
        e.setId(employeeModel.getId());
        e.setName(employeeModel.getName());
        e.setSalary(employeeModel.getSalary());
        e.setDepartmentId(employeeModel.getDepartmentId());
        e.setManagerId(employeeModel.getManagerId());
        e.setDesignation(EmployeeDesignation.valueOf(employeeModel.getDesignation()));
        employeeRepository.save(e);

        // getting below error for above steps
        Generation of HibernateProxy instances at runtime is not allowed when the configured BytecodeProvider is 'none'; your model requires a more advanced BytecodeProvider to be enabled.
        */

        Optional<Employee> employee = employeeRepository.findById(employeeModel.getId());
        employee.ifPresent(e -> {
            e.setId(employeeModel.getId());
            e.setName(employeeModel.getName());
            e.setSalary(employeeModel.getSalary());
            e.setDepartmentId(employeeModel.getDepartmentId());
            e.setManagerId(employeeModel.getManagerId());
            e.setDesignation(EmployeeDesignation.valueOf(employeeModel.getDesignation()));
            employeeRepository.save(e);
        });
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findEmployeeByDepartmentId(Long id) {
        return employeeRepository.findEmployeesByDepartmentId(id);
    }

    public Optional<Employee> findEmployeeByIdBySpecification(Long id) {
        Specification<Employee> specification = employeeSpecification.findById(id);
        return employeeRepository.findOne(specification);

    }
    public List<Employee> findEmployeesGreaterThanGivenSalary(Long salary) {
        Specification<Employee> specification = employeeSpecification.findGreaterThan(salary);
        return employeeRepository.findAll(specification);

    }
    public List<Employee> findByDesignation(String designation) {
        Specification<Employee> specification = employeeSpecification.findByDesignation(designation);
        return employeeRepository.findAll(specification);

    }
    public List<Employee> findEmployeesWithSalaryAndDepartment(Long salary, Long id) {
        Specification<Employee> specification = employeeSpecification.findGreaterThanWithinDepartment(salary, id);
        return employeeRepository.findAll(specification);

    }
}
