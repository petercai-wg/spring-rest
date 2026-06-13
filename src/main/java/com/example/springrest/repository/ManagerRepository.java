package com.example.springrest.repository;

import com.example.springrest.entity.Manager;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    List<Manager> findAll();

    Optional<Manager> findById(Integer id);

}
