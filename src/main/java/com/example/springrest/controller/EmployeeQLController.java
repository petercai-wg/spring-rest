package com.example.springrest.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.springrest.entity.Employee;
import com.example.springrest.service.EmployeeService;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Slf4j
@Controller
public class EmployeeQLController {

    private final EmployeeService service;

    @QueryMapping
    public List<Employee> getEmployees() {
        log.info("QueryMapping getEmployees ... ");
        return service.getAllEmployees();

    }

    @QueryMapping
    public List<Employee> getEmployeesByManagerID(@Argument Integer id) {
        log.info("QueryMapping getEmployeesByManagerID for manager id: " + id);

        return service.getEmployeesByManagerId(id);
    }
}
