package com.example.springrest.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manager")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "employeeid")
    private Integer employeeId;

    @Column(name = "department", length = 255)
    private String department;

    @Column(name = "transit")
    private Integer transit;

    // let DB fill default; mark non-insertable/updatable so JPA doesn't override
    @Column(name = "created_date", insertable = false, updatable = false)
    private LocalDate createdDate;

    @Column(name = "last_updated", insertable = false, updatable = false)
    private LocalDateTime lastUpdated;

    // @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<Employee> employees = new ArrayList<>();

    // public void addEmployee(Employee e) {
    // employees.add(e);
    // e.setManager(this);
    // }

    // public void removeEmployee(Employee e) {
    // employees.remove(e);
    // e.setManager(null);
    // }
}