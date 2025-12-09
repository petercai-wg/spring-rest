package com.example.springrest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springrest.entity.Employee;
import com.example.springrest.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesByManagerId(Integer managerId) {
        return employeeRepository.findByManagerId(managerId);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
