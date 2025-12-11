package com.example.springrest.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.example.springrest.entity.Employee;
import com.example.springrest.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class VirtualThreadService {

    private final EmployeeRepository employeeRepository;

    private ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();

    // executor.execute() uses Runnable
    // To return data, must submit a Callable, return a Future
    // or CompletableFuture.

    // Using Callable + Future to get result from Virtual Thread
    public List<Employee> getEmployeesByManagerId(Integer managerId) {
        try {
            Future<List<Employee>> future = virtualThreadExecutor.submit(() -> {
                log.info("Virtual Thread {} get Employee for id: {}", Thread.currentThread(), managerId);
                return employeeRepository.findByManagerId(managerId);
            });

            return future.get(); // blocks until result is available
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch employees", e);
        }
    }

    // Using CompletableFuture with Virtual Thread Executor
    public List<Employee> asyncEmployeesByManagerId(Integer managerId) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Virtual Thread {} get Employee for id: {}", Thread.currentThread(), managerId);
            return employeeRepository.findByManagerId(managerId);
        }, virtualThreadExecutor).join(); // waits and returns result
    }

    /**
     * Default settings for Tomcat's thread pool in SpringBoot:
     * server.tomcat.threads.max=200
     * The maximum number of worker threads in the pool,
     * the concurrent requests that Tomcat can actively process.
     */

}
