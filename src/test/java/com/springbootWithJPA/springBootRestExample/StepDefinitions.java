package com.springbootWithJPA.springBootRestExample;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StepDefinitions {
    @Autowired
    SpringBootRestExampleIntegrationTest springBootRestExampleIntegrationTest;
    Employee employee = new Employee();
    Employee emp;
    Employee[] employees;

    @Given("Valid employee object")
    public void valid_employee_object() {
        employee.setFirstName("Soumya");
        employee.setLastName("Nagesh");
        employee.setEmailId("Soumya@gmail.com");
    }

    @When("I  send a  request a new employee is created in db")
    public void send_a_request_with_Id_firstName_Jashika_lastName_Vasu_emailId_JashikaVasu_gmail_com_a_new_employee_is_created() {
        emp = springBootRestExampleIntegrationTest.createEmployee(employee);
    }

    @Then("I should get that employee object returned")
    public void should_be_able_to_see_a_new_employee_created() {
        assertEquals(employee.getFirstName(), emp.getFirstName());
    }

    @Given("id={int}")
    public void have_specified_id(Integer id) {
        emp = springBootRestExampleIntegrationTest.getEmployeeById(id);
    }
    @Then("I should see the employee object for the id={int}")
    public void should_see_the_employee_object_for_the_id(int id) {
        assertNotNull(emp.getId());
    }

  @Given("Endpoint")
    public void have_specified_endpoint() {
        employees=springBootRestExampleIntegrationTest.getEmployees();
    }
    @Then("I should get all employees from the db")
    public void should_get_all_employees_from_the_db() {
        Assert.assertNotNull(Arrays.asList(employees).size());
    }

   @Given("Employee object to be updated")
    public void values_to_be_updated_and_an_id() {
        employee.setFirstName("YashikaHassan");
       employee.setLastName("Balagopal");
       employee.setEmailId("Yashika@gmail.com");
    }
    @When("I send a request with the object and id={int} an employee with specified id is updated in db")
    public void send_a_request_with_the_values_and_id_an_employee_with_specified_id_is_updated_in_db(int id) {
        emp=springBootRestExampleIntegrationTest.UpdateEmployeeById(employee,id);
    }
    @Then("I should see the updated employee object from db as response")
    public void should_see_the_updated_employee_object_from_db_as_response() {
       assertNotNull(emp);
    }
//  @Given("An employee id={int} which has to be deleted")
//    public void have_specified_an_employee_id_which_has_to_be_deleted(int id) {
//        springBootRestExampleIntegrationTest.deleteEmployeeById(id);
//    }
//    @Then("I should see the  employee object deleted for id={int} from db")
//    public void should_see_the_employee_object_deleted_from_db(int id) {
//        employees=springBootRestExampleIntegrationTest.getEmployees();
//        assertFalse(Arrays.asList(employees).stream().anyMatch(item->item.getId()==(id)));
//    }

    @Given("invalid id={int}")
    public void given_invalid_id(Integer id) {
        emp = springBootRestExampleIntegrationTest.getEmployeeById(id);
    }
    @Then("I should return null object")
    public void should_return_empty_object() {
        assertNull(emp);
    }


}
