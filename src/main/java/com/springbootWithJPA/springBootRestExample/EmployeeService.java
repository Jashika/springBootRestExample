package com.springbootWithJPA.springBootRestExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Employee> getEmployee(int id)  throws ResourceNotFoundException  {
     Employee employee= employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
     return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    public ResponseEntity<Employee> updateEmployee(int id,Employee employee) throws ResourceNotFoundException  {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id :" + id));
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmailId(employee.getEmailId());
        Employee updatedEmp=employeeRepository.save(emp);
        return ResponseEntity.ok(updatedEmp);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(int id)  throws ResourceNotFoundException  {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with Id :" + id));
        employeeRepository.delete(employee);
    }
}

