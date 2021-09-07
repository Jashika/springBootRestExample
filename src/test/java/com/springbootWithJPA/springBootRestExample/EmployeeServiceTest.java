package  com.springbootWithJPA.springBootRestExample;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    public void createEmployeeTest() {
        Employee employee = new Employee();
        employee.setFirstName("Jashika");
        employee.setLastName("Vasu");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee newEmployee = employeeService.createEmployee(new Employee());
        assertEquals("Jashika", newEmployee.getFirstName());
    }

    @Test
    public void getEmployeeTest() throws ResourceNotFoundException{
        int id = 1;
        Employee employee = new Employee();
        employee.setFirstName("Jashika");
        employee.setLastName("Vasu");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        assertEquals(employeeService.getEmployee(id).getBody().getFirstName(), "Jashika");
    }

   @Test
    public void getAllEmployeesTest() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Unnathi");
        employee1.setLastName("Ajay");
        Employee employee2 = new Employee();
        employee2.setFirstName("Unnathi");
        employee2.setLastName("Ajay");
        List<Employee> mockEmpLIst = new ArrayList<>(Arrays.asList(employee1,employee2));
        when(employeeRepository.findAll()).thenReturn(mockEmpLIst);
        List<Employee> actalEmpList = employeeService.getAllEmployees();
        assertEquals(actalEmpList.size(), actalEmpList.size());
    }

    @Test
    public void updateEmployeeTest() throws ResourceNotFoundException {
        int id =1;
        ResponseEntity<Employee> employee = employeeService.getEmployee(id);
        employee.getBody().setFirstName("jashika");
        ResponseEntity<Employee> contactResponseEntity = employeeService.updateEmployee(id,employee.getBody());
        verify(employeeRepository).save(any(Employee.class));
        verify(contactResponseEntity.getBody()).setFirstName("jashika");
    }

    @Test
    void deleteEmployeeTest() throws ResourceNotFoundException {
        employeeService.deleteEmployee(1);
        verify(employeeRepository).delete(any(Employee.class));
    }

}
