package com.example.demo.specifications;


import com.example.demo.constant.EmployeeDesignation;
import com.example.demo.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> findById(Long id) {
        return(root,query,cb)-> (cb.equal(root.get("id"), id));
        };

    public static Specification<Employee> findByDesignation(String designation) {
        return(root,query,cb)-> (cb.equal(root.get("designation"), EmployeeDesignation.valueOf(designation)));
        };

    public static Specification<Employee> findGreaterThan(Long salary) {
        return(root,query,cb)-> {
            query.orderBy(cb.asc(root.get("name")));
            return cb.and(
                    cb.greaterThan(root.get("salary"), salary));
        };
        };
    public static Specification<Employee> findGreaterThanWithinDepartment(Long salary, Long id) {
        return(root,query,cb)-> {
            query.orderBy(cb.asc(root.get("name")));
            return cb.and(
                    cb.greaterThan(root.get("salary"), salary),
                    cb.equal(root.get("departmentId"), id)
                    );
        };
        };
}
