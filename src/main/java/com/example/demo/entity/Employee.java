package com.example.demo.entity;

import com.example.demo.constant.EmployeeDesignation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "employee_list")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "manager_id")
    private Long managerId;

    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    @ManyToOne()
    private Employee manager;

    @Column(name = "department_id")
    private Long departmentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeDesignation designation;

    @ManyToOne()
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private Department department;
}
