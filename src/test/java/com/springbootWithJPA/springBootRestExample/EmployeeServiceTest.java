package  com.springbootWithJPA.springBootRestExample;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
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
        when(employeeRepository.save(ArgumentMatchers.any(Employee.class))).thenReturn(employee);
        Employee newEmployee = employeeService.createEmployee(new Employee());
        assertEquals("Jashika", newEmployee.getFirstName());
    }

    @Test
    public void getEmployeeTest() {
        Employee emp = new Employee();
        emp.setFirstName("Yashika");
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(emp));
        Employee newEmployee = employeeService.getEmployee(anyInt());
       assertEquals(emp.getFirstName(), newEmployee.getFirstName());
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

}
