package com.example.springrest.controller;

import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springrest.service.EmployeeService;

import com.example.springrest.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/rest")
public class EmployeeRestController {

    private final EmployeeService service;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        String loginUser = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(" @GetMapping getAllEmployees .. , by " + loginUser);
        List<Employee> list = service.getAllEmployees();
        log.info(" @GetMapping getAllEmployees return " + list.size() + " employees");

        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(),
                HttpStatus.OK);
    }

    @GetMapping("byManager/{id}")
    public ResponseEntity<List<Employee>> getEmployeesByManagerID(@PathVariable("id") Integer id) {

        List<Employee> list = service.getEmployeesByManagerId(id);
        log.info(" @GetMapping getEmployeesByManagerID return " + list.size() + " employees");
        return ResponseEntity.ok(list);

    }
}
