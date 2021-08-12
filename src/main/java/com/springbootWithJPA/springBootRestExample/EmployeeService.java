package com.springbootwithjpa.springbootrestexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
    }

    public Employee updateEmployee(int id,Employee employee) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmailId(employee.getEmailId());
        return employeeRepository.save(emp);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> deleteEmployee(int id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

