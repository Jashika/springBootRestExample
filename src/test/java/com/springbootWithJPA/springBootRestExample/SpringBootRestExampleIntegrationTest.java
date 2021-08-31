package com.springbootWithJPA.springBootRestExample;

import org.junit.platform.commons.logging.Logger;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class SpringBootRestExampleIntegrationTest implements Constants{
private Logger logger;

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String endpoint() {
        return SERVER_URL + ":" + port + THINGS_ENDPOINT;
    }

    public Employee createEmployee(final Employee something) {
        return restTemplate.postForEntity(endpoint(), something, Employee.class).getBody();
    }

    public Employee getEmployeeById(int id)  {
        return restTemplate.getForEntity(endpoint()+ "/" +id, Employee.class).getBody();
    }

    public Employee[] getEmployees() {
        return restTemplate.getForEntity(endpoint()+ "/employees", Employee[].class).getBody();
    }

    public Employee UpdateEmployeeById(Employee employee,int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
       return restTemplate.exchange(endpoint()+ "/" +id, HttpMethod.PUT,entity,Employee.class,id).getBody();
    }

    public void deleteEmployeeById(int id) {
        restTemplate.delete(endpoint()+"/" +id);
    }
}