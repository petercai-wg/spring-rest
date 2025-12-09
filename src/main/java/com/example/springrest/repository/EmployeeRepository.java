package com.example.springrest.repository;

import com.example.springrest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByManagerId(Integer managerId);
}
