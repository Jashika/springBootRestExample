package com.springbootWithJPA.springBootRestExample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController controller;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createEmployeeTest() throws Exception {
      when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(getEmployees().get(0));
        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapper.writeValueAsString(getEmployees().get(0)))).andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value(getEmployees().get(0).getFirstName()));
    }



    @Test
    public void testGetEmployee() throws Exception {
        Mockito.when(employeeService.getEmployee(ArgumentMatchers.anyInt())).thenReturn((ResponseEntity.status(HttpStatus.OK).body(getEmployees().get(0))));
        mockMvc.perform(get("/employee/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value(getEmployees().get(0).getFirstName())).
                andExpect(jsonPath("$.lastName").value(getEmployees().get(0).getLastName())).andExpect(jsonPath("$.emailId").value(getEmployees().get(0).getEmailId()));
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        Mockito.when(employeeService.getAllEmployees()).thenReturn(getEmployees());
        mockMvc.perform(get("/employee/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].firstName").value(getEmployees().get(0).getFirstName())).
                andExpect(jsonPath("$[0].lastName").value(getEmployees().get(0).getLastName())).andExpect(jsonPath("$[0].emailId").value(getEmployees().get(0).getEmailId())).andExpect(jsonPath("$[1].firstName").value(getEmployees().get(1).getFirstName())).
                andExpect(jsonPath("$[1].lastName").value(getEmployees().get(1).getLastName())).andExpect(jsonPath("$[1].emailId").value(getEmployees().get(1).getEmailId()));
    }

    @Test
    public void updateEmployeeTest() throws Exception {
        when(employeeService.updateEmployee(1,getEmployees().get(0))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(getEmployees().get(0)));
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
       // mockMvc.perform(delete("/employee/2")).andExpect(status().isOk());
        int id = 1;
        EmployeeService spyService  = Mockito.spy(employeeService);
        Mockito.doNothing().when(spyService).deleteEmployee(id);
        mockMvc.perform(delete("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        verify(employeeService, times(1)).deleteEmployee(id);
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("jashika");
        employee1.setLastName("vasu");
        employee1.setEmailId("jashikavasu@email.com");
        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setFirstName("yashika");
        employee2.setLastName("vasu");
        employee2.setEmailId("yashikavasu@email.com");
        employees.add(employee1);
        employees.add(employee2);
        return  employees;
    }
}

