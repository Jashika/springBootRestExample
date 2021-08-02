package com.springbootWithJPA.springBootRestExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable(value = "id") Integer id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @GetMapping("/employee/allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }
}

