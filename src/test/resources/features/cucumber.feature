Feature: Test CRUD methods in Sample Employee REST API testing
  Scenario: Save employee
    Given  Valid employee data
    When POST request is sent with employee data
    Then The employee should be saved in database

  Scenario:GET Employee
    Given Send GET request with employee id
    Then I should see employee data returned from database

  Scenario:GET Employee Record with non existing id in db
    Given Send GET request with employee id that does not exist in database
    Then Error code with status is returned

  Scenario:GET all Employee Records
    Given Send GET Employees service api endpoint
    Then Should get all employees from the database

  Scenario:Update Employee
    Given Employee to be updated
    When I Send a PUT HTTP Employee service request with the object and an existing employee id
    Then The employee is updated in database

  Scenario:Update employee which does not exist in the database
    Given Employee with an existing employee id to be updated
    When I Send a request with the employee data and an existing employee id
    Then I should get Not Found Exception returned

  Scenario:Delete employee
   Given Employee id
   Then I should see the  employee object deleted in the database

