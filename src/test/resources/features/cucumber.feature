Feature: Test CRUD methods in Sample Employee REST API testing
#  Scenario: Save employee
#    Given  Valid employee object
#    When Send POST Employee service api endpoint with new employee object
#    Then Recieve 200 HTTP response code

  Scenario:GET Employee Record
    Given Send GET Employee service api endpoint with id=75
    Then Recieve valid HTTP response code 200

  Scenario:GET Employee Record with non existing id in db
    Given Send GET request with id=1
    Then Recieve  HTTP response code 404


  Scenario:GET all Employee Records
    Given Send GET Employees service api endpoint
    Then Should get all employees from the db

  Scenario:Update Employee Record
    Given Employee object to be with fields to be updated
    When Send a PUT HTTP Employee service request with the object and an existing employee id=79
    Then Recieve HTTP response code 200
#
#  Scenario:Delete a employee Record
#   Given An employee id=90 which has to be deleted
#   Then I should see the  employee object deleted for id=90




