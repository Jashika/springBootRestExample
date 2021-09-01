package com.springbootWithJPA.springBootRestExample;

import org.junit.platform.commons.logging.Logger;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class SpringBootRestExampleIntegrationTest implements Constants{
private Logger logger;

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response;
    private String endpoint() {
        return SERVER_URL + ":" + port + THINGS_ENDPOINT;
    }

    public ResponseEntity<String> createEmployee(final Employee something) {
        response=restTemplate.postForEntity(endpoint(), something, String.class);
        return response;
    }

    public ResponseEntity<String> getEmployeeById(int id)  {
        try {
            response = restTemplate.getForEntity(endpoint() + "/" + id, String.class);
            return response;
        }
        catch(RestClientResponseException e){
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getStatusText());
        }
    }

    public Employee[] getEmployees() {
        return restTemplate.getForEntity(endpoint()+ "/employees", Employee[].class).getBody();
    }

    public ResponseEntity<String> UpdateEmployeeById(String requestBody,int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        response=restTemplate.exchange(endpoint()+ "/" +id, HttpMethod.PUT,entity,String.class,id);
        return response;
    }

    public void deleteEmployeeById(int id) {
        restTemplate.delete(endpoint()+"/" +id);
    }
}