package com.example.springrest;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;

import com.example.springrest.dto.GqlEmployee;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Slf4j
@SpringBootApplication
public class SpringrestApplication {

	private final HttpGraphQlClient graphQlClient;

	public static void main(String[] args) {
		SpringApplication.run(SpringrestApplication.class, args);
	}

	public List<GqlEmployee> getEmployees() {
		String graphQLQuery = """
				query GetEmployees {
				    getEmployees {
				        firstname
				        jobLevel
				    }
				}
				""";

		log.info("graphQLQuery " + graphQLQuery);
		return graphQlClient.document(graphQLQuery)
				.retrieve("getEmployees")
				.toEntityList(GqlEmployee.class).block();
	}

	@Bean
	public CommandLineRunner startup() {
		return args -> {
			log.info("Application started...");

			List<GqlEmployee> employees = getEmployees();

			log.info("Employees list " + employees.size());
			for (GqlEmployee e : employees) {
				log.info(e.getFirstname() + " " + e.getJobLevel());
			}
		};

	}

}
