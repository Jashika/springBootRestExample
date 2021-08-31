Feature: Test CRUD methods in Sample Employee REST API testing
  Scenario: I want to create a new Employee
    Given I create a new Employee
    When I  send a  request a new employee is created in db
    Then I should get that employee object returned

  Scenario:I want to get a employee based on id
    Given I have specified id=75
    Then I should see the employee object for the id=75

  Scenario:I want to get a all employee from db
    Given I have specified endpoint
    Then I should get all employees from the db

  Scenario:I want to update employee in db
    Given Employee object to be updated
    When I send a request with the object and id=79 an employee with specified id is updated in db
    Then I should see the updated employee object from db as response

  Scenario:I want to delete an employee from db
    Given I have specified an employee id=86 which has to be deleted
    Then I should see the  employee object deleted for id=86 from db


