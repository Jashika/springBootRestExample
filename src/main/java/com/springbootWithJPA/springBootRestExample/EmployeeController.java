package com.springbootWithJPA.springBootRestExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

        @Autowired
        private EmployeeRepository employeeRepository;

        @PostMapping("/employee")
        public Employee createEmployee(@RequestBody Employee employee) {
            return this.employeeRepository.save(employee);
        }

        @GetMapping("/employee/{id}")
        public Optional<Employee> getEmployee(@PathVariable(value = "id") Integer id) {
            return employeeRepository.findById(id);
        }

        @PutMapping("/employee/{id}")
        public Employee updateEmployee(@PathVariable(value = "id") Integer id,@RequestBody Employee employee ){
           Employee emp= employeeRepository.getById(id);
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setEmailId(employee.getEmailId());
            return employeeRepository.save(emp);
        }

    }

