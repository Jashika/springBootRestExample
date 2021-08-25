package com.springbootwithjpa.springbootrestexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    public Employee createEmployee(Employee employee) {
        LOGGER.info("Inside Service");
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(int id)  throws ResourceNotFoundException  {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
    }

    public Employee updateEmployee(int id,Employee employee) throws ResourceNotFoundException  {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmailId(employee.getEmailId());
        return employeeRepository.save(emp);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(int id)  throws ResourceNotFoundException  {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with Id :" + id));
        employeeRepository.delete(employee);
    }
}

