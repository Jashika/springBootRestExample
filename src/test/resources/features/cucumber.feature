Feature: Test CRUD methods in Sample Employee REST API testing
  Scenario: Save employee
    Given  Valid employee object
    When I  send a  request a new employee is created in db
    Then I should get that employee object returned

  Scenario:I want to get a employee based on id
    Given id=75
    Then I should see the employee object for the id=75


  Scenario:I want to get a all employee from db
    Given Endpoint
    Then I should get all employees from the db

  Scenario:I want to update employee in db
    Given Employee object to be updated
    When I send a request with the object and id=79 an employee with specified id is updated in db
    Then I should see the updated employee object from db as response

  Scenario:I want to delete an employee from db
   Given An employee id=89 which has to be deleted
   Then I should see the  employee object deleted for id=89 from db




