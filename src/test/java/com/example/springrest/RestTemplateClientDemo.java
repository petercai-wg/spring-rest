package com.example.springrest;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import com.example.springrest.dto.EmployeeDTO;

public class RestTemplateClientDemo {
    public static final String REST_API_URL = "http://localhost:9080/rest/byManager/1";

    public static void main(String[] args) {
        System.out.println("API Client Demo");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<EmployeeDTO>> exchange = restTemplate.exchange(
                REST_API_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<EmployeeDTO>>() {
                });

        List<EmployeeDTO> employees = exchange.getBody();
        if (employees != null) {
            for (EmployeeDTO emp : employees) {
                System.out.println(emp);
            }
        }

    }

}
