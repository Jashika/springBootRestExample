package com.springbootwithjpa.springbootrestexample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = EmployeeControllerTest.class)
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
        String URI = "/api/employee";
     when(employeeService.createEmployee(any(Employee.class))).thenReturn(mockEmployee);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String outputInJson = result.getRequest().getContentAsString();
    assertEquals(inputInJson,outputInJson);
    }


  @Test
    public void testGetEmployee() throws Exception {
     /*   Employee mockEmployee = new Employee();
        mockEmployee.setId(1);
        mockEmployee.setFirstName("Jashika");
        when(employeeService.getEmployee(1)).thenReturn(mockEmployee);*/
      /*  RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/getEmployee/"+1).contentType(MediaType.APPLICATION_JSON);*/
     /*   MvcResult   mvcResult = mockMvc.perform(requestBuilder).andReturn();*/
   /*  assertEquals(mockEmployee.getFirstName(), mvcResult.getResponse());*/
      //mockMvc.perform(get("/api/getEmployee/1").contentType(MediaType.APPLICATION_JSON)).andExpect((ResultMatcher) jsonPath("$.firstName").value(mockEmployee.getFirstName()));
    //System.out.println("asdsaf"+mockMvc.perform(get("/api/getEmployee/1").contentType(MediaType.APPLICATION_JSON)));
      Employee employee = new Employee();
      employee.setId(1);
      employee.setFirstName("binaya");
     /* employee.setLastName("kumar");
      employee.setEmailId("bk@email.com");*/

      Mockito.when(employeeService.getEmployee(1)).thenReturn(employee);
      mockMvc.perform(get("/api/getEmployee/1")).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.firstName").value("binaya"));
  }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

