package com.springbootWithJPA.springBootRestExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        log.info("Inside Service Test");
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        return employeeService.getEmployee(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") int id, @RequestBody Employee employee) throws ResourceNotFoundException {
        return employeeService.updateEmployee(id, employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id)  throws ResourceNotFoundException  {
     employeeService.deleteEmployee(id);
    }
}

