package com.example.springrest.dto;

public record EmployeeDTO(
        Integer id,
        String name,
        String jobTitle,
        String department,
        String managerName,
        String hireDate) {

}
