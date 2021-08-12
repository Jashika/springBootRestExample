package com.springbootwithjpa.springbootrestexample;

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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    public void testCreateEmployee() throws Exception {
        Employee mockEmployee = new Employee();
        mockEmployee.setFirstName("Jashika");
        mockEmployee.setLastName("Vasu");
        mockEmployee.setEmailId("jashikavasu@gmail.com");
        String inputInJson = this.mapToJson(mockEmployee);
        String URI = "/api/createEmployee";
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(mockEmployee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputInJson = result.getRequest().getContentAsString();
        assertEquals(inputInJson, outputInJson);
    }


    @Test
    public void testGetEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("jashika");
        Mockito.when(employeeService.getEmployee(ArgumentMatchers.anyInt())).thenReturn(employee);
        mockMvc.perform(get("/api/getEmployee/1")).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.firstName").value("jashika"));
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        Employee e1 = new Employee();
        e1.setFirstName("jashika1");
        Employee e2 = new Employee();
        e2.setFirstName("jashika2");
        Employee e3 = new Employee();
        e3.setFirstName("jashika3");
        List<Employee> empList = new ArrayList<>(Arrays.asList(e1, e2, e3));
        when(employeeService.getAllEmployees()).thenReturn(empList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/allEmployees");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(this.mapToJson(empList), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Jashika");
        employee.setLastName("Vasu");
        employee.setEmailId("JashikaVasu@gmail.com");
        String inputInJson = this.mapToJson(employee);
        when(employeeService.updateEmployee(ArgumentMatchers.anyInt(), ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/updateEmployee/1")
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String outPutInJson = mvcResult.getRequest().getContentAsString();
        assertEquals(inputInJson, outPutInJson);
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        mockMvc.perform(delete("/api/deleteEmployee/1")).andExpect(status().isOk());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

