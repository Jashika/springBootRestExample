package com.springbootWithJPA.springBootRestExample;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StepDefinitions {
    @Autowired
    SpringBootRestExampleIntegrationTest springBootRestExampleIntegrationTest;
    Employee employee = new Employee();
    Employee emp;
    Employee[] employees;
    ResponseEntity<String> response;
    String requestBody;

    @Given("Valid employee data")
    public void createEmployeeObject() {
        employee.setFirstName("Soumya");
        employee.setLastName("Nagesh");
        employee.setEmailId("Soumya@gmail.com");
    }

    @When("POST request is sent with employee data")
    public void sendPostRequest() {
        response = springBootRestExampleIntegrationTest.createEmployee(employee);
    }

    @Then("The employee should be saved in database")
    public void checkEmployeeCreated() {
        assertTrue(response.getStatusCode()== HttpStatus.OK);
    }

    @Given("Send GET request with employee id")
    public void sendGetRequest() {
       response = springBootRestExampleIntegrationTest.getEmployeeById(79);
    }
    @Then("I should see employee data returned from database")
    public void receiveGetResponse() {
       assertTrue(response.getStatusCode()== HttpStatus.OK);
    }
    @Given("Send GET request with employee id that does not exist in database")
    public void sendGetRequestWithInvalidId() {
        response = springBootRestExampleIntegrationTest.getEmployeeById(1);
    }
    @Then("Error is returned")
    public void receiveErrorCode() {
        assertTrue(response.getStatusCode()==HttpStatus.NOT_FOUND);
    }

  @Given("Send GET Employees service api endpoint")
    public void getAllEmployees() {
        employees=springBootRestExampleIntegrationTest.getEmployees();
    }

    @Then("Should get all employees from the database")
    public void checkAllEmployeesReturned() {
        Assert.assertNotNull(Arrays.asList(employees).size());
    }

   @Given("Employee to be updated")
    public void dataToBeUpdated() {
     String firstName="Nayana";
       String lastName="pg";
       String emailId="Nayana@gmail.com";
        requestBody = "{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\",\"emailId\":\""+emailId+"\"}";
    }
    @When("I Send a PUT HTTP Employee service request with the object and an existing employee id")
    public void sendPutRequest() {
        response=springBootRestExampleIntegrationTest.UpdateEmployeeById(requestBody,79);
    }
    @Then("The employee is updated in database")
    public void receiveUpdatedResponse() {
        assertTrue(response.getStatusCode()== HttpStatus.OK);
    }

  @Given("Employee id")
    public void deleteEmployee() {
        springBootRestExampleIntegrationTest.deleteEmployeeById(98);
    }
    @Then("I should see the  employee object deleted in the database")
    public void checkEmployeeDeleted() {
        employees=springBootRestExampleIntegrationTest.getEmployees();
        assertFalse(Arrays.asList(employees).stream().anyMatch(item->item.getId()==98));
    }

}
