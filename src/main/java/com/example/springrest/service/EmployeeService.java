package com.example.springrest.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.example.springrest.dto.EmployeeDTO;
import com.example.springrest.entity.Employee;
import com.example.springrest.mapper.EmployeeMapper;
import com.example.springrest.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    public List<Employee> getEmployeesByManagerId(Integer managerId) {
        return employeeRepository.findByManagerId(managerId);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<EmployeeDTO> getEmployeeDTO(Integer managerId) {

        List<Employee> employees = getEmployeesByManagerId(managerId);

        return employees.stream()
                .map(emp -> employeeMapper.toEmployeeDTO(emp, emp.getManager()))
                .toList();
    }

}
