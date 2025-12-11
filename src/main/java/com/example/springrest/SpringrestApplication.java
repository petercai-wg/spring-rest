package com.example.springrest;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;

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
			log.info("Application started...");

			getEmployeeDTO();

		};

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
