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

    @Given("Valid employee object")
    public void valid_employee_object() {
        employee.setFirstName("Soumya");
        employee.setLastName("Nagesh");
        employee.setEmailId("Soumya@gmail.com");
    }

    @When("Send POST Employee service api endpoint with new employee object")
    public void send_post_request() {
        response = springBootRestExampleIntegrationTest.createEmployee(employee);
    }

    @Then("Recieve 200 HTTP response code")
    public void should_be_able_to_see_a_new_employee_created() {
        assertTrue(response.getStatusCode()== HttpStatus.OK);
    }

    @Given("Send GET Employee service api endpoint with id={int}")
    public void send_get_request(Integer id) {
       response = springBootRestExampleIntegrationTest.getEmployeeById(id);
    }
    @Then("Recieve valid HTTP response code 200")
    public void receive_valid_response() {
       assertTrue(response.getStatusCode()== HttpStatus.OK);
    }
    @Given("Send GET request with id={int}")
    public void send_get_request_negative_scenario(Integer id) {
        response = springBootRestExampleIntegrationTest.getEmployeeById(id);
    }
    @Then("Recieve  HTTP response code 404")
    public void receive_exception_with_status_code() {
        assertTrue(response.getStatusCode()==HttpStatus.NOT_FOUND);
    }

  @Given("Send GET Employees service api endpoint")
    public void have_specified_endpoint() {
        employees=springBootRestExampleIntegrationTest.getEmployees();
    }

    @Then("Should get all employees from the db")
    public void should_get_all_employees_from_the_db() {
        Assert.assertNotNull(Arrays.asList(employees).size());
    }

   @Given("Employee object to be with fields to be updated")
    public void values_to_be_updated_and_an_id() {
     String firstName="Nayana";
       String lastName="pg";
       String emailId="Nayana@gmail.com";
        requestBody = "{\"firstName\":\""+firstName+"\",\"lastName\":\""+lastName+"\",\"emailId\":\""+emailId+"\"}";
    }
    @When("Send a PUT HTTP Employee service request with the object and an existing employee id={int}")
    public void send_put_request(int id) {
        response=springBootRestExampleIntegrationTest.UpdateEmployeeById(requestBody,id);
    }
    @Then("Recieve HTTP response code 200")
    public void receieve_updated_valid_response() {
        assertTrue(response.getStatusCode()== HttpStatus.OK);
    }

  @Given("An employee id={int} which has to be deleted")
    public void have_specified_an_employee_id_which_has_to_be_deleted(int id) {
        springBootRestExampleIntegrationTest.deleteEmployeeById(id);
    }
    @Then("Then I should see the  employee object deleted for id={int}")
    public void should_see_the_employee_object_deleted_from_db(int id) {
        employees=springBootRestExampleIntegrationTest.getEmployees();
        assertFalse(Arrays.asList(employees).stream().anyMatch(item->item.getId()==(id)));
    }

    @Then("I should return null object")
    public void should_return_empty_object() {
        assertNull(emp);
    }


}
