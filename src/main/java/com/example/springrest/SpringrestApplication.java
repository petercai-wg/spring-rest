package com.example.springrest;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.springrest.dto.EmployeeDTO;
import com.example.springrest.dto.GqlEmployee;
import com.example.springrest.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class SpringrestApplication {

	private final HttpGraphQlClient graphQlClient;
	private final EmployeeService service;

	public static void main(String[] args) {
		SpringApplication.run(SpringrestApplication.class, args);
	}

	@Bean
	public CommandLineRunner startup() {
		return args -> {
			log.debug("Application started...");

			getRestClientEmployeeDTO();

		};

	}

	public void getWebClientEmployeeDTO() {
		WebClient webClient = WebClient.create("http://localhost:9080");

		List<EmployeeDTO> employeeDTOs = webClient.get()
				.uri("/rest/byManager/1")
				.retrieve()
				.bodyToFlux(EmployeeDTO.class)
				.collectList()
				.block();

		log.info("EmployeeDTO list " + employeeDTOs.size());

		for (EmployeeDTO dto : employeeDTOs) {
			log.info(dto.toString());
		}
	}

	public void getRestClientEmployeeDTO() {

		log.debug("RestClient call getRestClientEmployeeDTO ...");

		RestClient restClient = RestClient.builder().baseUrl("http://localhost:9080").build();

		List<EmployeeDTO> employeeDTOs = restClient.get().uri("/rest/byManager/1").retrieve()
				.body(new ParameterizedTypeReference<List<EmployeeDTO>>() {
				});

		log.info("EmployeeDTO list " + employeeDTOs.size());

		for (EmployeeDTO dto : employeeDTOs) {
			log.info(dto.toString());
		}

	}

	public void getGQLEmployees() {
		String graphQLQuery = """
				query GetEmployees {
				    getEmployees {
				        firstname
				        jobLevel
				    }
				}
				""";

		log.info("graphQLQuery : {}", graphQLQuery);
		List<GqlEmployee> employees = graphQlClient.document(graphQLQuery)
				.retrieve("getEmployees")
				.toEntityList(GqlEmployee.class).block();
		log.info("Employees list " + employees.size());
		for (GqlEmployee e : employees) {
			log.info(e.getFirstname() + " " + e.getJobLevel());
		}

	}

	public void getEmployeeDTO() {

		List<EmployeeDTO> employeeDTOs = service.getEmployeeDTO(1);
		log.info("EmployeeDTO list " + employeeDTOs.size());

		for (EmployeeDTO dto : employeeDTOs) {
			log.info(dto.toString());
		}

	}
}
