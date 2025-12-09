package com.example.springrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLClientConfig {

    private static final String GRAPHQL_ENDPOINT = "http://localhost:9080/graphql";
    // build-in GUI http://localhost:9080/graphiql?path=/graphql

    @Bean
    public HttpGraphQlClient graphQlClient() {

        WebClient webClient = WebClient.builder()
                .baseUrl(GRAPHQL_ENDPOINT)
                .build();

        return HttpGraphQlClient.builder(webClient).build();
    }
}