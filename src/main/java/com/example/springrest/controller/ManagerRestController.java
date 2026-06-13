package com.example.springrest.controller;

import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springrest.service.EmployeeService;
import com.example.springrest.service.VirtualThreadService;
import com.example.springrest.entity.Employee;
import com.example.springrest.entity.Manager;
import com.example.springrest.repository.ManagerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/rest")
public class ManagerRestController {

    private final ManagerRepository managerRepository;

    @GetMapping("/managers")
    public ResponseEntity<List<Manager>> getManagers() {

        String loginUser = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Manager> list = managerRepository.findAll();

        log.info(" GetMapping Managers  by " + loginUser + " return #manager: " + list.size());
        return new ResponseEntity<List<Manager>>(list, new HttpHeaders(),
                HttpStatus.OK);
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<Manager> getManager(@PathVariable Integer id) {

        log.info(" find manager by id " + id);

        Optional<Manager> manager = managerRepository.findById(id);

        if (manager.isPresent()) {
            log.info(" GetMapping  return Manager: " + manager.get());
            return new ResponseEntity<Manager>(manager.get(), new HttpHeaders(), HttpStatus.OK);
        } else {
            log.warn(" GetMapping  return NOT FOUND");
            return new ResponseEntity<Manager>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

}
