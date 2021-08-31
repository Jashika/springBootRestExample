package com.springbootWithJPA.springBootRestExample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("jashika");
        Mockito.when(employeeService.getEmployee(ArgumentMatchers.anyInt())).thenReturn(employee);
        mockMvc.perform(get("/employee/1")).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.firstName").value("jashika"));
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
        Employee employee = new Employee();
        employee.setFirstName("Jashika");
        employee.setLastName("Vasu");
        employee.setEmailId("JashikaVasu@gmail.com");
        String inputInJson = this.mapToJson(employee);
        when(employeeService.updateEmployee(ArgumentMatchers.anyInt(), ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/employee/1")
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String outPutInJson = mvcResult.getRequest().getContentAsString();
        assertEquals(inputInJson, outPutInJson);
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

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
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

