package com.example.springrest.mapper;

import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.springrest.dto.EmployeeDTO;
import com.example.springrest.entity.Employee;
import com.example.springrest.entity.Manager;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "employee.id", target = "id")
    @Mapping(target = "name", source = "employee", qualifiedByName = "getFullName")
    @Mapping(source = "employee.description", target = "jobTitle")
    @Mapping(source = "manager.department", target = "department")
    @Mapping(target = "managerName", ignore = true) // to be set in service layer
    @Mapping(source = "employee.createdDate", target = "hireDate", qualifiedByName = "maskDate")
    EmployeeDTO toEmployeeDTO(Employee employee, Manager manager);

    @Named("maskDate")
    public static String maskDate(java.time.LocalDate date) {
        if (date == null) {
            return "xxxx-xx-xx";
        }

        String ret = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date);
        return "****" + ret.substring(4);
    }

    @Named("getFullName")
    public static String getFullName(Employee person) {
        return person.getFirstname() + " " + person.getLastname();
    }

}
